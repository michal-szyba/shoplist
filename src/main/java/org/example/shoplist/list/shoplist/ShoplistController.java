package org.example.shoplist.list.shoplist;

import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoplist")
public class ShoplistController {
    private final ShoplistService shoplistServiceImpl;

    public ShoplistController(ShoplistService shoplistServiceImpl) {
        this.shoplistServiceImpl = shoplistServiceImpl;
    }
    @PostMapping("/add")
    public ResponseEntity<Shoplist> save(@RequestBody String name){
        return shoplistServiceImpl.createShoplist(name);
    }
    @GetMapping("/{shoplistId}/products")
    public ResponseEntity<List<Product>> productsByList(@PathVariable Long shoplistId){
        return shoplistServiceImpl.getProductsOnList(shoplistId);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ShoplistProduct> removeProductFromList(@RequestParam("shoplistId") Long shoplistId,
                                                                 @RequestParam("productId") Long productId){
        return shoplistServiceImpl.removeProduct(shoplistId, productId);
    }


}
