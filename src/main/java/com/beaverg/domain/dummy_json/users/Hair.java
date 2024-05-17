package com.beaverg.domain.dummy_json.users;

import java.util.Objects;

public class Hair {
    private String color;
    private String type;

    public Hair() { }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hair hair = (Hair) o;
        return Objects.equals(color, hair.color) &&
                Objects.equals(type, hair.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
