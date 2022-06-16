package io.simpolor.multiconfig.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = {"io.simpolor.multiconfig.repository.secondary"},
        entityManagerFactoryRef = "secondaryEntityManager",
        transactionManagerRef = "secondaryTransactionManager"
)
@RequiredArgsConstructor
public class SecondaryDatabaseConfig {

    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManager() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondaryDataSource());
        em.setPackagesToScan(new String[] { "io.simpolor.multiconfig.repository.entity" });
        em.setJpaPropertyMap(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "application.datasources.secondary")
    public HikariConfig secondaryConfiguration() {

        return new HikariConfig();
    }

    @Bean
    public DataSource secondaryDataSource() {

        return new HikariDataSource(secondaryConfiguration());
    }

    @Bean
    public PlatformTransactionManager secondaryTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(secondaryEntityManager().getObject());

        return transactionManager;
    }
}
