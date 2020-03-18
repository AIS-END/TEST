package com.pinyougou.common;

import java.io.Serializable;
import java.util.HashMap;

public class Result implements Serializable {
    private boolean success;
    private String message;
    private HashMap<String, Object> map;

    public Result() {
    }

    public Result(String message) {

        this.message = message;
    }

    public Result(boolean success) {

        this.success = success;
    }

    public Result(boolean success, String message) {

        this.success = success;
        this.message = message;
    }

    public HashMap<String, Object> getMap() {
        map = new HashMap<String, Object>();
        this.map.put("success", success);
        this.map.put("message", message);
        return this.map;
    }
}
