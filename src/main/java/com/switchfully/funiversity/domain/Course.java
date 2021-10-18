package com.switchfully.funiversity.domain;

public class Course {

    private final int id;
    private final String name;
    private int studyPoints;
    private final Professor professor;

    public Course(int id, String name, int studyPoints, Professor professor) {
        this.id = id;
        this.name = name;
        setStudyPoints(studyPoints);
        this.professor = professor;
    }

    private void setStudyPoints(int studyPoints) {
        if (studyPoints < 1 || studyPoints > 6) {
            throw new IllegalArgumentException("Study points must be 1 to 6 and not " + studyPoints);
        }
        this.studyPoints = studyPoints;
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

    public Professor getProfessor() {
        return professor;
    }
}
