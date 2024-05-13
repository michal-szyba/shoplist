package org.example.shoplist.list.shoplist;

import org.example.shoplist.list.product.ProductRepository;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.example.shoplist.list.shoplistproduct.ShoplistProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShoplistServiceImpl implements ShoplistService{

    private final ShoplistRepository shoplistRepository;
    private final ProductRepository productRepository;
    private final ShoplistProductRepository shoplistProductRepository;

    @Autowired
    public ShoplistServiceImpl(ShoplistRepository shoplistRepository, ProductRepository productRepository, ShoplistProductRepository shoplistProductRepository) {
        this.shoplistRepository = shoplistRepository;
        this.productRepository = productRepository;
        this.shoplistProductRepository = shoplistProductRepository;
    }




    @Override
    public ResponseEntity<Shoplist> createShoplist(String name){
        Shoplist shoplist = new Shoplist(name);
        try {
            shoplistRepository.save(shoplist);
            return ResponseEntity.status(HttpStatus.CREATED).body(shoplist);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public void addProduct(Long shoplistId, Long productId, int quantity) {

    }

    @Override
    public void removeProduct(Long shoplistId, Long productId) {

    }

    @Override
    public List<ShoplistProduct> getProductsOnList(Long shoplistId){
        List<ShoplistProduct> list = new ArrayList<>();
        return list;
    }
}
