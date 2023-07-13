package io.cursos.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.cursos.domain")
@EnableJpaRepositories("io.cursos.repos")
@EnableTransactionManagement
public class DomainConfig {
}
