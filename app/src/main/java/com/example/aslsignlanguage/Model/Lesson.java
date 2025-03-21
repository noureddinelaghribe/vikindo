package com.example.aslsignlanguage.Model;

public class Lesson {

    int id;
    int unit;
    String titleLesson;

    public Lesson(int id, int unit, String titleLesson) {
        this.id = id;
        this.unit = unit;
        this.titleLesson = titleLesson;
    }

    public Lesson() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public String getTitleLesson() {
        return titleLesson;
    }

    public void setTitleLesson(String titleLesson) {
        this.titleLesson = titleLesson;
    }
}
