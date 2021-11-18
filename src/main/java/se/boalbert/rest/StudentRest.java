package se.boalbert.rest;

import se.boalbert.entity.Student;
import se.boalbert.entity.StudentEmail;
import se.boalbert.exception.StudentNotFoundException;
import se.boalbert.service.StudentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    private final StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("{id}")
    @GET
    public Response get(@PathParam("id") Long id) {
        var foundStudent = studentService.findById(id);
        if (foundStudent != null)
            return Response.ok(foundStudent).build();

        throw new StudentNotFoundException(id);
    }

    @Path("")
    @GET
    public Response getAll() {
        var all = studentService.getAll();
        return Response.ok(all).build();
    }

    @Path("/search")
    @GET
    public Response getByLastName(@QueryParam("lastName") String lastName) {
        var byLastName = studentService.findByLastName(lastName);
        return Response.ok(byLastName).build();
    }

    @Path("{id}")
    @DELETE
    public Response delete(@PathParam("id") Long id) {
        if (studentService.delete(id))
            return Response.noContent().build();

        throw new StudentNotFoundException(id);
    }

    @Path("")
    @POST
    public Response create(Student student, @Context UriInfo uriInfo) {
        studentService.createStudent(student);
        URI createdUri = studentService.generateCreatedUri(uriInfo, student.getId());
        return Response.created(createdUri).entity(student).build();
    }

    @Path("{id}")
    @PUT
    public Response update(@Context UriInfo uriInfo, @PathParam("id") Long id, Student student) {
        if (studentService.update(id, student))
            return Response.ok(student).build();

        URI createdUri = studentService.generateCreatedUri(uriInfo, student.getId());
        return Response.created(createdUri).entity(student).build();
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @Valid StudentEmail studentEmail) {
        if (studentService.replaceEmail(id, studentEmail))
            return Response.ok(studentEmail).build();

        throw new StudentNotFoundException(id);
    }
}
