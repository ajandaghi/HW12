package store.entity;

public class Product {
    private Integer id;
    private Integer adminId;
    private Integer categoryId;
    private String name;
    private Double price;
    private int number;


    public Product() {
    }

    public Product(Integer id, Integer adminId, Integer categoryId, String name, Double price, int number) {
        this.id = id;
        this.adminId = adminId;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.number = number;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
