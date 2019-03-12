package com.github.paizo.camelsplit.configuration.router;

import com.github.paizo.camelsplit.service.MyService;
import org.apache.camel.builder.ExpressionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class RouteBuilder extends org.apache.camel.builder.RouteBuilder {


    @Autowired
    private MyService service;

    @Override
    public void configure() throws Exception {
        // @formatter:off

        from("{{route.from}}")
            .autoStartup(true)
            .threads(1, 5)
            .setProperty("originalA", body())
//            .split(body().method("getBList"))
            .split(ExpressionBuilder.beanExpression(new ClassSplitter(), "getBList"), new MyAggregationStrategy())
                .streaming()
                .setProperty("originalB", body())
//                .split(body().method("getCList"))
                .split(ExpressionBuilder.beanExpression(new ClassSplitter(), "getCList"), new MyAggregationStrategy())
                    .streaming()
                    .bean(service)
                .end()
                .bean(service, "updateBWithCResults(${property.originalB}, ${body})")
            .end()
            .bean(service, "updateAWithBResults(${property.originalA}, ${body})")
            .to("seda:out")
        .end();
    }

    // @formatter:on
}
