package com.gondortree.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author itmoura
 */
@Entity
@Table(name="members") // RELACIONANDO COM A TABELA DO BANCO
public class Member implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="email")
    private String email;
    
    @Column(name="password")
    private String password;
    
    @Column(name="last_login", columnDefinition="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date last_login;
    
    @Column(name="name")
    private String name;
    
    @Column(name="description", columnDefinition="text")
    private String description;
    
    @Column(name="photo")
    private String photo;
    
    @Column(name="cover_photo")
    private String coverPhoto;
    
    @ManyToOne
    @JoinColumn(name = "members_mae_id", columnDefinition="int")
    private Member memberMae;
    
    @ManyToOne
    @JoinColumn(name = "members_pai_id", columnDefinition="int")
    private Member memberPai;
    
    @ManyToOne
    @JoinColumn(name = "family_id", columnDefinition="int")
    private Blazon familyId;
    
    public Member(){
        this(new Long(0));
    }
    
    public Member(Long id){
        this.id = id;
        this.name = "";
        this.description = "";
        this.coverPhoto = "gondor.jpg";
        this.photo = "user.png";
        this.email = "";
        this.password = "";
        this.last_login = new Date();
        this.memberMae = null;
        this.memberPai = null;
        this.familyId = null;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    } 

    public Member getMemberMae() {
        return memberMae;
    }

    public void setMemberMae(Member memberMae) {
        this.memberMae = memberMae;
    }

    public Member getMemberPai() {
        return memberPai;
    }

    public void setMemberPai(Member memberPai) {
        this.memberPai = memberPai;
    }

    public Blazon getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Blazon familyId) {
        this.familyId = familyId;
    }
    
    
}
