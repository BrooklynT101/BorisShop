package dao;

import domain.SaleItem;
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
public interface SaleItemJdbiDAO extends SaleItemDAO {

    @Override
    @SqlUpdate("INSERT INTO Sale_Item (SaleId, ProductId, Quantity) VALUES (:saleId, :productId, :quantity)")
    void addSaleItem(@BindBean SaleItem saleItem);

//    used CGPT to help with the inner joining
    @Override
    @SqlQuery("SELECT * FROM Product INNER JOIN Sale_Item ON Product.ProductId = Sale_Item.ProductId WHERE SaleId = :saleId")
    @RegisterBeanMapper(Product.class)
    Collection<Product> getItemsBySaleId(@Bind("saleId") int saleId);

    @Override
    @SqlUpdate("DELETE FROM Sale_Item WHERE SaleId = :saleId AND ProductId = :productId")
    void removeSaleItem(@Bind("saleId") int saleId, @Bind("productId") int productId);
}
