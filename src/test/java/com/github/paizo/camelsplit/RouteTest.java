package com.github.paizo.camelsplit;

import com.github.paizo.camelsplit.pojo.A;
import com.github.paizo.camelsplit.pojo.B;
import com.github.paizo.camelsplit.pojo.C;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public class RouteTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject(uri = "mock:seda:out")
    private MockEndpoint resultEndpoint;

    @Test
    public void testNonEmptyListStructure() throws InterruptedException {
        A body = nonEmpty();
        resultEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("seda://go", body);

        resultEndpoint.assertIsSatisfied();
    }


    @Test
    public void testEmptyBList() throws InterruptedException {
        A body = emptyBList();
        resultEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("seda://go", body);

        resultEndpoint.assertIsSatisfied();
    }

    @Test
    public void testEmptyCList() throws InterruptedException {
        A body = emptyCList();
        resultEndpoint.expectedMessageCount(1);

        producerTemplate.sendBody("seda://go", body);

        resultEndpoint.assertIsSatisfied();
    }

    private A emptyBList() {
        return A.builder().build();
    }

    private A emptyCList() {
        return A
                .builder()
                .bList(Arrays.asList(B.builder().build()))
                .build();
    }

    private A nonEmpty() {
        return A
                .builder()
                .bList(
                        Arrays.asList(
                            B
                                .builder()
                                .cList(Arrays.asList(C.builder().build()))
                                .build()
                        )
                )
                .build();
    }

}
