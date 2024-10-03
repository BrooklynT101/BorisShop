package domain;

import java.math.BigDecimal;

/**
 * @author Mark George
 */
public class SaleItem {

    private Product product;
    private int quantityPurchased;
    private BigDecimal salePrice;

    public SaleItem() {
    }

    public SaleItem(Product product, int quantityPurchased) {
        this.product = product;
        this.quantityPurchased = quantityPurchased;
        this.salePrice = product.getListPrice();
    
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public BigDecimal getItemTotal() {
        return this.product.getListPrice().multiply(BigDecimal.valueOf(quantityPurchased));
    }

    @Override
    public String toString() {
        return "SaleItem{" + "product=" + product + ", quantity=" + quantityPurchased + ", Product list price"+ salePrice +'}';
    }

}
