package se.boalbert.rest;

import se.boalbert.entity.Student;
import se.boalbert.entity.StudentEmail;
import se.boalbert.entity.Subject;
import se.boalbert.exception.StudentNotFoundException;
import se.boalbert.service.StudentService;
import se.boalbert.service.SubjectService;

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
    private final SubjectService subjectService;

    @Inject
    public StudentRest(StudentService studentService, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Path("{id}")
    @GET
    public Response get(@PathParam("id") Long id) {
        var foundStudent = studentService.findById(id);
        return Response.ok(foundStudent).build();
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

    @Path("{id}/subjects")
    @POST
    public Response addSubject(@PathParam("id") Long studentId, Subject subject) {
        var foundStudent = studentService.findById(studentId);
        var updatedStudent = studentService.addSubjectToStudent(foundStudent, subject);
        return Response.ok(updatedStudent).build();

    }
}
