package org.example.shoplist.list;

import jakarta.persistence.*;

@Entity
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
}
