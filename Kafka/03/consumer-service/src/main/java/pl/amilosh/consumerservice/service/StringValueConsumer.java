package pl.amilosh.consumerservice.service;

import pl.amilosh.consumerservice.model.StringValue;

import java.util.List;

public interface StringValueConsumer {

    void accept(List<StringValue> value);
}
