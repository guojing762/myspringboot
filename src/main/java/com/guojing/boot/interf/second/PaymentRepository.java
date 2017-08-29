package com.guojing.boot.interf.second;

import com.guojing.boot.entity.second.TbPaymentRoute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/8/29.
 */
public interface PaymentRepository extends JpaRepository<TbPaymentRoute, Long> {

}
