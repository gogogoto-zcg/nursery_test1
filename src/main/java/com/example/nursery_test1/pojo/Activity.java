package com.example.nursery_test1.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "activity")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Activity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int didi;
    private int dada;
    private String introduce;
    private Date uploadTime;
    @ManyToOne
    @JoinColumn(name="uid")
    private User user;

    @ManyToOne
    @JoinColumn(name="cid")
//    @JsonIgnoreProperties(value = "activityList")
    private Class aClass;

    @OneToMany(mappedBy = "activity")
    @JsonIgnoreProperties(value = "activity")
    private List<ActivityResource> activityResourceList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public List<ActivityResource> getActivityResourceList() {
        return activityResourceList;
    }

    public void setActivityResourceList(List<ActivityResource> activityResourceList) {
        this.activityResourceList = activityResourceList;
    }
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", introduce='" + introduce + '\'' +
                ", uploadTime=" + uploadTime +
                ", user=" + user +
                ", aClass=" + aClass +
                '}';
    }
}
