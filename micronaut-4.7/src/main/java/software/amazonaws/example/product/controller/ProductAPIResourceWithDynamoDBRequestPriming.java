package software.amazonaws.example.product.controller;

import org.crac.Context;
import org.crac.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.crac.OrderedResource;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import software.amazonaws.example.product.dao.ProductDao;


@Singleton
public class ProductAPIResourceWithDynamoDBRequestPriming implements OrderedResource  {

	@Inject
	private ProductDao productDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductAPIResourceWithDynamoDBRequestPriming.class);
	
    @Override
    public void beforeCheckpoint(Context<? extends Resource> context) throws Exception {
    	logger.info("entered dynamo db requst priming before checkpoint method");
    	this.productDao.getProduct("0");
    }

    @Override
    public void afterRestore(Context<? extends Resource> context) throws Exception {
    }
}
