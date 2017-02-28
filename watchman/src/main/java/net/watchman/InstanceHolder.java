package net.watchman;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ilya Vasiuk on 18.02.17 20:15 21:14.
 */
public final class InstanceHolder {

    private final static HashMap<String, String> instances = new HashMap<String, String>();

    public static void addInstance(String name, String uri) {
        instances.put(name, uri);
    }

    public static Map<String, String> getInstances() {
        return instances;
    }
}
