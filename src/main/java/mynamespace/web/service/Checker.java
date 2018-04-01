////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 24.03.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Checker {

    public Checker() {}

    public int getAmount(int number) {
        return number * 7;
    }

    public List<String> getFlights() {
        List<String> s = new ArrayList<>();
        s.add("abc");
        s.add("bb");
        s.add("ccc");
        s.add("dd");
        s.add("eee");

        return s;
    }

    public String getFlight() {

        return "abc";
    }

    //    public String buildSearch(){
    //        String val = (String)request.getAttribute("colnames");
    //    }

}
