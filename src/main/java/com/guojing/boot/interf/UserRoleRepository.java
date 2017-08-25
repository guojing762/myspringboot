package com.guojing.boot.interf;

import com.guojing.boot.entity.TbUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<TbUserRole, Long> {

    @Query("from TbUserRole u where u.user.loginName=:loginName")
    List<TbUserRole> findUserId(@Param("loginName") String loginName);

}