package store.entity;

public class CustomerBasket {
    private  Integer customerId;
    private Integer productId;
    private Integer number;
    private Double totalPrice;

    public CustomerBasket(Integer customerId, Integer productId, Integer number, Double totalPrice) {
        this.customerId = customerId;
        this.productId = productId;
        this.number = number;
        this.totalPrice = totalPrice;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
