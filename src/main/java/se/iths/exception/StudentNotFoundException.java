package se.iths.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class StudentNotFoundException extends WebApplicationException {
    public StudentNotFoundException(Long id) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity("Student ID: '" + id + "' not found.")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build());
    }
}
