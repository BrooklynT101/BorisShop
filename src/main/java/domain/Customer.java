package domain;

import java.util.Objects;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author Mark George
 */
public class Customer {

    private Integer customerId;

    @NotNull(message = "Username must be provided.")
    @NotBlank(message = "Username must be provided.")
    @Length(min = 2, message = "Username must contain at least two characters.")
    private String username;

    @NotNull(message = "First name must be provided.")
    @NotBlank(message = "First name must be provided.")
    @Length(min = 2, message = "Firstname must contain at least two characters.")
    private String firstName;

    @NotNull(message = "Surname must be provided.")
    @NotBlank(message = "Surname must be provided.")
    @Length(min = 2, message = "Surname must contain at least two characters.")
    private String surname;

    @NotNull(message = "Password must be provided.")
    @NotBlank(message = "Password must be provided.")
    private String password;

    @NotNull(message = "Email must be provided.")
    @NotBlank(message = "Email must be provided.")
    private String emailAddress;

    @NotNull(message = "Shipping address must be provided.")
    @NotBlank(message = "Shipping address must be provided.")
    private String shippingAddress;

    public Customer() {
    }

    public Customer(String username, String password, String firstName, String surname, String shippingAddress, String emailAddress) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.shippingAddress = shippingAddress;
        this.emailAddress = emailAddress;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer personId) {
        this.customerId = personId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", username=" + username + ", firstName=" + firstName + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + '}';
    }

    @Override
    public boolean equals(Object o) {
        // if this is just the object return true
        if (this == o) {
            return true;
        }
        // if o is null or the classes dont match return false
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        // otherwise just create a customer object and use java utility equals method
        Customer customer = (Customer) o;
        return Objects.equals(username, customer.username);
    }

    // Had to get chatgpt to help with this one since I forgot 
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

}
