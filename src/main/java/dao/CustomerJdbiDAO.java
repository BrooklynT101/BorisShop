/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Brooklyn
 */
public interface CustomerJdbiDAO extends CustomerDAO {

////    Had to get ChatGPT to help get this to work as this method required a sequence
//    @Override
//    @SqlQuery("SELECT NEXT VALUE FOR seq_customer_id")
//    int getUniqueID();

    @Override
    @SqlUpdate("DELETE FROM Customer WHERE Username = :username")
    void removeCustomer(@Bind("username") String username);

    @Override
    @SqlQuery("select exists (select * from Customer where Username = :username AND Password = :password)")
    boolean checkCustomerCredentials(@Bind("username") String username, @Bind("password") String password);

    @Override
    @RegisterBeanMapper(Customer.class)
    @SqlQuery("SELECT * FROM Customer WHERE Username = :username")
    Customer getCustomerByUsername(@Bind("username") String username);

    @Override
    @SqlUpdate("INSERT INTO Customer (Username, Firstname, Surname, Password, EmailAddress, ShippingAddress) VALUES (:username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
    void saveCustomer(@BindBean Customer customer);
}
