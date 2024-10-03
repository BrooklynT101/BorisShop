package domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Mark George
 */
public class Sale {

	private Integer saleId;
	private transient LocalDateTime date;
	private Customer customer;
	private String status;
	private BigDecimal saleTotal;

	private Collection<SaleItem> items = new HashSet<>();

	public Sale() {
	}

	public Sale(LocalDateTime date, Customer customer, String status, BigDecimal saleTotal) {
		this.date = date;
		this.customer = customer;
		this.status = status;
                this.saleTotal = saleTotal;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getSaleId() {
		return saleId;
	}
        
        //set sale total if needed
        public void setSaleTotal(BigDecimal saleTotal) {
		this.saleTotal = saleTotal;
	}

        //return the sale total
	public BigDecimal getSaleTotal() {
		return saleTotal;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Collection<SaleItem> getItems() {
		return items;
	}

	public void setItems(Collection<SaleItem> items) {
		this.items = items;
	}

	public void addItem(SaleItem item) {
		items.add(item);
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;

		for (SaleItem item : items) {
			total = total.add(item.getItemTotal());
		}

		return total;
	}

	@Override
	public String toString() {
		return "Sale{" + "saleId=" + saleId + ", date=" + date + ", customer=" + customer + ", status=" + status + ", items=" + items + '}';
	}

}
