package org.example.shoplist.list.shoplist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.shoplist.list.product.ProductDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShoplistDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products;
}
