package fis.com.test_final.dto;

import fis.com.test_final.model.Customer;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDTO {
    private Long id;
    private String name;
    private String mobile;
    private String address;

    public static class Mapper {
        public static CustomerDTO mapFromCustomerEntity(Customer customer) {
            return CustomerDTO.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .mobile(customer.getMobile())
                    .address(customer.getAddress())
                    .build();
        }
    }
}
