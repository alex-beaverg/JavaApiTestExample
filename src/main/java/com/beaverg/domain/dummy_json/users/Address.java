package com.beaverg.domain.dummy_json.users;

import java.util.Objects;

public class Address {
    private String address;
    private String city;
    private Coordinates coordinates;
    private String stateCode;
    private String postalCode;
    private String state;
    private String country;

    public Address() { }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address) &&
                Objects.equals(city, address1.city) &&
                Objects.equals(country, address1.country) &&
                Objects.equals(coordinates, address1.coordinates) &&
                Objects.equals(stateCode, address1.stateCode) &&
                Objects.equals(postalCode, address1.postalCode) &&
                Objects.equals(state, address1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, coordinates, postalCode, state, stateCode, country);
    }
}
