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
package sample.rs.service.hello1;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sample.rs.service.HelloService;

@Component
public class HelloServiceImpl1 implements HelloService {
	
	@Autowired
	private CamelContext camelContext;
	
	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private ConsumerTemplate consumerTemplate;	
	
	public String sayHello(String a) {
		if (producerTemplate == null)
		{
			System.out.println("producer template is null");
		}
		
		if (camelContext == null)
		{
			System.out.println("camelContext template is null");
		}
    	Route r  = camelContext.getRoute("TIMER_ROUTE");
    	String route_status = "Route "+ r.getId() + " is running";
    	
    	
    	producerTemplate.sendBody("direct:getUser", null);
        
    	return "Hello " + a + ", Welcome to CXF RS Spring Boot World!!! " + route_status;
    }

}
