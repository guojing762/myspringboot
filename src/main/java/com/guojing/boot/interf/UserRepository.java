package com.guojing.boot.interf;

import com.guojing.boot.entity.TbMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<TbMember, Long> {

    TbMember findByLoginName(String loginName);

    @Query("from TbMember u where u.loginName=:loginName")
    TbMember findUser(@Param("loginName") String loginName);

}