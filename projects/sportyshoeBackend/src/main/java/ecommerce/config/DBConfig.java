package ecommerce.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import ecommerce.model.Category;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class DBConfig {

	@Bean(name = "dataSource")

	public DataSource getH2DataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/test5");
		dataSource.setUsername("IIIT");
		dataSource.setPassword("123456");

		System.out.println("-----------DataSource Object is created-------------");

		return dataSource;

	}

	@Bean(name = "sessionFactory")

	public SessionFactory getSessionFactory() {

		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.hdm2ddl.auto", "update");
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		LocalSessionFactoryBuilder localFactory = new LocalSessionFactoryBuilder(getH2DataSource());

		localFactory.addProperties(hibernateProp);

		localFactory.addAnnotatedClass(Category.class);

		System.out.println("-----------SessionFactory Object is Created----------");

		return localFactory.buildSessionFactory();

	}

	@Bean(name = "txManager")

	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {

		System.out.println("--------Transaction Manager Object Created------");
		return new HibernateTransactionManager(sessionFactory);
	}
}
