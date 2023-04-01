package esalaf.projet11;

import java.util.Date;

public class ProductData {
    private Integer productId;
    private String nameProduct;
    private Double price;
    private String status;
    private Date date;

    public ProductData(Integer productId, String nameProduct, String status, Double price, Date date) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.price = price;
        this.status = status;
        this.date = date;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String type) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "productId=" + productId +
                ", nameProduct='" + nameProduct + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
}
