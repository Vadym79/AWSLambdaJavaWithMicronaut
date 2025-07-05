// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package software.amazonaws.example.product.handler;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import io.micronaut.function.aws.MicronautRequestHandler;
import io.micronaut.json.JsonMapper;
import jakarta.inject.Inject;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazonaws.example.product.dao.ProductDao;
import software.amazonaws.example.product.entity.Product;


public class GetProductByIdHandler extends
		MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>  {

	@Inject
	private ProductDao productDao;

	@Inject
	private JsonMapper objectMapper;

	private static final Logger logger = LoggerFactory.getLogger(GetProductByIdHandler.class);


	@Override
	public APIGatewayProxyResponseEvent execute(APIGatewayProxyRequestEvent requestEvent) {
		String id = requestEvent.getPathParameters().get("id");
		Optional<Product> optionalProduct = productDao.getProduct(id);
		try {
			if (optionalProduct.isEmpty()) {
				logger.info(" product with id " + id + " not found ");
				return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatusCode.NOT_FOUND)
						.withBody("Product with id = " + id + " not found");
			}
			logger.info(" product " + optionalProduct.get() + " found ");
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatusCode.OK)
					.withBody(objectMapper.writeValueAsString(optionalProduct.get()));
		} catch (Exception je) {
			je.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR)
					.withBody("Internal Server Error :: " + je.getMessage());
		}
	}

}