package com.example.salonmain;

public class service_data {

    public service_data(){

    }
    private String serviceName, Description, Duration;
    private double price;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public service_data(String serviceName, String description, String duration, double price) {
        this.serviceName = serviceName;
        Description = description;
        Duration = duration;
        this.price = price;
    }
}
