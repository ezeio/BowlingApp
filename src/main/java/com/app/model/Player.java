package com.app.model;

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
}
