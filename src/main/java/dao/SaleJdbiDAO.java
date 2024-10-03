package dao;

import domain.Sale;
import domain.SaleItem;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.transaction.Transaction;

/**
 *
 * @author Brooklyn
 */

public interface SaleJdbiDAO extends SaleDAO {

    // inserts new sale into sales table,
    // retrieves the genrated sale ID for save method
    @SqlUpdate("INSERT INTO sales (customer_id, date, status) VALUES (:customer.customerId, :date, :status)")
    @GetGeneratedKeys
    Integer insertSale(@BindBean Sale sale);

    // inserts the new SaleItem into the sale_item table, linked by sale ID
    @SqlUpdate("INSERT INTO sale_item (saleid, productId, quantity, salePrice) VALUES (:saleId, :item.product.productId, :item.quantityPurchased, :item.salePrice)")
    void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

    // Updates the stock level of a product in the products table
    @SqlUpdate("UPDATE products SET quantityinstock = quantityinstock - :item.quantityPurchased WHERE productid = :item.product.productId")
    void updateStockLevel(@BindBean SaleItem item);

    @Override
    @Transaction
    default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());

        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
        Integer saleId = insertSale(sale);
        sale.setSaleId(saleId);

        // loop through the sale's items.
        for (SaleItem item : sale.getItems()) {
            insertSaleItem(item, saleId);
            updateStockLevel(item);
        }
    }
}