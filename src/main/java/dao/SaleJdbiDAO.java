package dao;

import domain.Sale;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.Collection;

/**
 *
 * @author Brooklyn
 */

public interface SaleJdbiDAO extends SaleDAO {

    @Override
    @SqlUpdate("INSERT INTO Sale (SaleDate, CustomerId, TotalAmount) VALUES (:saleDate, :customerId, :totalAmount)")
    void saveSale(@BindBean Sale sale);

    @Override
    @SqlQuery("SELECT * FROM Sale WHERE SaleId = :saleId")
    @RegisterBeanMapper(Sale.class)
    Sale getSaleById(@Bind("saleId") int saleId);

    @Override
    @SqlQuery("SELECT * FROM Sale")
    @RegisterBeanMapper(Sale.class)
    Collection<Sale> getAllSales();

    @Override
    @SqlUpdate("DELETE FROM Sale WHERE SaleId = :saleId")
    void deleteSale(@Bind("saleId") int saleId);
}
