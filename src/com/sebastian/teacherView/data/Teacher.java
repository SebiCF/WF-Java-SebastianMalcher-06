package com.sebastian.teacherView.data;

public class Teacher {

    private int teacher_id;
    private String teacher_name;
    private String teacher_surname;
    private String teacher_email;

    public Teacher(int teacher_id, String teacher_name, String teacher_surname, String teacher_email) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_surname = teacher_surname;
        this.teacher_email = teacher_email;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

//    public void setTeacher_id(int teacher_id) {
//        this.teacher_id = teacher_id;
//    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_surname() {
        return teacher_surname;
    }

    public void setTeacher_surname(String teacher_surname) {
        this.teacher_surname = teacher_surname;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public void setTeacher_email(String teacher_email) {
        this.teacher_email = teacher_email;
    }

    @Override
    public String toString() {
        return "Name='" + teacher_name + '\'' +
                "  Surname='" + teacher_surname + '\'';
    }
}
