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

            // get product by ID
            get("/{id}", ctx -> {
                String id = ctx.path("id").value();
                Product product = dao.searchById(id);
                return product != null ? product : ctx.send(StatusCode.NOT_FOUND);
            });

        });

        // Endpoint to get categories and filter
        path("/api/categories", () -> {
            // Get all distinct categories
            get("/", ctx -> dao.getCategories());

            // get products by category
            get("/{category}", ctx -> {
                String category = ctx.path("category").value();
                return dao.filterByCategory(category);
            });
        });
    }
}
