/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import dao.CustomerDAO;
import domain.Customer;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import io.jooby.annotation.Path;

/**
 *
 * @author Brooklyn
 */
@Path("/api/customers")
public class CustomerModule extends Jooby {

    public CustomerModule(CustomerDAO dao) {

        // Login route for customers
        post("/api/signin", ctx -> {
            Customer attempt = ctx.body().to(Customer.class); // Get login details from request body
            if (attempt == null) {
                // customer not found -- need to alert customer 
                return ctx.send(StatusCode.NOT_FOUND);
            }

            Customer customer = dao.getCustomerByUsername(attempt.getUsername());

            // check credientials
            if (dao.checkCustomerCredentials(attempt.getUsername(), attempt.getPassword())) {
                // Successful login
                return customer;
            } else {
                // if login failed return error -- need to alert customer customer "Invalid login credentials"
                return ctx.send(StatusCode.UNAUTHORIZED);
            }
        });

        // Register customer route
        post("/api/register", ctx -> {
            Customer newCustomer = ctx.body().to(Customer.class);
            dao.saveCustomer(newCustomer);  // Save the new customer to the database
            return ctx.send(StatusCode.CREATED);
        });

    }
}
