package com.sportit.configuration;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.net.InetAddress;

/**
 * Cassandra configuration.
 * Generated manually instead of using spring-data-cassandra due to current missing support of datastax driver 3.0.
 */
@Configuration
@PropertySource(value = {"classpath:cassandra-config.properties"})
public class CassandraConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraConfiguration.class);

    @Inject
    private Environment environment;

    @Bean
    public Cluster cluster(){

        LOGGER.info("Starting Cassandra cluster...");

        String clusterAddress= environment.getProperty("cassandra.cluster.address");
        Cluster cluster = Cluster.builder().addContactPoint(clusterAddress).build();

        LOGGER.info("Cassandra cluster connected to {}", cluster.getClusterName());
        return cluster;
    }

    @Bean
    public Session session(){

        LOGGER.info("Starting Cassandra session...");

        String keyspaceName = environment.getProperty("cassandra.session.keyspace");
        Session session = cluster().connect(keyspaceName);

        LOGGER.info("Cassandra session started! Keyspace : {}", session.getLoggedKeyspace());

        return session;
    }
}
