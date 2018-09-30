package local.qqq.presentation.controller;

import java.time.Clock;
import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;

public class Response<T> {
    private final HttpStatus status;
    private final String message;
    private final T entity;
    private final OffsetDateTime timestamp;

    public Response(HttpStatus status, String message, T entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
        this.timestamp = OffsetDateTime.now(Clock.systemUTC());
    }

    public int getStatus() {
        return status.value();
    }

    public String getReason() {
        return status.getReasonPhrase();
    }

    public String getMessage() {
        return message;
    }

    public T getEntity() {
        return entity;
    }

    public String getTimestamp() {
        return timestamp.toString();
    }
}