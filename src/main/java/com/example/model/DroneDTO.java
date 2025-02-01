package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DroneDTO {
    private int id;
    private String dronetype;
    private String created;
    private String serialnumber;
    
    @JsonProperty("carriage_weight")
    private int carriageWeight;
    
    @JsonProperty("carriage_type")
    private String carriageType;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDronetype() { return dronetype; }
    public void setDronetype(String dronetype) { this.dronetype = dronetype; }
    public String getCreated() { return created; }
    public void setCreated(String created) { this.created = created; }
    public String getSerialnumber() { return serialnumber; }
    public void setSerialnumber(String serialnumber) { this.serialnumber = serialnumber; }
    public int getCarriageWeight() { return carriageWeight; }
    public void setCarriageWeight(int carriageWeight) { this.carriageWeight = carriageWeight; }
    public String getCarriageType() { return carriageType; }
    public void setCarriageType(String carriageType) { this.carriageType = carriageType; }
}
