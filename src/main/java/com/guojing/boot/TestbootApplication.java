package com.guojing.boot;

import com.guojing.boot.entity.BookBean;
import com.guojing.boot.entity.Person;
import com.guojing.boot.entity.TbMember;
import com.guojing.boot.inte.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootApplication
public class TestbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestbootApplication.class, args);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(TestbootApplication.class);
//		//修改Banner的模式为OFF
//		builder.bannerMode(Banner.Mode.OFF).run(args);
	}



}
