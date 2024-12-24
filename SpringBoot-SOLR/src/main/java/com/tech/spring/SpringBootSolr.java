package com.tech.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@SpringBootApplication
@EnableSolrRepositories("com.tech.spring.repository")
public class SpringBootSolr{
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSolr.class, args);
	}
}
