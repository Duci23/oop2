package me.fit.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/time/current")
@RegisterRestClient(configKey = "time-api")
public interface TimeClient {

    @GET
    @Path("/ip")
    @Produces(MediaType.APPLICATION_JSON)
    String getTime(@QueryParam("ipAddress") String ip);
}