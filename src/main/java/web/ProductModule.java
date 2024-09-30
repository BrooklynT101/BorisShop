package web;

import dao.ProductDAO;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import io.jooby.annotation.Path;

@Path("/api/products")
public class ProductModule extends Jooby {

    public ProductModule(ProductDAO dao) {

        // CGPT suggested using a "true" path to fix a display error
        // Ensure path starts with "/api/products"
        path("/api/products", () -> {
            // Get all products
            get("/", ctx -> dao.getProducts());

            // Get product by ID
            get("/{id}", ctx -> {
                String id = ctx.path("id").value();
                Product product = dao.searchById(id);
                return product != null ? product : ctx.send(StatusCode.NOT_FOUND);
            });

            // Create a new product
            post("/", ctx -> {
                Product product = ctx.body().to(Product.class);
                dao.saveProduct(product);
                return ctx.send(StatusCode.CREATED);
            });
        });

//        //get all products
//        get("/api/products", ctx -> dao.getProducts());
//        //get product by id
//        get("/api/products/{id}", ctx -> {
//
//            String id = ctx.path("id").value();
//
//            Product product = dao.searchById(id);
//
//            if (product == null) {
//                // no product with that ID found, so return a 404/Not Found error
//                return ctx.send(StatusCode.NOT_FOUND);
//            } else {
//                return product;
//            }
//        });
//
//        /**
//         * or you could use get("/", ctx -> { return "Hello World"; });
//         */
//        // creating a new product
//        post("/api/products", ctx -> {
//            Product product = ctx.body().to(Product.class);
//            dao.saveProduct(product);
//            return ctx.send(StatusCode.CREATED);
//        });
    }
}
