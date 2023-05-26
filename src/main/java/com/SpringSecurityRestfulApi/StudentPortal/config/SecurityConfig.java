package com.SpringSecurityRestfulApi.StudentPortal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/studentDetails/getAllStudents").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/studentDetails/getById").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/studentDetails/addStudent").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/studentDetails/updateById").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/studentDetails/deleteById").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/studentDetails/{id}/enroll").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/studentDetails/{id}/drop").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/courseDetails/getAllCourses").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/courseDetails/getCourseById").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/courseDetails/addCourse").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/courseDetails/updateCourse").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/courseDetails/deleteCourse").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
