package com.company.FinalProject.ISS;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpaceResponse {

    @JsonProperty("iss_position")
    private ISSPosition iss_position;
    private double timestamp;
    private String message;

    // getters + setters
    public ISSPosition getIss_position() {
        return iss_position;
    }

    public void setIss_position(ISSPosition iss_position) {
        this.iss_position = iss_position;
    }

    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
