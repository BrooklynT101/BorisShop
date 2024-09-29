/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Product;
import dao.ProductCollectionsDAO;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Brooklyn
 */
public class ProductCollectionsDAOTest {

    private ProductDAO dao;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;

    

    @BeforeEach
    public void setUp() {
        
        dao = new ProductCollectionsDAO();

        product1 = new Product();
        product1.setName("Pandionia Perfume");
        product1.setDescription("Smells like lemons and strawberry");
        product1.setCategory("Fragrencies");
        product1.setProductId("12");
        product1.setListPrice(new BigDecimal(43.3));
        product1.setQuantityInStock(new BigDecimal(2));

        product2 = new Product();
        product2.setName("Bagingy");
        product2.setDescription("Tiny Itern shaped object I found outside");
        product2.setCategory("Toys");
        product2.setProductId("534");
        product2.setListPrice(new BigDecimal(69.69));
        product2.setQuantityInStock(new BigDecimal(2));

        product3 = new Product();
        product3.setName("Pee-Be-Gone");
        product3.setDescription("Tired of wetting the bed in the night and having it smell?");
        product3.setCategory("Fragrencies");
        product3.setProductId("933");
        product3.setListPrice(new BigDecimal(1.00));
        product3.setQuantityInStock(new BigDecimal(2));

        product4 = new Product();
        product4.setName("Snake Gun");
        product4.setDescription("Pretty self-explanatory");
        product4.setCategory("Toys");
        product4.setProductId("700");
        product4.setListPrice(new BigDecimal(1400.49));
        product4.setQuantityInStock(new BigDecimal(32));

        product5 = new Product();
        product5.setName("Donut");
        product5.setDescription("Donut of Donut orgins, flavored like a Donut, topped with bits of Donuts");
        product5.setCategory("Food");
        product5.setProductId("808");
        product5.setListPrice(new BigDecimal(6.00));
        product5.setQuantityInStock(new BigDecimal(600));

        dao.saveProduct(product1);
        dao.saveProduct(product2);
        dao.saveProduct(product3);
    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(product1.getProductId());
        dao.removeProduct(product2.getProductId());
        dao.removeProduct(product3.getProductId());
        dao.removeProduct(product4.getProductId());
        dao.removeProduct(product5.getProductId());
    }

    @Test
    public void testSave() {
        Collection<Product> products = dao.getProducts();
        assertThat(products, hasItem(product3));

        dao.saveProduct(product4);
        assertThat(products, hasItem(product4));
    }

    @Test
    public void testDelete() {
        Collection<Product> products = dao.getProducts();
        assertNotNull(products, "The product list shouldn't be empty");
        assertThat(products, not(empty()));

        // remove an item, check everything else is still there
        dao.removeProduct(product1.getProductId());
        assertThat(products, not(hasItem(product1)));
        assertThat(products, hasItems(product2, product3));

        dao.removeProduct(product2.getProductId());
        assertThat(products, not(hasItem(product2)));
    }

    @Test
    public void testGetAll() {
        Collection<Product> products = dao.getProducts();
        assertThat(products, hasItems(product1, product2, product3));
        assertThat(products, not(hasItems(product4, product5)));
    }

    @Test
    public void testGetCategories() {
        Collection<String> productCategories = dao.getCategories();

        assertThat(productCategories, hasItems("Toys", "Fragrencies"));
        assertThat(productCategories, not(hasItems("Food")));

    }

    @Test
    public void testGetProductByID() {
        Product product = dao.searchById("12");
        assertThat(product, is(notNullValue()));
        assertThat(product, is(product1));
        assertThat(product, hasProperty("name", equalTo("Pandionia Perfume")));
        assertThat(product, hasProperty("category", equalTo("Fragrencies")));

        Product fakeProduct = dao.searchById("4545");
        assertThat(fakeProduct, is(nullValue()));
    }

    @Test
    public void testFilterByCategory() {
        dao.saveProduct(product5);
        assertThat(dao.filterByCategory("Food"), hasItems(product5));
        assertThat(dao.filterByCategory("Toys"), not(hasItems(product2, product4)));
    }
}
