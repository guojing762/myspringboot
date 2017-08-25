package com.guojing.boot.config;

import com.guojing.boot.service.UserDetailsAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsAdminServiceImpl userDetailsAdminService;

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices("remember-me-key", (UserDetailsService) userDetailsAdminService);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .sessionManagement()
//                .maximumSessions(1).expiredUrl("/jsp/login.jsp?register=1");
//        http.headers().frameOptions().disable().csrf().disable().authorizeRequests()
//                .antMatchers("/shop/*").permitAll()
//                .antMatchers("/admin/cms/**/*_pre_view.do").permitAll()
//                .antMatchers("/admin/cms/**/*_cache_config.do").permitAll()
//                .antMatchers("/admin/erpstock/stockSync.do").permitAll()
//                .antMatchers("/admin/erpstock/stockConfirm.do").permitAll()
//                .antMatchers("/admin/erpstock/stockReq.do").permitAll()
//                .antMatchers("/admin/marketing/wechat/weChatRequest.do").permitAll()
//                .antMatchers("/admin/marketing/wechat/weChatConfirm.do").permitAll()
//                .antMatchers("/admin/pingan/query/**.do").permitAll()
//                .antMatchers("/admin/user/findpasswords.do").permitAll()
//                .antMatchers("/admin/forScm/hasMore.do").permitAll()
//                .antMatchers("/admin/customer/order/fetch_pics_by_mtl.do").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("manager").and().formLogin().loginPage("/jsp/login.jsp").loginProcessingUrl("/authenticate").failureUrl("/fail.do").defaultSuccessUrl("/jsp/login_success.jsp")
//                .and().logout().logoutUrl("/logout");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //设置拦截规则
                .antMatchers("/")
                .permitAll()
//                .antMatchers("/index/**").hasIpAddress("192.168.52.188")
                .anyRequest()
                .authenticated()
                .and()
                //开启默认登录页面
                .formLogin()
                //默认登录页面
                .loginPage("/login")
                //默认登录成功跳转页面
                .defaultSuccessUrl("/chat")
//                .failureUrl("/login?error")
                .permitAll()
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .and()
                //设置注销
                .logout()
//                .logoutUrl("/custom-logout")
                .permitAll();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("lenve").password("111").roles("USER")
//                .and()
//                .withUser("sang").password("222").roles("USER");
//    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.eraseCredentials(true)
                .userDetailsService(userDetailsAdminService)
                .passwordEncoder(passwordEncoder());
    	*/
        ReflectionSaltSource rss = new ReflectionSaltSource();
        rss.setUserPropertyToUse("salt");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setSaltSource(rss);
        provider.setUserDetailsService(userDetailsAdminService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        //auth.authenticationProvider(provider);
        auth.eraseCredentials(true).authenticationProvider(provider);
   /*     auth.eraseCredentials(true)
                .userDetailsService(shopSecurityService)
                .passwordEncoder(passwordEncoder());*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //设置不拦截规则
        web.ignoring().antMatchers("/resources/static/**");
    }
}