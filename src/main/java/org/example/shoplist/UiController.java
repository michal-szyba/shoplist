package org.example.shoplist;

import org.example.shoplist.list.shoplist.Shoplist;
import org.example.shoplist.list.shoplist.ShoplistRepository;
import org.example.shoplist.list.shoplist.ShoplistServiceImpl;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.example.shoplist.list.shoplistproduct.ShoplistProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.shoplist.list.shoplist.ShoplistDTO;

import java.util.List;

@Controller
public class UiController {

    private final ShoplistProductRepository shoplistProductRepository;
    private final ShoplistServiceImpl shoplistService;

    public UiController(ShoplistProductRepository shoplistProductRepository, ShoplistServiceImpl shoplistService) {
        this.shoplistProductRepository = shoplistProductRepository;
        this.shoplistService = shoplistService;
    }

    @GetMapping("/home")
    public String home(Model model){
        List<ShoplistDTO> shoplistDTOS = shoplistService.getAllShoplistsDTO();
        model.addAttribute("shoplistsDTOS", shoplistDTOS);
        return "home";
    }

}
