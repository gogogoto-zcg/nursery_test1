package com.example.nursery_test1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "class")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Class {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private int studentmax;
    private int studentnum;
    private int studentneed;

//    @OneToMany
//    @JsonIgnoreProperties(value = "aClass")
//    private List<Activity> activityList;

    @ManyToOne
    @JoinColumn(name = "tid")
    private Subclass type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Subclass getType() {
        return type;
    }

    public void setType(Subclass type) {
        this.type = type;
    }

    public int getStudentmax() {
        return studentmax;
    }

    public void setStudentmax(int studentmax) {
        this.studentmax = studentmax;
    }

    public int getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(int studentnum) {
        this.studentnum = studentnum;
    }

    public int getStudentneed() {
        return studentneed;
    }

    public void setStudentneed(int studentneed) {
        this.studentneed = studentneed;
    }




//    @Override
//    public String toString() {
//        return "Class{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", studentmax=" + studentmax +
//                ", studentnum=" + studentnum +
//                ", studentneed=" + studentneed +
//                ", type=" + type +
//                '}';
//    }
}
