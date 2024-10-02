package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author Brooklyn
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlQuery("SELECT * FROM Product WHERE ProductID = :id")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("id") String id);

    @Override
    @SqlUpdate("INSERT INTO Product (ProductID, Name, Description, Category, ListPrice, QuantityInStock) VALUES (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlUpdate("DELETE FROM Product WHERE ProductId = :productId")
    public void removeProduct(@Bind("productId") String productId);

    @Override
    @SqlQuery("SELECT * FROM Product")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();

    @Override
    @SqlQuery("SELECT DISTINCT Category FROM Product")
    public Collection<String> getCategories();

    @Override
//    @SqlQuery("SELECT DISTINCT Category FROM Product WHERE Category = :category")
    @SqlQuery("SELECT * FROM Product WHERE Category = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);

}
