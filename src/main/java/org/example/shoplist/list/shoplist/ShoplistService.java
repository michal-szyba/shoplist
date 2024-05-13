package org.example.shoplist.list.shoplist;

import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShoplistService {
    ResponseEntity<Shoplist> createShoplist(String name);
    ResponseEntity<ShoplistProduct> addProduct(Long shoplistId, Long productId, int quantity);
    void removeProduct(Long shoplistId, Long productId);
    List<ShoplistProduct> getProductsOnList(Long shoplistId);
}
