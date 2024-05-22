package org.example.shoplist.list.shoplistproduct;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.shoplist.Shoplist;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "shoplist_product")
public class ShoplistProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shoplist_id")
    private Shoplist shoplist;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private boolean purchased;
}
