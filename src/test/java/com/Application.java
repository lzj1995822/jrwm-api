package com;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.jet.zkf.job","com.jet.zkf.service","com.jet.zkf.mysql"})
public class Application {
}
