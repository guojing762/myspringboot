package com.guojing.boot;

import com.guojing.boot.entity.TbMember;
import com.guojing.boot.interf.MemberRepository;
import com.guojing.boot.interf.second.PaymentRepository;
import com.guojing.boot.interf.primary.SysRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestbootApplicationTests {

	@Resource
    MemberRepository memberRepository;

	@Autowired
	private SysRepository sysRepository;
	@Autowired
	private PaymentRepository paymentRepository;

	@Test
	public  void getByName(){
		TbMember tbMember = memberRepository.findUser("hch114");
		System.out.println(tbMember.getCustomerId());
	}

	@Test
	public  void getByLongName(){
		TbMember tbMember = memberRepository.findByLoginName("hch114");
		System.out.println(tbMember.getCustomerId());
	}

	@Test
	public  void testMoreDataSource(){
		//主
		Long count = sysRepository.count();
		System.out.println(count);
		//从
		Long sum = paymentRepository.count();
		System.out.println(sum);
	}

	@Test
	public void contextLoads() {
	}

}
