package software.amazonaws.example.product.handler;

import java.util.Map;

import org.crac.Context;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import io.micronaut.crac.OrderedResource;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

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
				*/
    	APIGatewayProxyRequestEvent requestEvent=this.getAPIGatewayProxyRequestEvent();
		logger.info("req event: " + requestEvent);
		APIGatewayProxyResponseEvent execute = new GetProductByIdHandler().execute(requestEvent);
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

	