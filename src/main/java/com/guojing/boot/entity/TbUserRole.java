package com.guojing.boot.entity;


import javax.persistence.*;

/**
 * Created by xingsen on 15/4/24.
 */
@Entity
@Table(name = "tb_user_role")
public class TbUserRole{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private TbRole role;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private TbUser user;

    public TbRole getRole() {
        return role;
    }

    public void setRole(TbRole role) {
        this.role = role;
    }

    public TbUser getUser() {
        return user;
    }

    public void setUser(TbUser user) {
        this.user = user;
    }
}
