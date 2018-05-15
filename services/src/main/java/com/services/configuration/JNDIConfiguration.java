package com.services.configuration;

import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class JNDIConfiguration {
	
	private final String PACKAGES_TO_SCAN = "com.services.model";
	private final String JDBC_LOCAL = "java:comp/env/jdbc/local";
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter, Properties properties) throws NamingException {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(dataSource);
        em.setPackagesToScan(PACKAGES_TO_SCAN);
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(properties);
         
        return em;
    }
	
	@Bean
	@Profile(EnvironmentType.LOCAL)
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(JDBC_LOCAL);
    }
	
	@Bean
	@Profile(JpaVendorType.HIBERNATE)
    public JpaVendorAdapter jpaVendorType() throws NamingException {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		return vendorAdapter;
    }
 
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }
    
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean
    @Profile(EnvironmentType.LOCAL)
    public Properties properties() {
    	Properties properties = new Properties();
    	
    	properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", DialectEnum.H2.getClassName());
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.physical_naming_strategy", CustomNamingStrategy.class.getName());
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        properties.setProperty("hibernate.connection.useUnicode", "true");
 
        return properties;
     }
	
}
