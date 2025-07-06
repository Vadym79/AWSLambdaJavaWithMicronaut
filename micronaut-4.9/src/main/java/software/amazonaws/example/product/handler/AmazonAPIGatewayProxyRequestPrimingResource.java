package software.amazonaws.example.product.handler;

import java.util.Map;

import org.crac.Context;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
//import com.amazonaws.services.lambda.runtime.serialization.events.LambdaEventSerializers;

import io.micronaut.crac.OrderedResource;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;


/**
 * This type of priming doesn't work together with Micronaut Serialization
 * (Serde) In order to make it run, please comment these 2 dependencies in
 * pom.xml 
 * <!-- 
 * <dependency> 
 *    <groupId>io.micronaut.serde</groupId>
 *    <artifactId>micronaut-serde-jackson</artifactId> 
 *    <scope>compile</scope>
 * </dependency> 
 * <dependency> 
 *    <groupId>io.micronaut.aws</groupId>
 *    <artifactId>micronaut-aws-lambda-events-serde</artifactId>
 *    <scope>compile</scope> 
 * </dependency> --> 
 * 
 * and uncomment these 2
 * 
 * <dependency> 
 *    <groupId>io.micronaut</groupId>
 *    <artifactId>micronaut-jackson-databind</artifactId> 
 *    <scope>runtime</scope>
 * </dependency> 
 * <dependency> 
 *    <groupId>com.amazonaws</groupId>
 *    <artifactId>aws-lambda-java-serialization
 *    </artifactId> 
 * </dependency>
 * 
 * Also remove these 2 annotations from Product.java
 * @Serdeable.Deserializable
 * @Serdeable.Serializable
 * 
 * and uncomment the whole code in the beforeCheckpoint 
 */

@Singleton
public class AmazonAPIGatewayProxyRequestPrimingResource implements OrderedResource  {

	@Inject
	private JsonMapper objectMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(AmazonAPIGatewayProxyRequestPrimingResource.class);

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    	logger.info("entered api gateway rest api priming before checkpoint method");
    	
    	/*
    	APIGatewayProxyRequestEvent requestEvent = LambdaEventSerializers
				.serializerFor(APIGatewayProxyRequestEvent.class, ClassLoader.getSystemClassLoader())
				.fromJson(getAPIGatewayProxyRequestEventAsJson());
				
		logger.info("req event: " + requestEvent);
		
		new GetProductByIdHandler().execute(requestEvent);
		*/
    }

       
    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
    }
    
	private String getAPIGatewayProxyRequestEventAsJson() throws Exception {
		return objectMapper.writeValueAsString(this.getAPIGatewayProxyRequestEvent());
	}
	
	private APIGatewayProxyRequestEvent getAPIGatewayProxyRequestEvent() throws Exception {
    	final APIGatewayProxyRequestEvent aPIGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent ();
    	aPIGatewayProxyRequestEvent.setHttpMethod("GET");
    	aPIGatewayProxyRequestEvent.setPath("/products/0");
    	aPIGatewayProxyRequestEvent.setPathParameters(Map.of("id","0"));
    	
    	//aPIGatewayProxyRequestEvent.setResource("/products/{id}");
    	
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

	