package com.br.dorotech.app.sqs;

import com.br.dorotech.app.models.dtos.ProductDTO;
import com.br.dorotech.app.services.ProductService;
import com.br.dorotech.app.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductMessageConsumer {

    private ProductService productService;

    @Value("${amazon.queue.product-creation}")
    private String queueName;

    @JmsListener(destination = "${amazon.queue.attendance}")
    public void messageConsumer(@Payload String message) {
        ProductDTO productDTO = JsonUtil.readValue(message, ProductDTO.class);

        log.info("***** PRODUCT CREATED:  " + queueName + ", PRODUCT NAME: " + productDTO.getName()
                + ", PRODUCT DESCRIPTION: " + productDTO.getDescription());

        //IMPLEMENTAR CHAMADA EM SERVICE
    }

}