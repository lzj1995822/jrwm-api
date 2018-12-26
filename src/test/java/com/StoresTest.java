package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)// 指定spring-boot的启动类
@Configuration
@ComponentScan(basePackages = {"com.zkf.mysql"})
public class StoresTest {

//    @Resource(name = "storesRepo")
//    private StoresRepo storesRepo;
//
    @Test
    public void createUser() {
//        Stores stores = new Stores();
//        stores.setName("测试门店");
//        stores.setAddress("青秀城");
//        stores.setCode("CDFG");
//        stores.setProvinces("浙江省");
//        stores.setCity("杭州市");
//        stores.setCounty("萧山区");
//        stores.setStartTime(new Date());
//        stores.setEndTime(new Date());
//        stores.setScope("全部");
//        stores.setBrandScope("宝马");
//        stores.setContact("蒋建才");
//        stores.setPhone("12328328376734");
//        stores.setDescribe("测试描述");
//        storesRepo.save(stores);
    }

}
