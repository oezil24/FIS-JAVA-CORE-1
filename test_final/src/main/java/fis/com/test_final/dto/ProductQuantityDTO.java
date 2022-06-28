package fis.com.test_final.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductQuantityDTO {
    private Long productId;
    private Long quantity;
}
