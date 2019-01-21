package ca.shorturl.rest.resources;

import ca.shorturl.business.ShortURLBusiness;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

@Path("shorturl")
@Produces({APPLICATION_XML, APPLICATION_JSON})
@Consumes({APPLICATION_XML, APPLICATION_JSON})
public class ShorURLResource {

    @Inject
    ShortURLBusiness business;

    @POST
    public Response createShortURL(@NotNull String longURL) {
        final String result = business.createNewShortURL(longURL);
        return Response.ok(result).build();
    }

    @GET
    @Path("{url}")
    public Response getLongURL(@PathParam("url") @NotNull String shortURL) {
        final String result = business.getLongURL(shortURL);
        return Response.ok(result).build();
    }
}
