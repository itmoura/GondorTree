package com.gondortree.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;
/**
 *
 * @author Ítalo Moura
 */
@Entity
@Table(name = "members_has_family") // RELACIONANDO COM A TABELA DO BANCO
public class Family implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "members_id", columnDefinition = "int")
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "blazon_id", columnDefinition = "int")
    private Blazon familyId;

    @Column(name = "members_function", columnDefinition = "tinyint")
    private Integer membersFunction;

    public static final Integer FUNCTION_ADMIN = new Integer(1);
    public static final Integer FUNCTION_USER = new Integer(2);

    public Family() {
        this(new Long(0));
    }

    public Family(Long id) {
        this.id = id;
        this.memberId = null;
        this.familyId = null;
        this.membersFunction = FUNCTION_ADMIN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMemberId() {
        return memberId;
    }

    public void setMemberId(Member memberId) {
        this.memberId = memberId;
    }

    public Blazon getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Blazon familyId) {
        this.familyId = familyId;
    }

    public Integer getMembersFunction() {
        return membersFunction;
    }

    public void setMembersFunction(Integer membersFunction) {
        this.membersFunction = membersFunction;
    }

    /* MAP TYPE */
    public String getMembersFunctionString() {
        String ret = "desconhecido";
        if (this.membersFunction != null) {
            if (this.membersFunction.equals(FUNCTION_ADMIN)) {
                ret = "Administrador";
            } else if (this.membersFunction.equals(FUNCTION_USER)) {
                ret = "Membro";
            }
        }
        return ret;
    }

    public static Map<Integer, String> getMembersFunctionMap() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        map.put(FUNCTION_ADMIN, "Administrador");
        map.put(FUNCTION_USER, "Membro");
        return map;
    }

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("id", this.id);
        obj.put("memberName", this.memberId.getName());
        obj.put("member", this.memberId.getId());
        obj.put("familyName", this.familyId.getName());
        obj.put("family", this.familyId.getId());
        obj.put("function", this.membersFunction);
        return obj;
    }
    
}
