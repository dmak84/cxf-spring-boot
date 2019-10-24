package sample.rs.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import io.swagger.annotations.Api;

@Path("/sayYell")
@Api(value="/say/Yell")
@Service
public interface YellService {

    @GET
    @Path("/{a}")
    @Produces(MediaType.TEXT_PLAIN)
    String sayYell(@PathParam("a") String a);

}