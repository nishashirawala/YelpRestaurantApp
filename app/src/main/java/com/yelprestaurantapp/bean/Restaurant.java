package com.yelprestaurantapp.bean;


public class Restaurant {
    private String id;
    private String name;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        Restaurant other = (Restaurant) o;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
