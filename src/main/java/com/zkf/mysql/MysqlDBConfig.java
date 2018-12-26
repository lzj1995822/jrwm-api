
package com.zkf.mysql;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;




@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableJpaRepositories(
		basePackages = "com.zkf.mysql.repository",
		entityManagerFactoryRef = "appEntityManager", 
		transactionManagerRef = "appTransactionManager")
public class MysqlDBConfig {
	
	@Autowired
	private Environment env;

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean appEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(appDataSource());
		em.setPersistenceUnitName("app_persistence");
		em.setPackagesToScan(new String[] { "com.zkf.mysql.entity" });
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<String, Object>();
//		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean
	//@ConfigurationProperties(prefix="datasource.primary")
	public DataSource appDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager appTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(appEntityManager().getObject());
		return transactionManager;
	}

}
