package net.watchman;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created Ilya Vasiuk me on 18.02.17 16:42.
 */
@Path("/")
public class ListenerApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String echo() {
        return "I'm still alive!";
    }
}
