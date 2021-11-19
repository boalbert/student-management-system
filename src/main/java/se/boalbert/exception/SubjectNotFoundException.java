package se.boalbert.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SubjectNotFoundException extends WebApplicationException {
    public SubjectNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND)
                .entity(new SubjectNotFoundMessage(message + " was not found."))
                .type(MediaType.APPLICATION_JSON)
                .build());
    }
}
