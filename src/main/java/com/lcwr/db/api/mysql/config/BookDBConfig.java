package com.lcwr.db.api.mysql.config;
/**
 * Configuration class of MySql database.
 */

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "bookEntityManagerFactoryBean",
        basePackages = {"com.lcwr.db.api.mysql.repository"},
        transactionManagerRef = "bookTransactionManager"
)
public class BookDBConfig {

    // Step 1: Define a configuration class for the Book database
    @Bean(name = "bookDataSource")
    @ConfigurationProperties(prefix = "spring.book.datasource")
    public DataSource dataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean(name = "bookEntityManagerFactoryBean")

    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("bookDataSource") DataSource dataSource) {

        // Step 3: Configure the EntityManagerFactory for the Book database
        return builder.dataSource(dataSource).packages("com.lcwr.db.api.mysql.entities")// Step 4: Specify the package containing the entities
                .build();

    }

    @Bean(name = "bookTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("bookEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory
    ) {
        // Step 5: Configure the transaction manager for the Book database
        return new JpaTransactionManager(entityManagerFactory);
    }
}
