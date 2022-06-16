package io.simpolor.masterslave.config;

import com.zaxxer.hikari.HikariDataSource;
import io.simpolor.masterslave.config.datasource.DataSourceType;
import io.simpolor.masterslave.config.datasource.ReplicationRoutingDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableJpaRepositories(
        basePackages = "io.simpolor.masterslave.repository",
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
@RequiredArgsConstructor
public class RoutingDataSourceConfig {

    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    /*@Bean("masterConfiguration")
    @ConfigurationProperties(prefix = "application.datasources.master")
    public HikariConfig masterConfiguration() {
        return new HikariConfig();
    }

    @Bean("masterDataSource")
    public DataSource masterDataSource() {
        return new HikariDataSource(masterConfiguration());
    }

    @Bean("slaveConfiguration")
    @ConfigurationProperties(prefix = "application.datasources.slave")
    public HikariConfig slaveConfiguration() {
        return new HikariConfig();
    }

    @Bean("slaveDataSource")
    public DataSource slaveDataSource() {
        return new HikariDataSource(slaveConfiguration());
    }*/

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "application.datasources.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "application.datasources.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "routingDataSource")
    @DependsOn({"masterDataSource", "slaveDataSource"})
    public DataSource routingDataSource(@Qualifier("masterDataSource") final DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") final DataSource slaveDataSource) {

        ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.Master, masterDataSource);
        dataSourceMap.put(DataSourceType.Slave, slaveDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    @Primary
    @Bean
    public DataSource dataSource(DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean masterEntityManager(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[] { "io.simpolor.masterslave.repository.entity" });
        em.setJpaPropertyMap(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean
    public PlatformTransactionManager masterTransactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }
}
