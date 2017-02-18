package net.watchman;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created Ilya Vasiuk me on 18.02.17 20:15.
 */
public final class InstanceHolder {

    private final static HashMap<String, URI> instances = new HashMap<String, URI>();

    public static void addInstance(String name, URI uri) {
        instances.put(name, uri);
    }

    public static Map<String, URI> getInstances() {
        return instances;
    }
}
