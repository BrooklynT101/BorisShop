/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brooklyn
 */
public class CustomerCollectionsDAO implements CustomerDAO {

//    private static int idCounter = 0; // Counter for unique IDs
//    // set to hold the used ids
//    private Set<Integer> usedIds = new HashSet<>();
    // key value pairs
    private final Map<String, Customer> customerMap = new HashMap<>();

    @Override
    public void saveCustomer(Customer customer) {
//        if (usedIds.contains(customer.getCustomerId())) {
//            throw new 
//        }
        String username = customer.getUsername();
        customerMap.put(username, customer);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerMap.get(username);
    }

    @Override
    public boolean checkCustomerCredentials(String username, String password) {
        Customer customer = customerMap.get(username);
        //returns true if the password matches and there is actually a customer
        return customer != null && customer.getPassword().equals(password);
    }

    @Override
    public void removeCustomer(String username) {
//        usedIds.remove(customerMap.get(username).getCustomerId()); //free up the ID 
        customerMap.remove(username);
    }

    /**
     * This method generates and returns an unique ID, ensures the ID isn't 
     * used before returning an ID. Used ChatGPT for optimizing the code, it 
     * came up with the pre-increment idea in the do-while loop
     * 
     * @return an ID unique to the DAO
     */
//    @Override
//    public int getUniqueID() {
//        int id;
//        do {
//            id = ++idCounter;
//        } while (usedIds.contains(id));
//        
//        usedIds.add(id);
//        return id;
//    }

}
