package com.guojing.boot.interf;

import com.guojing.boot.entity.TbMember;
import com.guojing.boot.entity.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<TbUser, Long> {

    TbUser findByLoginName(String loginName);

    @Query("from TbUser u where u.loginName=:loginName")
    TbUser findUser(@Param("loginName") String loginName);

}