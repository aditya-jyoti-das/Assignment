package com.example.ASSIGNMENT_RBAC_BOTMAKERS.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.repository",
        entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager")
public class UserDbConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource userDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("spring.userdb.datasource.url"));
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.userdb.datasource.driver-class-name")));
        dataSource.setUsername(env.getProperty("spring.userdb.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.userdb.datasource.password"));
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSource());
        em.setPackagesToScan(new String[]{"com.example.ASSIGNMENT_RBAC_BOTMAKERS.userdb.entity"});
        em.setPersistenceUnitName("user");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.userdb.jpa.properties.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.userdb.jpa.properties.hibernate.dialect"));
        properties.put("hibernate.show_sql", "true");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }

}
