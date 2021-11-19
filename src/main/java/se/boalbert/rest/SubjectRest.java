package se.boalbert.rest;

import se.boalbert.entity.Subject;
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
    @POST
    public Response create(Subject subject) {
        subjectService.createSubject(subject);
        return Response.ok(subject).build();
    }

    @Path("")
    @GET
    public Response getAll() {

        var all = subjectService.getAll();

        return Response.ok(all).build();
    }
}
