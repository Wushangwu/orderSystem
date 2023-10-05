package com.example.order.web;

import com.example.order.application.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

}
