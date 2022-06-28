package fis.com.test_final.dto;

import fis.com.test_final.model.OrderItem;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItemDTO {
    private Long id;

    private ProductDTO productDTO;

    private Long quantity;

    private Double amount;

    public static class Mapper {
        public static OrderItemDTO mapFromOrderItemEntity(OrderItem orderItem) {
            return OrderItemDTO.builder()
                    .id(orderItem.getId())
                    .productDTO(ProductDTO.Mapper.mapFromProductEntity(orderItem.getProduct()))
                    .quantity(orderItem.getQuantity())
                    .amount(orderItem.getAmount())
                    .build();
        }
    }
}
