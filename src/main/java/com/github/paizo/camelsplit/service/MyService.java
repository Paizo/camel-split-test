package com.github.paizo.camelsplit.service;

import com.github.paizo.camelsplit.pojo.A;
import com.github.paizo.camelsplit.pojo.B;
import com.github.paizo.camelsplit.pojo.C;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Handler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MyService {

    @Handler
    public C doWork(C c) {
        //..
        return c;
    }


    @SuppressWarnings("unused")
    public A updateAWithBResults(A a, List<B> bResults) {
        a.setBList(bResults);
        a.getBList().removeIf(b -> (b.getValue() != null && b.getValue().equals("skip")));
        for (B b: a.getBList()) {
            a.getBList().removeIf(c -> (c.getValue() != null && c.getValue().equals("skip")));
            for (C c: b.getCList()) {
                // do stuff
            }
        }
        return a;
    }

    @SuppressWarnings("unused")
    public B updateBWithCResults(B b, List<C> cResults) {
        b.setCList(cResults);
        b.getCList().removeIf(c -> (c.getValue() != null && c.getValue().equals("skip")));
        for (C c: b.getCList()) {
            //test
        }
        return b;
    }
}
