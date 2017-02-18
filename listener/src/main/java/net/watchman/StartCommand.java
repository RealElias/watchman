package net.watchman;

import com.google.inject.Inject;
import com.google.inject.Provider;
import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.eclipse.jetty.server.Server;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created Ilya Vasiuk me on 18.02.17 17:11.
 */
public class StartCommand extends CommandWithMetadata {

    @Inject
    private Provider<Server> serverProvider;

    public StartCommand() {
        super(CommandMetadata.builder(StartCommand.class).build());
    }

    public CommandOutcome run(Cli cli) {
        Server server = serverProvider.get();

        try {
            register();
            server.start();
        } catch (Exception e) {
            return CommandOutcome.failed(1, e);
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException ie) {

            // interruption of a running Jetty daemon is a normal event, so unless we get shutdown errors, return success
            try {
                server.stop();
            } catch (Exception se) {
                return CommandOutcome.failed(1, se);
            }
        }

        return CommandOutcome.succeeded();
    }

    private void register() throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPut putRequest = new HttpPut("http://localhost:7777/watchman");

        HttpParams params = new BasicHttpParams();
        params.setParameter("name", "instance1");
        params.setParameter("ip", Inet4Address.getLocalHost().getHostName());
        putRequest.setParams(params);

        httpClient.execute(putRequest);
    }

}
