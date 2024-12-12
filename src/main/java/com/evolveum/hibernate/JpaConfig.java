package com.evolveum.hibernate;

import com.evolveum.hibernate.util.EntityStateInterceptor;
import org.hibernate.cfg.*;
import org.hibernate.dialect.SQLServerDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(vendorAdapter);

        // While dataSource == dataSourceFactory.getDataSource(), we're using dataSource as
        // parameter to assure, that Spring already called the factory method. Explicit is good.
        bean.setDataSource(dataSource);

        Properties props = new Properties();
        props.setProperty(JdbcSettings.DIALECT, SQLServerDialect.class.getName());
        props.setProperty(SchemaToolingSettings.HBM2DDL_AUTO, "update");
        props.setProperty("hibernate.id.new_generator_mappings", "true");
        props.setProperty(BatchSettings.STATEMENT_BATCH_SIZE, "20");
        props.setProperty(ValidationSettings.JAKARTA_VALIDATION_MODE, "none");
        props.setProperty(TransactionSettings.TRANSACTION_COORDINATOR_STRATEGY, "jdbc");
        props.setProperty("hibernate.hql.bulk_id_strategy",
                "org.hibernate.hql.spi.id.inline.InlineIdsOrClauseBulkIdStrategy");
        props.setProperty(SessionEventSettings.INTERCEPTOR, EntityStateInterceptor.class.getName());

        bean.setJpaProperties(props);
        bean.setPackagesToScan("com.evolveum.hibernate.entity");

        return bean;
    }
}