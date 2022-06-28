package fis.com.test_final.dto;

import fis.com.test_final.model.Order;
import fis.com.test_final.model.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDTO {

    private Long id;

    private LocalDateTime orderDateTime;

    private Long customerId;

    private Double totalAmount;

    private OrderStatus status;

    public static class Mapper {
        public static OrderDTO mapFromOrderEntity(Order order) {
            return OrderDTO.builder()
                    .id(order.getId())
                    .customerId(order.getCustomer().getId())
                    .orderDateTime(order.getOrderDateTime())
                    .status(order.getStatus())
                    .totalAmount(order.getTotalAmount())
                    .build();
        }
    }
}
