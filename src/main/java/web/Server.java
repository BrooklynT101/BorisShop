package web;

import dao.CustomerDAO;
import dao.JdbiDaoFactory;
import dao.ProductDAO;
import dao.SaleDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {

    public Server() {

        install(new GsonModule());

        ProductDAO productDAO = JdbiDaoFactory.getProductDAO();
        CustomerDAO customerDAO = JdbiDaoFactory.getCustomerDAO();
        SaleDAO saleDAO = JdbiDaoFactory.getSaleDAO();

        mount(new StaticAssetModule());
        
        // mount API route for products
        mount(new ProductModule(productDAO));
        
        // mount API route for customers
        mount(new CustomerModule(customerDAO));
        
        // mount API route for sales
        mount(new SaleModule(saleDAO));
    }

    public static void main(String[] args) {
        System.out.println("\nStarting Server.");
        new Server()
                .setServerOptions(new ServerOptions().setPort(8087))
                .start();
    }

}
