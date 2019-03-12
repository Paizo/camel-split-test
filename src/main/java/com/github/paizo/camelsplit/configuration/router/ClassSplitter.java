package com.github.paizo.camelsplit.configuration.router;

import com.github.paizo.camelsplit.pojo.A;
import com.github.paizo.camelsplit.pojo.B;
import com.github.paizo.camelsplit.pojo.C;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassSplitter {

    public List<B> getBList(A a) {
//        return a.getBList() == null ? Collections.<B>emptyList() : a.getBList();
        return a.getBList() == null || a.getBList().isEmpty()
                ? Arrays.asList(B.builder().value("skip").build())
                : a.getBList();
    }

    public List<C> getCList(B b) {
//        return b.getCList() == null ? Collections.<C>emptyList() : b.getCList();
        return b.getCList() == null || b.getCList().isEmpty()
                ? Arrays.asList(C.builder().value("skip").build())
                : b.getCList();

    }
}
