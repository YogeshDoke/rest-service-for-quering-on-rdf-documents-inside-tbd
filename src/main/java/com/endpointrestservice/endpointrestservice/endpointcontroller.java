/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.endpointrestservice.endpointrestservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author yogesh
 */
@RestController
public class endpointcontroller {

    /**
     *
     * @param University
     */
    @Autowired
    QueryprocessorService service1;

    @RequestMapping(value = "rdf",method = RequestMethod.POST)
    public String endpoint(@RequestParam(value = "University") String University) {

        return service1.Tbdqueryprocessor(University);

    }

}
