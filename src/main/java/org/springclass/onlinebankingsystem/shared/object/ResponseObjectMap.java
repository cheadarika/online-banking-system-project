package org.springclass.onlinebankingsystem.shared.object;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Component
public class ResponseObjectMap {

    private ResponseObject responseObject;

    public Map<String, Object> responseObject(Optional<Object> obj, Long totalElements) {
        Map<String, Object> response = new HashMap<>();
        if (obj.isPresent()) {
            response.put("results", obj);
            response.put("length", totalElements);
            response.put("response", responseObject.success());
        } else {
            response.put("response", responseObject.error());
        }
        return response;
    }

    public Map<String, Object> responseObject(Optional<Object> obj, Long totalElements, int page, int size) {
        Map<String, Object> response = new HashMap<>();
        if (obj.isPresent()) {
            response.put("size", size);
            response.put("page", page);
            response.put("results", obj);
            response.put("length", totalElements);
            response.put("response", responseObject.success());
            response.put("totalPage", ((totalElements + size - 1) / size));
        } else {
            response.put("response", responseObject.error());
        }
        return response;
    }

    public Map<String, ?> responseObject(Optional<Object> obj) {
        Map<String, Object> response = new HashMap<>();
        if (obj.isPresent()) {
            response.put("results", obj);
            response.put("response", responseObject.success());
        } else {
            response.put("response", responseObject.error());
        }
        return response;
    }

    public Map<String, Object> responseCodeWithMessage(int code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("response", new ResponseObject(code, message));
        return response;
    }

    public Map<String, Object> responseCodeWithMessageAndObj(int code, String message, Object obj) {
        Map<String, Object> response = new HashMap<>();
        response.put("response", new ResponseObject(code, message));
        response.put("results", obj);
        return response;
    }
}
