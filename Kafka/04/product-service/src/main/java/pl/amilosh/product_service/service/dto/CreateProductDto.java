package pl.amilosh.product_service.service.dto;

import java.math.BigDecimal;

public class CreateProductDto {
    private String title;
    private BigDecimal price;

    public CreateProductDto() {
    }

    public CreateProductDto(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
