package org.example.shoplist.list;

import org.example.shoplist.list.shoplist.Shoplist;
import org.example.shoplist.list.shoplist.ShoplistRepository;

import org.example.shoplist.list.shoplist.ShoplistServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ShoplistServiceImplTest {
    @Mock
    private ShoplistRepository shoplistRepository;
    @InjectMocks
    private ShoplistServiceImpl shoplistService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateShoplist_Success(){
        String shoplistName = "TEST SHOPLIST";

        ResponseEntity<Shoplist> response = shoplistService.createShoplist(shoplistName);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(shoplistName, response.getBody().getName());

        verify(shoplistRepository, times(1)).save(any(Shoplist.class));
    }

    @Test
    public void testCreateShoplist_Error(){
        String shoplistName = "TEST SHOPLIST";
        doThrow(new RuntimeException("Error saving shoplist")).when(shoplistRepository).save(any(Shoplist.class));

        ResponseEntity<Shoplist> response = shoplistService.createShoplist(shoplistName);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }

}
