package net.watchman;

import com.google.inject.Binder;
import com.google.inject.Module;
import io.bootique.Bootique;
import io.bootique.jersey.JerseyModule;

/**
 * Created by Ilya Vasiuk on 18.02.17 12:50.
 */
public class WatchmanApp implements Module {

    public static void main(String[] args) {
        Bootique.app(args)
                .modules(WatchmanApp.class)
                .autoLoadModules()
                .run();
    }

    public void configure(Binder binder) {
        JerseyModule.contributeResources(binder).addBinding().to(WatchmanApi.class);
    }
}
