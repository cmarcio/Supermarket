package market;

import java.util.GregorianCalendar;

/**
 * Created by Marcio on 17/06/2015.
 */
public class Product {
    public String name;
    public float price;
    public GregorianCalendar expirationDay;
    public String vendor;
    public int quantity;

    public Product(String name, float price, String vendor) {
        this.name = name;
        this.price = price;
        this.vendor = vendor;
        this.quantity = 0;
    }

    public Product(String[] fields) {
        this.name = fields[0];
        this.price = Float.parseFloat(fields[1]);
        this.vendor = fields[2];
        this.quantity = Integer.parseInt(fields[3]);
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

    public String getVendor() {
        return vendor;
    }

    public GregorianCalendar getExpirationDay() {
        return expirationDay;
    }

    public int getQuantity() {
        return quantity;
    }
}


