package org.example.shoplist.list.shoplist;

import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShoplistService {
    ResponseEntity<Shoplist> createShoplist(String name);
    ResponseEntity<ShoplistProduct> addProduct(Long shoplistId, Long productId, int quantity);
    ResponseEntity<ShoplistProduct> removeProduct(Long shoplistId, Long productId);
    ResponseEntity<List<Product>> getProductsOnList(Long shoplistId);
    List<ShoplistDTO> getAllShoplistsDTO();
    boolean updatePurchasedStatus(Long id, Boolean purchased);
}
