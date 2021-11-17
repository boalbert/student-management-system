package se.boalbert.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ConstraintViolationMessage> errors = exception.getConstraintViolations()
                .stream()
                .map(this::toValidationError)
                .collect(toList());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .type(MediaType.APPLICATION_JSON).build();
    }

    private ConstraintViolationMessage toValidationError(ConstraintViolation constraintViolation) {
        ConstraintViolationMessage error = new ConstraintViolationMessage();
        error.setProperty(constraintViolation.getPropertyPath().toString());
        error.setMessage(constraintViolation.getMessage());
        return error;
    }
}