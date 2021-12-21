package kea.dk.kommunevalg_julius.exceptions;

import lombok.Data;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Julius Panduro
 */
@Data
public class ExceptionDetail {
    private final int status;
    private final String message;
    private final String path;
    private final ZonedDateTime timestamp;

    public ExceptionDetail(int status, String message, String path) {
        super();
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = ZonedDateTime.now(ZoneId.of("Europe/Copenhagen"));
    }
}