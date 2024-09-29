package dao;

import domain.Product;
import java.util.Collection;

/**
 * @author Mark George
 */
public interface ProductDAO {

	Collection<Product> filterByCategory(String category);

	Collection<String> getCategories();

	Collection<Product> getProducts();

	void removeProduct(String productId);

	void saveProduct(Product product);

	Product searchById(String id);

}
