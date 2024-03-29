/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package sample.rs.service;
import java.util.Arrays;

import org.apache.camel.CamelContext;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sample.rs.service.hello1.HelloServiceImpl1;
import sample.rs.service.hello2.HelloServiceImpl2;

@EnableAutoConfiguration
@SpringBootApplication
public class SampleRestApplication {
    @Autowired
    private Bus bus;

    @Autowired
    CamelContext camelContext;
    
    @Autowired
    private HelloServiceImpl1 helloService;
    
    @Autowired
    private HelloServiceImpl2 hello2Service;
    
    
    public static void main(String[] args) {
        SpringApplication.run(SampleRestApplication.class, args);
    }

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Arrays.<Object>asList(helloService, hello2Service));
        endpoint.setAddress("/");
        endpoint.setFeatures(Arrays.asList(createSwagger2Feature()));
        return endpoint.create();
    }
    
    @Bean
    public Swagger2Feature createSwagger2Feature()
    {
    	Swagger2Feature feature = new Swagger2Feature();
    	feature.setTitle("Hello Service");
    	
    	return feature;
    }
/**
    @Bean
    public OpenApiFeature createOpenApiFeature() {
        final OpenApiFeature openApiFeature = new OpenApiFeature();
        openApiFeature.setPrettyPrint(true);
        openApiFeature.setTitle("Spring Boot CXF REST Application");
        openApiFeature.setContactName("The Apache CXF team");
        openApiFeature.setDescription("This sample project demonstrates how to use CXF JAX-RS services"
                + " with Spring Boot. This demo has two JAX-RS class resources being"
                + " deployed in a single JAX-RS endpoint.");
        openApiFeature.setVersion("1.0.0");
        openApiFeature.setSwaggerUiConfig(
            new SwaggerUiConfig()
                .url("/services/helloservice/openapi.json"));
        return openApiFeature;
    }
 **/
}
