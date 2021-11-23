package se.boalbert.rest;

import se.boalbert.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    private final SubjectService subjectService;

    @Inject
    public SubjectRest(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Path("")
    @GET
    public Response getAll() {
        var all = subjectService.getAll();

        return Response.ok(all).build();
    }

    @Path("/search")
    @GET
    public Response getByName(@QueryParam("subject") String subjectName) {
        var foundSubject = subjectService.findSubjecetByName(subjectName);

        return Response.ok(foundSubject).build();
    }
}
