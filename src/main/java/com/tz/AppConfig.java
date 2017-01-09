package com.tz;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * project_name : crm02
 * user : xhj224
 * date : 2017/1/9 14:07
 */
@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfig {
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    @Bean
    public ComboPooledDataSource getComboPooledDataSource() {
        LOGGER.debug("getComboPooledDataSource() run...");
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("oracle.jdbc.driver.OracleDriver");
            dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
            dataSource.setUser("jsd1609");
            dataSource.setPassword("jsd1609");
            dataSource.setInitialPoolSize(10);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getLocalSessionFactoryBean() {
        LOGGER.debug("getLocalSessionFactoryBean() run...");
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(this.getComboPooledDataSource());
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        localSessionFactoryBean.setHibernateProperties(props);
        localSessionFactoryBean.setPackagesToScan("com.tz.entity");
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {
        LOGGER.debug("PlatformTransactionManager() run...");
        HibernateTransactionManager htm = new HibernateTransactionManager(sessionFactory);
        return htm;
    }
}
