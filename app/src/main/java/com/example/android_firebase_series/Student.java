package com.example.android_firebase_series;

public class Student {
    String id;
    String name;
    String college;
    long age;

    public Student () {

    }

    public Student(String id, String name, String college, long age) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.age = age;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
