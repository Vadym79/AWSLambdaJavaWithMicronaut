package software.amazonaws.example.product.controller;

import org.crac.Context;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.serialization.events.LambdaEventSerializers;

import io.micronaut.crac.OrderedResource;
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * For this type of priming, please uncomment this dependency in pom.xml
 * 
 * <dependency> 
 *    <groupId>com.amazonaws</groupId>
 *    <artifactId>aws-lambda-java-serialization
 *    </artifactId> 
 * </dependency>
 *
 * This dependency not required for other Lambda SnapStart priming techniques
 */
@Singleton
public class AmazonAPIGatewayProxyRequestPrimingResource implements OrderedResource  {
	
	@Inject
	private JsonMapper objectMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(AmazonAPIGatewayProxyRequestPrimingResource.class);

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    	logger.info("entered API Gateway Proxy Rest API Request Event priming before checkpoint method");
    	
       	APIGatewayProxyRequestEvent requestEvent = LambdaEventSerializers
    				.serializerFor(APIGatewayProxyRequestEvent.class, AmazonAPIGatewayProxyRequestPrimingResource.class.getClassLoader())
    				.fromJson(getAPIGatewayProxyRequestEventAsJson());
       	logger.info("APi Gateway proxy request event: " + requestEvent);
    	try (ApiGatewayProxyRequestEventFunction apiGatewayProxyRequestEventFunction = new ApiGatewayProxyRequestEventFunction()) {
    		apiGatewayProxyRequestEventFunction.handleRequest(requestEvent, new MockLambdaContext());
		}
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
    }
    
	private String getAPIGatewayProxyRequestEventAsJson() throws Exception {
		return objectMapper.writeValueAsString(this.getAPIGatewayProxyRequestEvent());
	}
    
    private APIGatewayProxyRequestEvent getAPIGatewayProxyRequestEvent() {
    	final APIGatewayProxyRequestEvent aPIGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent ();
    	aPIGatewayProxyRequestEvent.setHttpMethod("GET");
    	aPIGatewayProxyRequestEvent.setPath("/products/0");
    	
    	//aPIGatewayProxyRequestEvent.setResource("/products/{id}");
    	//aPIGatewayProxyRequestEvent.setPathParameters(Map.of("id","0"));
    	/*
    	final ProxyRequestContext proxyRequestContext = new ProxyRequestContext();
    	final RequestIdentity requestIdentity= new RequestIdentity();
    	requestIdentity.setApiKey("blabla");
    	proxyRequestContext.setIdentity(requestIdentity);
    	aPIGatewayProxyRequestEvent.setRequestContext(proxyRequestContext);
    	*/
    	return aPIGatewayProxyRequestEvent;		
    }
}
