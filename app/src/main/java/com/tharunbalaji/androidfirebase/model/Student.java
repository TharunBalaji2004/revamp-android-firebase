package com.tharunbalaji.androidfirebase.model;

public class Student {
    private String id;
    private String studentName;
    private long age;
    private String collegeName;

    public Student() {}

    public Student(String id, String studentName, long age, String collegeName) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.collegeName = collegeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String printContents(){
        return this.studentName + " " + this.collegeName + " " + this.age;
    }
}
