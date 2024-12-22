package net.javaguides.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String paymentId;
    private String payerName;
    private int orderId;
    private double totalAmount;
}
