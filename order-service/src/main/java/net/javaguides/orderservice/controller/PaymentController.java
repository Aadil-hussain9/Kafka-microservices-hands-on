package net.javaguides.orderservice.controller;

import net.javaguides.orderservice.dto.Payment;
import net.javaguides.orderservice.dto.PaymentEvent;
import net.javaguides.orderservice.kafka.PaymentProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class PaymentController {
    private final PaymentProducer paymentProducer;

    public PaymentController(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @PostMapping("payment")
    public String payment(@RequestBody Payment payment) {
        payment.setPaymentId(UUID.randomUUID().toString());

        PaymentEvent paymentEvent = new PaymentEvent();
        paymentEvent.setMessage("Payment successful");
        paymentEvent.setStatus("SUCCESS");
        paymentEvent.setPayment(payment);
        paymentProducer.sendPaymentMessage(paymentEvent);
        return "Payment successful";
    }
}
