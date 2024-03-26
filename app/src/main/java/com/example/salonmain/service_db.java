package com.example.salonmain;

public class service_db { //this the data for getting and setting the service information
    private String serviceID;
    private String name;
    private String description;
    private String duration; // Assuming duration is in minutes
    private double price; // Price attribute
    public service_db(){

    }
    @Override
    public String toString() {
        return "service_db{" +
                "serviceID='" + serviceID + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public service_db(String name, String description, String duration, double price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }


}
