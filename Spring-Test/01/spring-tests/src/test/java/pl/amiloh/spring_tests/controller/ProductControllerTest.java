package pl.amiloh.spring_tests.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import pl.amiloh.spring_tests.model.Product;
import pl.amiloh.spring_tests.service.ProductService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    @DisplayName("GET /products returns OK and list of products")
    void getAll_ReturnValidResponseEntity() {
        // given
        var products = List.of(new Product(), new Product());
        doReturn(products).when(productService).getAll();

        // when
        var responseEntity = productController.getAll();

        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(products, responseEntity.getBody());
        assertInstanceOf(List.class, responseEntity.getBody());

        verify(productService).getAll();
        verifyNoMoreInteractions(productService);
//        verifyNoInteractions(productService);
    }
}