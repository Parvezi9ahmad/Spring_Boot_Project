package com.springboot.blog.utils;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.springboot.blog.security.CutomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderGenerator {
    public static void main(String[] args){
        Logger logger= LoggerFactory.getLogger(CutomUserDetailsService.class);
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("password"));
        logger.info("Test for run"+passwordEncoder.encode("password"));
    }
}
