package com.gondortree.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author itmoura
 */
@Entity
@Table(name="members") // RELACIONANDO COM A TABELA DO BANCO
public class Member implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="id",columnDefinition="int")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="description", columnDefinition="text")
    private String description;
    
    @Column(name="photo")
    private String photo;
    
    @Column(name="cover_photo")
    private String coverPhoto;
    
    public Member(){
        this(new Long(0));
    }
    
    public Member(Long id){
        this.id = id;
        this.name = "Nome Default";
        this.description = "";
        this.coverPhoto = "";
        this.photo = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

   
    
    
    
}
