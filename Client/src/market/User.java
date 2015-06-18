package market;

/**
 * Created by Marcio on 17/06/2015.
 */
public class User {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String id;
    private String password;

    public User(String firstName, String lastName, String address, String phone, String email, String id, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    private String getPassword() {
        return password;
    }
}
