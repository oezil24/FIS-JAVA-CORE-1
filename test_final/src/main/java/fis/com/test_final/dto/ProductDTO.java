package fis.com.test_final.dto;

import fis.com.test_final.model.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;

    private Long avaiable;

    public static class Mapper {
        public static ProductDTO mapFromProductEntity(Product product) {
            return ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .avaiable(product.getAvaiable())
                    .build();
        }
    }
}
