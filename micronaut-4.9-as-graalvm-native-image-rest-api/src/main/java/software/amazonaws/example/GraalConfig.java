package software.amazonaws.example;

import java.util.HashSet;
import org.joda.time.DateTime;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import io.micronaut.core.annotation.ReflectionConfig;


@ReflectionConfig(type = APIGatewayProxyRequestEvent.class)
@ReflectionConfig(type = APIGatewayProxyRequestEvent.ProxyRequestContext.class)
@ReflectionConfig(type = APIGatewayProxyRequestEvent.RequestIdentity.class)
@ReflectionConfig(type = HashSet.class)
@ReflectionConfig(type = DateTime.class)
		
public class GraalConfig {

}
