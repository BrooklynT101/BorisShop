package dao;

import domain.Product;
import domain.SaleItem;
import java.util.Collection;

/**
 *
 * @author Brooklyn
 */
public interface SaleItemDAO {

    void addSaleItem(SaleItem saleItem);

    Collection<Product> getItemsBySaleId(int saleId);

    void removeSaleItem(int saleId, int productId);
}