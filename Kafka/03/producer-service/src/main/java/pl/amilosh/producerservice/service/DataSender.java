package pl.amilosh.producerservice.service;

import pl.amilosh.producerservice.model.StringValue;

public interface DataSender {

    void send(StringValue value);
}
