package domain;

import java.math.BigDecimal;

/**
 * @author Mark George
 */
public class SaleItem {

    private Product product;
    private int quantity;

    public SaleItem() {
    }

    public SaleItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityPurchased() {
        return quantity;
    }

    public void setQuantityPurchased(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getItemTotal() {
        return this.product.getListPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return "SaleItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }

}
