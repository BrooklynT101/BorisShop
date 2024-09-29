/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;

/**
 *
 * @author Brooklyn
 */
public interface CustomerDAO {
    void saveCustomer(Customer customer);
    Customer getCustomerByUsername(String username);
    boolean checkCustomerCredentials(String username, String password);
    void removeCustomer(String username);
//    int getUniqueID();
}
