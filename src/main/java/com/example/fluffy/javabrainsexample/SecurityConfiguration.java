package com.example.fluffy.javabrainsexample;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity//this tells that this is a web security configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {//this calss must be extended inorder to get the needed configure()


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{// here otherwise it will give us the default authentication {//this takes AuthenticationManagerBuilder as an argument we will cahnge this
               auth.inMemoryAuthentication()                                     //this says that we are using in memory authentication
                        .withUser("blah")                               //here we are creating an inmemory user
                        .password("blah")
                        .roles("USER")
                        .and()
                        .withUser("foo")                    //here we are creating an inmemory user
                        .password("foo")
                        .roles("ADMIN");


    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){//spring security has enforce the user to encode the pw
        return NoOpPasswordEncoder.getInstance();//this means the pw won't be encoded,this only for example
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()                //this http object of type HttpSecurity can specify permisson and path
                .antMatchers("/admin").hasRole("ADMIN")           //check hasAnyRole  //we can;'t specify every path so we use wildcards /**-all path current level and nested levels
                .antMatchers("/user").hasAnyRole("USER","ADMIN") //since s.s doesn't know who is supirior we need to give them both access
                .antMatchers("/","static/css","static/js").permitAll()//this says no authorization needed
                 .and().formLogin();           //we ended a part of it now a new one,now the type of login we choose form based here
    }

}

//since we changed configure() now  spring  security won't create the default user
//Basically we are changing defult s.s behaviour with configure()
//with and() we haave appended more users


//Important _here we have a logout page also created by default just type http://localhost:8080/logout

//when authorizationing go from most restrective to least see above