package com.gondortree.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="testimony") // RELACIONANDO COM A TABELA DO BANCO
public class Testimony implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="description", columnDefinition="text")
    private String description;
    
    @Column(name="date_post", columnDefinition="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date_post;
    
    @ManyToOne
    @JoinColumn(name = "members_id",columnDefinition="int")
    private Member members_id;
    
    public Testimony() {
        this(new Long(0));
    }
	
    public Testimony(Long id) {
        this.id = id;
        this.title = "";
        this.description = "";
        this.date_post = new Date();
        this.members_id = new Member();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_post() {
        return date_post;
    }

    public void setDate_post(Date date_post) {
        this.date_post = date_post;
    }

    public Member getMemberId() {
        return members_id;
    }

    public void setMemberId(Member memberId) {
        this.members_id = memberId;
    }
    
    

}
