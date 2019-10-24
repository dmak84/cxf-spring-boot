package sample.rs.service;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TimerRoute extends RouteBuilder {

    public static final String ROUTE_NAME = "TIMER_ROUTE";
    
    @Value("${cxf.path}")
    private String cxfpath;

    @Override

    public void configure() throws Exception {

    	System.out.println("cxf.path="+cxfpath);
    	
        from("timer:initial//start?period=10000")
                .routeId(ROUTE_NAME)
                .log("executed");
        
        from("direct:getUser").log("User 1 displayed");
        

    }

}