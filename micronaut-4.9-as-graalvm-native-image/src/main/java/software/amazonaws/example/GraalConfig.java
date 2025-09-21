package software.amazonaws.example;

import java.util.HashSet;
import org.joda.time.DateTime;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import software.amazonaws.example.product.handler.CreateProductHandler;
import software.amazonaws.example.product.handler.GetProductByIdHandler;
import software.amazonaws.example.product.entity.Product;
import software.amazonaws.example.product.entity.Products;


import io.micronaut.core.annotation.ReflectionConfig;


/*
@ReflectionConfig(type = APIGatewayProxyRequestEvent.class)
@ReflectionConfig(type = APIGatewayProxyRequestEvent.ProxyRequestContext.class)
@ReflectionConfig(type = APIGatewayProxyRequestEvent.RequestIdentity.class)
@ReflectionConfig(type = HashSet.class)
@ReflectionConfig(type = DateTime.class)
@ReflectionConfig(type = CreateProductHandler.class)
@ReflectionConfig(type = GetProductByIdHandler.class)
@ReflectionConfig(type = Product.class)
@ReflectionConfig(type = Products.class)

*/

public class GraalConfig {

}
