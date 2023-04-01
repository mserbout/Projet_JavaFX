package esalaf.projet11;

import java.sql.Date;

public class CustomerData {
    private Integer customerNum;
    private String first_name;
    private String last_name;
    private String gender;
    private String phoneNumber;
    private Integer  productID;
    private String productName;
    private String type;
    private Integer quantity;
    private Double total;
    private Date date;

    public CustomerData(Integer customerNum, String first_name, String last_name, String gender, String phoneNumber, Integer productID, String productName, String type, Integer quantity, Double total, Date date) {
        this.customerNum = customerNum;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.productID = productID;
        this.productName = productName;
        this.type = type;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    public Integer getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(Integer customerNum) {
        this.customerNum = customerNum;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
