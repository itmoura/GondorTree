package com.gondortree.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author itmoura
 */
@Entity
@Table(name="family") // RELACIONANDO COM A TABELA DO BANCO
public class Blazon implements Serializable {    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="image")
    private String image;
    
    @ManyToOne
    @JoinColumn(name = "create_id",columnDefinition="int")
    private Member createId;
    
    public Blazon(){
        this(new Long(0));
    }
    
    public Blazon(Long id){
        this.id = id;
        this.name = "";
        this.image = "logo.jpg";
        this.createId = null;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }   

    public Member getCreateId() {
        return createId;
    }

    public void setCreateId(Member createId) {
        this.createId = createId;
    }
    
    
}
