package org.example.shoplist.list.shoplist;

import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.product.ProductRepository;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.example.shoplist.list.shoplistproduct.ShoplistProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<ShoplistProduct> addProduct(Long shoplistId, Long productId, int quantity) {
        Optional<Shoplist> shoplistOptional = shoplistRepository.findById(shoplistId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if(shoplistOptional.isPresent() && productOptional.isPresent()){
            ShoplistProduct shoplistProduct = new ShoplistProduct();
            shoplistProduct.setShoplist(shoplistOptional.get());
            shoplistProduct.setProduct(productOptional.get());
            shoplistProduct.setQuantity(quantity);
            shoplistProductRepository.save(shoplistProduct);
            return ResponseEntity.status(HttpStatus.FOUND).body(shoplistProduct);
        } else {
               return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<ShoplistProduct> removeProduct(Long shoplistId, Long productId) {
        Optional<Shoplist> shoplistOptional = shoplistRepository.findById(shoplistId);
        Optional<Product> productOptional = productRepository.findById(productId);
        if(shoplistOptional.isPresent() && productOptional.isPresent()){
            shoplistProductRepository.deleteByShoplistAndProduct(
                    shoplistOptional.get(),
                    productOptional.get()
            );
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<List<Product>> getProductsOnList(Long shoplistId){
        Optional<Shoplist> shoplistOptional = shoplistRepository.findById(shoplistId);
        if(shoplistOptional.isPresent()){
            List<ShoplistProduct> shoplistProducts = shoplistProductRepository.findShoplistProductsByShoplist(shoplistOptional.get());
            return ResponseEntity.status(HttpStatus.FOUND).body(shoplistProducts
                            .stream()
                            .map(ShoplistProduct::getProduct)
                            .collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
