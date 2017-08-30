package com.guojing.boot;

import com.guojing.boot.config.WebMVCConfig;
import com.guojing.boot.controller.MainController;
import com.guojing.boot.controller.UserController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", classes = WebMVCConfig.class),
})
public class ApplicationTests2 {
 
	private MockMvc mockMvc;
 
	@Before
    public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new MainController()).build();
	}

	@Test
	public void testView() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/main/1"))
				.andExpect(MockMvcResultMatchers.view().name("main/test"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("user"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();

		Assert.assertNotNull(result.getModelAndView().getModel().get("user"));
	}

}