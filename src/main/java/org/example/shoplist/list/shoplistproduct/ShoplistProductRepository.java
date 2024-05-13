package org.example.shoplist.list.shoplistproduct;

import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.shoplist.Shoplist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ShoplistProductRepository extends JpaRepository<ShoplistProduct, Long> {
    void deleteByShoplistAndProduct(Shoplist shoplist, Product product);
    List<ShoplistProduct> findShoplistProductsByShoplist(Shoplist shoplist);
}
