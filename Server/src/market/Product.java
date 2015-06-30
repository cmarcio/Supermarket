package market;

import java.util.GregorianCalendar;

/**
 * Created by Marcio on 17/06/2015.
 */
public class Product {
    public String name;
    public float price;
    public GregorianCalendar expirationDay;
    public Vendor vendor;
    public int quantity;

    public Product(String name, float price, Vendor vendor) {
        this.name = name;
        this.price = price;
        this.vendor = vendor;
        this.quantity = 0;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setExpirationDay(GregorianCalendar expirationDay) {
        this.expirationDay = expirationDay;
    }

    public void updateQuantity(int number) {
        quantity = quantity + number;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public GregorianCalendar getExpirationDay() {
        return expirationDay;
    }

    public int getQuantity() {
        return quantity;
    }
}


