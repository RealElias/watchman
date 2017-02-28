package net.watchman;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Ilya Vasiuk on 18.02.17 14:26.
 */
@Path("/")
public class WatchmanApi {

    private static Logger LOG = LoggerFactory.getLogger(WatchmanApi.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String addListener() {
        final JSONObject json = new JSONObject();
        final JSONArray array = new JSONArray();
        array.put(InstanceHolder.getInstances());
        json.put("instances", array);

        return json.toString();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addListener(@FormParam("name") String name, @Context HttpServletRequest requestContext) {
        String uri = requestContext.getRemoteAddr();

        InstanceHolder.addInstance(name, uri);
        LOG.info(String.format("New instance '%s' at %s", name, uri));

        return Response.ok().build();
    }
}
