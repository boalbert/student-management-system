package se.iths.rest;

import se.iths.entity.Student;
import se.iths.exception.StudentNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    StudentService studentService;

    @Inject
    public StudentRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @Path("")
    @POST
    public Response createStudent(Student student, @Context UriInfo uriInfo) {

        studentService.createStudent(student);

        var createdUri =
                uriInfo.getBaseUriBuilder().path(student.getId().toString()).build();

        return Response.created(createdUri).build();
    }

//    @Path("{id}")
//    public Response updateStudent(Student student) {
//        // Todo Implement UPDATE
//        return null;
//    }
//
    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        var foundStudent = studentService.findById(id);
        if(foundStudent != null)
            return Response.ok(foundStudent).build();

        throw new StudentNotFoundException(id);
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        var allStudents = studentService.getAll();

        return Response.ok(allStudents).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        if (studentService.delete(id)) {
            return Response.ok().build();
        }
        throw new StudentNotFoundException(id);
    }
}
