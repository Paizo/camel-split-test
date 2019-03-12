package com.github.paizo.camelsplit.configuration.router;

import com.github.paizo.camelsplit.pojo.A;
import com.github.paizo.camelsplit.pojo.B;
import com.github.paizo.camelsplit.pojo.C;

import java.util.Collections;
import java.util.List;

public class ClassSplitter {

    public List<B> getBList(A a) {
        return a.getBList() == null ? Collections.<B>emptyList() : a.getBList();
    }

    public List<C> getCList(B b) {
        return b.getCList() == null ? Collections.<C>emptyList() : b.getCList();
    }
}
