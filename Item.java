public class Item {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Item(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Quantity: " + quantity + ", Price: ₦" + price;
    }

    public String getName() {
        return name;
    }
}


    

