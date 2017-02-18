package net.watchman;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.BQCoreModule;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

/**
 * Created Ilya Vasiuk me on 18.02.17 16:34.
 */
public class ListenerApp implements Module {

    public static void main(String[] args) {
        Bootique.app(args)
                .module(ListenerApp.class)
                .autoLoadModules()
                .run();
    }

    public void configure(Binder binder) {
        JerseyModule.contributeResources(binder).addBinding().to(ListenerApi.class);

        BQCoreModule.contributeCommands(binder).addBinding().to(StartCommand.class);
    }
}
