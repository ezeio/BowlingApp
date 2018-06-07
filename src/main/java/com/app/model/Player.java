package com.app.model;

import java.util.Objects;

public class Player {

    private String firstName;
    private String lastName;
    private String formattedName;

    public Player(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFormattedName(String string) {
        formattedName = string;
    }
    public String getFormattedName() {
        return formattedName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getFirstName(), player.getFirstName()) &&
                Objects.equals(getLastName(), player.getLastName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFirstName(), getLastName());
    }


    @Override
    public String toString() {
        return firstName + "\t" + lastName;
    }
}
