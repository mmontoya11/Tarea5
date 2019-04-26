package com.iteso.sesion9.beans;

public class Store {
    private	int	id;
    private	String	name;
    private	String	phone;
    private	int	thumbnail;
    private	double	latitude;
    private	double	longitude;
    private	City	city;

    public Store() {
    }

    public Store(int id, String name, String phone, int thumbnail, double latitude, double longitude, City city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.thumbnail = thumbnail;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
