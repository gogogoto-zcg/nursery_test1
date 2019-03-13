package com.example.nursery_test1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "register")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Register {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @ManyToOne
    @JoinColumn(name = "sid")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "subid")
    private Subclass type;

    private Date enrolltime;
    private Date paytime;
    private String ispay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Subclass getType() {
        return type;
    }

    public void setType(Subclass type) {
        this.type = type;
    }

    public Date getEnrolltime() {
        return enrolltime;
    }

    public void setEnrolltime(Date enrolltime) {
        this.enrolltime = enrolltime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getIspay() {
        return ispay;
    }

    public void setIspay(String ispay) {
        this.ispay = ispay;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", student=" + student +
                ", user=" + user +
                ", type=" + type +
                ", enrolltime=" + enrolltime +
                ", paytime=" + paytime +
                ", ispay='" + ispay + '\'' +
                '}';
    }
}
