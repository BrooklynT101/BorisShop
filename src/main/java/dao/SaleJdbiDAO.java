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

    // inserts new sale into Sale table,
    // retrieves the genrated sale ID for save method
    @SqlUpdate("INSERT INTO Sale (date, customerId, status, total) VALUES (:date, :customer.customerId, :status, :saleTotal)")
    @GetGeneratedKeys
    Integer insertSale(@BindBean Sale sale);

    // inserts the new SaleItem into the sale_item table, linked by sale ID
    @SqlUpdate("INSERT INTO Sale_item (saleid, productId, quantity, salePrice) VALUES (:saleId, :productId, :quantityPurchased, :salePrice)")
    void insertSaleItem(
            @Bind("saleId") int saleId,
            @Bind("productId") String productId,
            @Bind("quantityPurchased") int quantityPurchased,
            @Bind("salePrice") BigDecimal salePrice);

    // updates the stock level of a product in the Product table
    @SqlUpdate("UPDATE Product SET quantityinstock = quantityinstock - :quantityPurchased WHERE productid = :productId")
    void updateStockLevel(
            @Bind("quantityPurchased") int quantityPurchased,
            @Bind("productId") String productId);

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
            boolean debugging = true;
            if (debugging) {
                System.out.println("SaleItem: \nSaleID: " + saleId + "\nProduct: " + item.getProduct() + "\nQuantity Purchased: " + item.getQuantityPurchased() + "\nProduct List Price:" + item.getProduct().getListPrice());
            }
            insertSaleItem(saleId, item.getProduct().getProductId(), item.getQuantityPurchased(), item.getProduct().getListPrice());
            updateStockLevel(item.getQuantityPurchased(), item.getProduct().getProductId());
        }
    }
}
