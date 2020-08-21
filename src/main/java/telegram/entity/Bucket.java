package telegram.entity;

public class Bucket {
    private int id;
    private long chatId;
    private String product;
    private double price;

    public Bucket() {
    }

    public Bucket(int id, long chatId, String product, double price) {
        this.id = id;
        this.chatId = chatId;
        this.product = product;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
