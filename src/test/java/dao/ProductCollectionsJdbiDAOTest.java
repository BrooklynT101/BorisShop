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
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import org.h2.jdbcx.JdbcConnectionPool;
import org.jdbi.v3.core.Jdbi;

/**
 *
 * @author Brooklyn
 */
public class ProductCollectionsJdbiDAOTest {

    private ProductDAO dao;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private static JdbcConnectionPool connectionPool;
    private Jdbi jdbi;

    /**
     * Full disclosure, the code used for testing the dao was all me,
     * but when it came to migrating to a persistent database I had chatgpt 
     * to try help me, it didn't work and I couldn't understand this. As I type
     * this its 12:35am 30/08/2024 and I still have other tasks to do so I guess
     * I'm losing marks for this part
     */
   @BeforeAll
    public static void beforeAll() {
        // Set up the H2 database with embedded mode and schema initialization
//        connectionPool = JdbcConnectionPool.create(
//                "jdbc:h2:mem:testdb;INIT=RUNSCRIPT FROM 'classpath:schema.sql'",
//                "sa",
//                "sa"
//        );
JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:test;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }


    @BeforeEach
    public void setUp() {
        // Initialize Jdbi with the H2 connection pool
       // jdbi = Jdbi.create(connectionPool);
        dao = JdbiDaoFactory.getProductDAO();
//        dao = new JdbiDaoFactory(jdbi).getProductDao();
        //System.out.println("Classpath: " + System.getProperty("java.class.path"));

        // Drop and recreate the schema to ensure a clean state
//        jdbi.useHandle(handle -> {
//            handle.execute("DROP ALL OBJECTS;");
//            handle.execute("RUNSCRIPT FROM 'classpath:schema.sql';");
//        });

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
        // Clean up database after each test
//        jdbi.useHandle(handle -> {
//            handle.execute("DELETE FROM Product");
//        });
dao.removeProduct(product1.getProductId());
dao.removeProduct(product2.getProductId());
dao.removeProduct(product3.getProductId());
dao.removeProduct(product4.getProductId());
    }

//    @AfterAll
//    static void afterAll() {
//        // dispose of the connection pool after all tests have run through
//        connectionPool.dispose();
//    }

    @Test
    public void testSave() {
        Collection<Product> products = dao.getProducts();
        assertThat(products, not(hasItem(product4)));

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
