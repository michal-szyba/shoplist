package org.example.shoplist.list.shoplistproduct;

import org.example.shoplist.list.shoplist.ShoplistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ShoplistProductController {

    @Autowired
    private ShoplistServiceImpl shoplistService;

    @PostMapping("/updatePurchasedStatus")
    public ResponseEntity<?> updatePurchasedStatus(@RequestBody Map<String, Object> body){
        Long id = Long.valueOf(body.get("id").toString());
        Boolean purchased = Boolean.valueOf(body.get("purchased").toString());

        boolean success = shoplistService.updatePurchasedStatus(id, purchased);
        if(success){
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
