package com.beaverg.domain.dummy_json.users;

import java.util.Objects;

public class Company {
    private Address address;
    private String department;
    private String name;
    private String title;

    public Company() { }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(address, company.address) &&
                Objects.equals(department, company.department) &&
                Objects.equals(name, company.name) &&
                Objects.equals(title, company.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, department, name, title);
    }
}
