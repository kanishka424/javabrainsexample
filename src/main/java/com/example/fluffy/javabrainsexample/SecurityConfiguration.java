package com.example.fluffy.javabrainsexample;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity//this tells that this is a web security configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {//this calss must be extended inorder to get the needed configure()
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{// here otherwise it will give us the default authentication {//this takes AuthenticationManagerBuilder as an argument we will cahnge this
               auth.inMemoryAuthentication()//this says that we are using in memory authentication
                        .withUser("blah")//here we are creating an inmemory user
                        .password("blah")
                        .roles("USER")
                        .and()
                       .withUser("blah")//here we are creating an inmemory user
                       .password("blah")
                       .roles("USER");


    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){//spring security has enforce the user to encode the pw
        return NoOpPasswordEncoder.getInstance();//this means the pw won't be encoded,this only for example
    }

}

//since we changed configure() now  spring  security won't create the default user
//Basically we are changing defult s.s behaviour with configure()
//with and() we haave appended more users
