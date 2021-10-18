package com.switchfully.funiversity.webapi.dtos;

public class ProfessorDTO {

    private final int id;
    private final String firstName;
    private final String lastName;

    public ProfessorDTO(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
