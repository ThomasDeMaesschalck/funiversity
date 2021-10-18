package com.switchfully.funiversity.webapi.dtos;

public class CourseDTO {

    private final int id;
    private final String name;
    private final int studyPoints;
    private final ProfessorDTO professor;

    public CourseDTO(int id, String name, int studyPoints, ProfessorDTO professor) {
        this.id = id;
        this.name = name;
        this.studyPoints = studyPoints;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudyPoints() {
        return studyPoints;
    }

    public ProfessorDTO getProfessor() {
        return professor;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", studyPoints=" + studyPoints +
                ", professor=" + professor +
                '}';
    }
}
