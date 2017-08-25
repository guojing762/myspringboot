package com.guojing.boot.entity;

import javax.persistence.*;

/**
 * Created by xingsen on 15/4/23.
 */
@Entity
@Table(name = "tb_role")
public class TbRole{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cnName;
    private String namePy;
    private String nameSpy;
    private String cnNamePy;
    private String cnNameSpy;
    private Integer roleType;
    private String cnNameWbc;

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

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getNameSpy() {
        return nameSpy;
    }

    public void setNameSpy(String nameSpy) {
        this.nameSpy = nameSpy;
    }

    public String getCnNamePy() {
        return cnNamePy;
    }

    public void setCnNamePy(String cnNamePy) {
        this.cnNamePy = cnNamePy;
    }

    public String getCnNameSpy() {
        return cnNameSpy;
    }

    public void setCnNameSpy(String cnNameSpy) {
        this.cnNameSpy = cnNameSpy;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getCnNameWbc() {
        return cnNameWbc;
    }

    public void setCnNameWbc(String cnNameWbc) {
        this.cnNameWbc = cnNameWbc;
    }
}
