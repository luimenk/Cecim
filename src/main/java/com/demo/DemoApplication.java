package com.demo;

//import com.dropbox.core.DbxException;
//import com.dropbox.core.DbxRequestConfig;
//import com.dropbox.core.http.StandardHttpRequestor;
//import com.dropbox.core.v2.DbxClientV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        /*ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        System.out.println("Los beans proporcionados: ");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/
    }

//    @Bean("dropboxClient")
//    public DbxClientV2 dropboxClient() throws DbxException {
//        String ACCESS_TOKEN = "sl.A2TolxeGbKZU5D_ZNYBh3-_bhUfXnL1Fjy2btXtRR9vUTCe9_I4c61o7udd6pUIJZtf4LJQDxkEFSY0rXa6jaSOw3ZE_jQwrSNAPkz92J0bkl8aG-_mDq-koQ7INjXknz27neD0_ck4s";
//        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial");
//        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
//        return client;
//    }
}