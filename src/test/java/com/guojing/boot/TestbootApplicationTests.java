package com.guojing.boot;

import com.guojing.boot.entity.TbMember;
import com.guojing.boot.interf.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestbootApplicationTests {

	@Resource
	UserRepository userRepository;

	@Test
	public  void getByName(){
		TbMember tbMember = userRepository.findUser("hch114");
		System.out.println(tbMember.getCustomerId());
	}

	@Test
	public  void getByLongName(){
		TbMember tbMember = userRepository.findByLoginName("hch114");
		System.out.println(tbMember.getCustomerId());
	}

	@Test
	public void contextLoads() {
	}

}
