package web;

import dao.SaleDAO;
import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import io.jooby.annotation.Path;

/**
 *
 * @author Brooklyn
 */
@Path("/api/sales")
public class SaleModule extends Jooby {

    public SaleModule(SaleDAO dao) {

        // create and save sale
        post("/", ctx -> {
            Sale sale = ctx.body().to(Sale.class);
            dao.saveSale(sale);
            return ctx.send(StatusCode.CREATED);
        });
    }

}
