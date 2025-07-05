package software.amazonaws.example.product.controller;

import java.util.Map;

import org.crac.Context;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent.ProxyRequestContext;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent.RequestIdentity;

import io.micronaut.crac.OrderedResource;
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction;

//@Singleton
public class ProductAPIResourceWithAPIGatewayRequestPriming implements OrderedResource  {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductAPIResourceWithAPIGatewayRequestPriming.class);

    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    	logger.info("entered api gateway priming before checkpoint method");
    	try (ApiGatewayProxyRequestEventFunction apiGatewayProxyRequestEventFunction = new ApiGatewayProxyRequestEventFunction()) {
    		apiGatewayProxyRequestEventFunction.handleRequest(getAwsProxyRequest(), new MockLambdaContext());
		}
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
    }
    
    private static APIGatewayProxyRequestEvent getAwsProxyRequest () {
    	final APIGatewayProxyRequestEvent aPIGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent ();
    	aPIGatewayProxyRequestEvent.setHttpMethod("GET");
    	aPIGatewayProxyRequestEvent.setPath("/products/0");
    	aPIGatewayProxyRequestEvent.setResource("/products/{id}");
    	aPIGatewayProxyRequestEvent.setPathParameters(Map.of("id","0"));
    	final ProxyRequestContext proxyRequestContext = new ProxyRequestContext();
    	final RequestIdentity requestIdentity= new RequestIdentity();
    	requestIdentity.setApiKey("blabla");
    	proxyRequestContext.setIdentity(requestIdentity);
    	aPIGatewayProxyRequestEvent.setRequestContext(proxyRequestContext);
    	return aPIGatewayProxyRequestEvent;		
    }
}
