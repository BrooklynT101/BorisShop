package dao;

import domain.Sale;
import java.util.Collection;

/**
 *
 * @author Brooklyn
 */
public interface SaleDAO {

    void saveSale(Sale sale);

    Sale getSaleById(int saleId);

    Collection<Sale> getAllSales();

    void deleteSale(int saleId);
}
