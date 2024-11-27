package com.ccapstools_app.exceptions;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable{
     
    private static final long serialVersionUID = 1L;

    private String message;
    private String details;
    private Date timestamp;

    public ExceptionResponse(String message, String details, Date timestamp) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }
    public String getDetails() {
        return details;
    }
    public Date getTimestamp() {
        return timestamp;
    }
}
