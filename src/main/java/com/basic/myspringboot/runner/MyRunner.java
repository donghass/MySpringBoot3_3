package com.basic.myspringboot.runner;

import com.basic.myspringboot.config.CustomVO;
import com.basic.myspringboot.property.MyBootProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    private String name;
    @Value("${myboot.age}")
    private int age;

    @Autowired
    Environment environment;

    @Autowired
    MyBootProperties properties;

    @Autowired
    private CustomVO customVO;

    Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        System.out.println("Program Argument bar "+args.containsOption("bar"));
        System.out.println("VM Argument foo "+args.containsOption("foo"));

        //Annominus
        args.getOptionNames().forEach(new Consumer<String>() {
            @Override
            public void accept(String name){
                System.out.println(name);
            }
        });

        //argument 목록 출력
        //forEach(Consumer) Consumer의 추상메서드는 void accept(T)
        args.getOptionNames().forEach(name-> System.out.println(name));
        //Method Reference
        args.getOptionNames().forEach(System.out::println);

        if(args.containsOption("local.server.port")){
            args.getOptionValues("local.server.port").forEach(System.out::println);
        }

        System.out.println("***************");
        System.out.println(name);
        System.out.println(age);

        System.out.println("environment getProperty fullName = "+environment.getProperty("myboot.fullName"));
        System.out.println("environment getProperty port = "+environment.getProperty("local.server.port"));

        System.out.println("MyBootProperties getName = "+properties.getName());

        System.out.println("현재 활성화 된 CustomVO = " + customVO);
        logger.info("현재 활성화 된 CustomVO = info {}", customVO);
        logger.debug("현재 활성화 된 CustomVO = debug {}", customVO);


        logger.info("Logger 구현객체명 = {}",logger.getClass().getName());
        logger.info("-----info-----");
        logger.info(properties.getName());
        logger.debug("-----debug-----");
        logger.debug(properties.getName());

    }
}
