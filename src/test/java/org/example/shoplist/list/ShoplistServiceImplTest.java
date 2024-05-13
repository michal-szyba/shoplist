package org.example.shoplist.list;

import org.example.shoplist.list.product.Product;
import org.example.shoplist.list.product.ProductRepository;
import org.example.shoplist.list.shoplist.Shoplist;
import org.example.shoplist.list.shoplist.ShoplistRepository;

import org.example.shoplist.list.shoplist.ShoplistServiceImpl;
import org.example.shoplist.list.shoplistproduct.ShoplistProduct;
import org.example.shoplist.list.shoplistproduct.ShoplistProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ShoplistServiceImplTest {
    @Mock
    private ShoplistRepository shoplistRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ShoplistProductRepository shoplistProductRepository;
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

    @Test
    public void testAddProduct_Success() {
        Shoplist shoplist = new Shoplist();
        Product product = new Product();
        ShoplistProduct shoplistProduct = new ShoplistProduct();
        shoplistProduct.setShoplist(shoplist);
        shoplistProduct.setProduct(product);
        shoplistProduct.setQuantity(5);

        when(shoplistRepository.findById(anyLong())).thenReturn(Optional.of(shoplist));
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(shoplistProductRepository.save(any(ShoplistProduct.class))).thenReturn(shoplistProduct);

        ResponseEntity<ShoplistProduct> response = shoplistService.addProduct(1L, 1L, 5);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(shoplistProduct, response.getBody());
    }

    @Test
    public void testAddProduct_NotFound() {
        when(shoplistRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<ShoplistProduct> response = shoplistService.addProduct(1L, 1L, 5);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    public void testRemoveProduct_Success() {
        Long shoplistId = 1L;
        Long productId = 1L;
        Shoplist shoplist = new Shoplist();
        shoplist.setId(shoplistId);
        Product product = new Product();
        product.setId(productId);
        when(shoplistRepository.findById(shoplistId)).thenReturn(Optional.of(shoplist));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        ResponseEntity<ShoplistProduct> response = shoplistService.removeProduct(shoplistId, productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(shoplistProductRepository).deleteByShoplistAndProduct(shoplist, product);
    }

    @Test
    public void testRemoveProduct_ShoplistNotFound() {
        Long shoplistId = 1L;
        Long productId = 1L;
        when(shoplistRepository.findById(shoplistId)).thenReturn(Optional.empty());
        ResponseEntity<ShoplistProduct> response = shoplistService.removeProduct(shoplistId, productId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shoplistProductRepository, never()).deleteByShoplistAndProduct(any(), any());
    }

    @Test
    public void testRemoveProduct_ProductNotFound() {
        Long shoplistId = 1L;
        Long productId = 1L;
        Shoplist shoplist = new Shoplist();
        shoplist.setId(shoplistId);
        when(shoplistRepository.findById(shoplistId)).thenReturn(Optional.of(shoplist));
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        ResponseEntity<ShoplistProduct> response = shoplistService.removeProduct(shoplistId, productId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(shoplistProductRepository, never()).deleteByShoplistAndProduct(any(), any());
    }

}
