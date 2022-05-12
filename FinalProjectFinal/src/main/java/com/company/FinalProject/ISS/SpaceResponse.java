package com.company.FinalProject.ISS;


public class SpaceResponse {

    private IssCoordinates iss_position;
    private String timestamp;
    private String message;


    // getters + setters
    public IssCoordinates getIss_position() {
        return iss_position;
    }

    public void setIss_position(IssCoordinates iss_position) {
        this.iss_position = iss_position;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
