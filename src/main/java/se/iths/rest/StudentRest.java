package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.StudentEmail;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    private final StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("")
    @POST
    public Response createStudent(Student student, @Context UriInfo uriInfo) {
        studentService.createStudent(student);
        URI createdUri = studentService.generateCreatedUri(uriInfo, student.getId());
        return Response.created(createdUri).build();
    }


    @Path("{id}")
    @PUT
    public Response updateStudent(@Context UriInfo uriInfo, @PathParam("id") Long id, Student student) {

        if (studentService.updateStudent(id, student)) {
            return Response.ok(student).build();
        } else {
            URI createdUri = studentService.generateCreatedUri(uriInfo, student.getId());
            return Response.created(createdUri).build();
        }

    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        var foundStudent = studentService.findById(id);
        if (foundStudent != null)
            return Response.ok(foundStudent).build();
        throw new StudentNotFoundException(id);
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        var allStudents = studentService.getAll();

        return Response.ok(allStudents).build();
    }

    @Path("/lastname")
    @GET
    public Response getByLastName(@QueryParam("lastName") String lastName) {
        List<Student> byLastName = studentService.findByLastName(lastName);

        return Response.ok(byLastName).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (studentService.delete(id)) {
            return Response.noContent().build();
        }
        throw new StudentNotFoundException(id);
    }

    @Path("{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @Valid StudentEmail studentEmail) {
        if (studentService.updateEmail(id, studentEmail)) {
            return Response.noContent().build();
        }
        throw new StudentNotFoundException(id);
    }

}
