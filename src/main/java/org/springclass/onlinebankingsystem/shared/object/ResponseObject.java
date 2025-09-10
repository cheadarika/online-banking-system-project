package org.springclass.onlinebankingsystem.shared.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseObject {
    private int statusCode;
    private String message;

    public ResponseObject(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseObject success() {
        return new ResponseObject(200, "success");
    }

    public ResponseObject error() {
        return new ResponseObject(404, "Error object not found");
    }
}
