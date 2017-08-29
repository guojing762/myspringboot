package com.guojing.boot.interf.primary;

import com.guojing.boot.entity.primary.TbSysSetting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface SysRepository extends JpaRepository<TbSysSetting, Long> {

}
