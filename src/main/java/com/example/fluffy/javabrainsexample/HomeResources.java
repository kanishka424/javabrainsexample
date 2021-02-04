package com.example.fluffy.javabrainsexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResources {
    @GetMapping("/")
    public String home(){
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return("<h1>Welcome Users<h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin<h1>");
    }

}


//NOTE
//1.These are the resources we have for different authorization levels
//  "/"-anyone,"/user"-user +admin,"/admin"-admins  we do this by using an object of type HttpSecurity
//2.lets us configure the paths and users that can access each path
//getting hold of httpsecurity object is very similar to getting authenticationobject(AuthenticationManagerBuilder)
//by extending WebSecurityConfigurerAdapter