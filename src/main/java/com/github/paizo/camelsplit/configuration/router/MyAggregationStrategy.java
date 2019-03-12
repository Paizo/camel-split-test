package com.github.paizo.camelsplit.configuration.router;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

public class MyAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        List list;
        if (oldExchange == null) {
            list = new ArrayList<>();
        } else {
            list =  oldExchange.getIn().getBody(ArrayList.class);
        }
        list.add(newExchange.getIn().getBody());
        newExchange.getIn().setBody(list);
        return newExchange;
    }
}