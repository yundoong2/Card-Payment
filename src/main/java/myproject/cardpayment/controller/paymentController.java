package myproject.cardpayment.controller;

import myproject.cardpayment.dto.paymentDTO;
import myproject.cardpayment.service.paymentService;
import myproject.cardpayment.service.paymentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {

    @RequestMapping(value = "/payment/new", method = RequestMethod.POST)
    public String pay(paymentForm paymentForm) {

    }

    @RequestMapping(value = "/payment/id", method = RequestMethod.POST)
    public String cancel(paymentForm paymentForm) {

    }

    @RequestMapping(value = "/payment/id", method = RequestMethod.GET)
    public String getPayment() {

    }
}
