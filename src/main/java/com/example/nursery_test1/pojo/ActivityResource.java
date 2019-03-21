package com.example.nursery_test1.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "activityResource")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class ActivityResource {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fileType;
    private String file_url;

    @ManyToOne()
    @JoinColumn(name = "aid")
    //次注解防止无限递归
//    @JsonBackReference
//    @JsonIgnoreProperties(value = { "users" })
    private Activity activity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "ActivityResource{" +
                "id=" + id +
                ", fileType='" + fileType + '\'' +
                ", file_url='" + file_url + '\'' +
                ", activity=" + activity +
                '}';
    }
}
