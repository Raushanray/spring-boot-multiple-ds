package com.lcwr.db.api.postgresql.config;

/**
 * UserDBConfig is a Configuration class of postgresql database.
 */

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = {"com.lcwr.db.api.postgresql.repository"},
        transactionManagerRef = "transactionManager"
)
public class UserDBConfig {

    // Step 1: Define a configuration class for the User database
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.user.datasource")
    public DataSource dataSource() {
        // Step 2: Configure the primary data source for the User database
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryBean")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("dataSource") DataSource dataSource) {

        // Step 3: Configure the EntityManagerFactory for the User database
        return builder.dataSource(dataSource)
                .packages("com.lcwr.db.api.postgresql.entities")// Step 4: Specify the package containing the entities
                .build();

    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactoryBean") EntityManagerFactory entityManagerFactory) {
        // Step 5: Configure the transaction manager for the User database
        return new JpaTransactionManager(entityManagerFactory);
    }
}
