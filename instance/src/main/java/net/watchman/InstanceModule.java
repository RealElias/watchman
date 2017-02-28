package net.watchman;

import com.google.inject.Binder;
import com.google.inject.Provides;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.ConfigModule;
import io.bootique.config.ConfigurationFactory;
import io.bootique.meta.application.OptionMetadata;

/**
 * Created by Ilya Vasiuk on 18.02.17 16:34.
 */
public class InstanceModule extends ConfigModule {

    public static final String HOST = "host";

    public static void main(String[] args) {
        Bootique.app(args)
                .modules(InstanceModule.class)
                .autoLoadModules()
                .run();
    }

    public void configure(Binder binder) {
        BQCoreModule.contributeCommands(binder).addBinding().to(RegisterCommand.class);

        OptionMetadata hostOption = OptionMetadata
                .builder(HOST)
                .description("Parent host")
                .valueRequired()
                .build();

        BQCoreModule.contributeOptions(binder).addBinding().toInstance(hostOption);
    }

    @Provides
    InstanceConfig provideInstanceConfig(ConfigurationFactory configurationFactory) {
        return configurationFactory.config(InstanceConfig.class, configPrefix);
    }
}
