package com.br.dorotech.app.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductMessageProducer {

    private AmazonSQS amazonSQS;

    public void sentToQueue(final String queueName, final String message) {
        amazonSQS.sendMessage(new SendMessageRequest()
                .withQueueUrl(queueName)
                .withMessageBody(message)
        );
    }

}

