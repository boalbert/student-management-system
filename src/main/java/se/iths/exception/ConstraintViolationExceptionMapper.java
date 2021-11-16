package se.iths.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ConstraintValidationError> errors = exception.getConstraintViolations().stream()
                .map(this::toValidationError)
                .collect(Collectors.toList());

        return Response.status(Response.Status.BAD_REQUEST).entity(errors)
                .type(MediaType.APPLICATION_JSON).build();
    }

    private ConstraintValidationError toValidationError(ConstraintViolation constraintViolation) {
        ConstraintValidationError error = new ConstraintValidationError();
        error.setPath(constraintViolation.getPropertyPath().toString());
        error.setMessage(constraintViolation.getMessage());
        return error;
    }
}