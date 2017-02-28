package net.watchman;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.jersey.client.HttpClientFactory;
import io.bootique.meta.application.CommandMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Ilya Vasiuk on 18.02.17 17:11.
 */
public class RegisterCommand extends CommandWithMetadata {

    private Logger LOG = LoggerFactory.getLogger(RegisterCommand.class);

    @Inject
    private Provider<HttpClientFactory> clientFactoryProvider;

    @Inject
    private Provider<InstanceConfig> instanceConfigProvider;

    public RegisterCommand() {
        super(CommandMetadata.builder(RegisterCommand.class).build());
    }

    public CommandOutcome run(Cli cli) {
        try {
            return register(cli.optionString(InstanceApp.HOST));
        } catch (Exception e) {
            return CommandOutcome.failed(1, e);
        }
    }

    private CommandOutcome register(String host) throws IOException {
        Client client = clientFactoryProvider.get().newClient();

        Form form = new Form();
        form.param("name", instanceConfigProvider.get().getName());
        Response response = client
                .target(host)
                .request()
                .put(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return CommandOutcome.succeeded();
        } else {
            return CommandOutcome.failed(1, "Server doesn't response");
        }
    }

}
