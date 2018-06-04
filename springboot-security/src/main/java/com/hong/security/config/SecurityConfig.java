package com.hong.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by hong on 2017/6/13.
 */
/** 可以创建一个名为 springSecurityFilterChain 的Filter. **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 自定义配置.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Spring Security是怎么知道我们希望支持表单形式的验证呢？原因是我们的SecurityConfig类继承了WebSecurityConfigurerAdapter，
         * 而其在 configure(HttpSecurity http)中提供了一些默认的配置。
         *
         * **/

        // 设置url访问权限,映射路径等等.
        http.authorizeRequests() // 该配置是要求应用中所有url的访问都需要进行验证.
            .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll()  // 虽都可以访问
            .antMatchers("/users/**").hasRole("USER")   // 需要相应的角色才能访问
            .antMatchers("/admins/**").hasRole("ADMIN")   // 需要相应的角色才能访问
            .and() //Java配置中的and()方法类似于xml配置中的结束标签，and()方法返回的对象还是HttpSecurity，方便我们继续对HttpSecurity进行配置。
            .formLogin()   //基于 Form 表单登录验证
            .loginPage("/login")  // 自定义登录界面
            .failureUrl("/login-error")  // 自定义登录失败页面
            //在没有配置登录表单的页面URL时，Spring Security就会自动生成一个登录页面(访问地址 /login)，并且对用户的登录操作进行处理。
            .and()
            .logout() //配置登出
            .logoutSuccessUrl("/login") //成功登出后，重定向到登录页.
            .and()
            .exceptionHandling().accessDeniedPage("/403");//处理没有访问权限异常，拒绝访问就重定向到 403 页面

    }

    /**
     * 用户信息服务.
     */
    @Bean
    public UserDetailsService userDetailsService() {
         /** 用户的认证信息是使用 InMemoryUserDetailsManager 来存储在内存中；**/
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager(); // 在内存中存放用户信息
        manager.createUser(User.withUsername("hong").password("123456").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("123456").roles("USER","ADMIN").build());
        return manager;
    }

    /**
     * 认证信息管理.
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
