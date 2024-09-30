package web;

import dao.JdbiDaoFactory;
import dao.ProductDAO;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.gson.GsonModule;

public class Server extends Jooby {

    public Server() {

        install(new GsonModule());

        ProductDAO productDAO = JdbiDaoFactory.getProductDAO();

        mount(new StaticAssetModule());
        
        // mount API route for products
        mount(new ProductModule(productDAO));
    }

    public static void main(String[] args) {
        System.out.println("\nStarting Server.");
        new Server()
                .setServerOptions(new ServerOptions().setPort(8087))
                .start();
    }

}
