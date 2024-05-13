package org.example.shoplist.list.shoplist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoplist")
public class ShoplistController {
    private final ShoplistService shoplistServiceImpl;

    public ShoplistController(ShoplistService shoplistServiceImpl) {
        this.shoplistServiceImpl = shoplistServiceImpl;
    }
    @PostMapping("/add")
    public ResponseEntity<Shoplist> save(String name){
        return shoplistServiceImpl.createShoplist(name);
    }
}
