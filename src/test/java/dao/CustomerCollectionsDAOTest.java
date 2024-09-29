/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.Customer;
import java.util.ArrayList;
import java.util.Collection;
import org.h2.jdbcx.JdbcConnectionPool;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Brooklyn
 */
public class CustomerCollectionsDAOTest {

    private CustomerDAO dao;
    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private Customer customer4;
    private Customer customer5;
private static JdbcConnectionPool connectionPool;
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
        
//        dao = new CustomerCollectionsDAO();
        dao = JdbiDaoFactory.getCustomerDAO();

        customer1 = new Customer();
        customer1.setUsername("PandioniaPerfume");
        customer1.setFirstName("Poppy");
        customer1.setSurname("Pendle");
        customer1.setCustomerId(1);
        customer1.setEmailAddress("poppy@gmail.com");
        customer1.setShippingAddress("123 SomeStreet, Lanceshire");
        customer1.setPassword("thePower");

        customer2 = new Customer();
        customer2.setUsername("TheCourage");
        customer2.setFirstName("Cat");
        customer2.setSurname("Campbell");
        customer2.setCustomerId(2);
        customer2.setEmailAddress("cat@gmail.com");
        customer2.setShippingAddress("42 High Street");
        customer2.setPassword("marvelous");

        customer3 = new Customer();
        customer3.setUsername("MissMabel");
        customer3.setFirstName("Mabel");
        customer3.setSurname("Magic");
        customer3.setCustomerId(3);
        customer3.setEmailAddress("magic@gmail.com");
        customer3.setShippingAddress("321 London St");
        customer3.setPassword("magic");

        customer4 = new Customer();
        customer4.setUsername("Warrior");
        customer4.setFirstName("Krieger");
        customer4.setSurname("Algernop");
        customer4.setCustomerId(4);
        customer4.setEmailAddress("pigley@gmail.com");
        customer4.setShippingAddress("432 Cherry Street");
        customer4.setPassword("fortKickAss");

        customer5 = new Customer();
        customer5.setUsername("MrBagingy");
        customer5.setFirstName("Fruit");
        customer5.setSurname("Loops");
        customer5.setCustomerId(5);
        customer5.setEmailAddress("itern@gmail.com");
        customer5.setShippingAddress("124 SomeStreet");
        customer5.setPassword("julien");

        dao.saveCustomer(customer1);
        dao.saveCustomer(customer2);
        dao.saveCustomer(customer3);
    }

    @AfterEach
    public void tearDown() {
        dao.removeCustomer(customer1.getUsername());
        dao.removeCustomer(customer2.getUsername());
        dao.removeCustomer(customer3.getUsername());
        dao.removeCustomer(customer4.getUsername());
        dao.removeCustomer(customer5.getUsername());
    }

    @Test
    public void testSave() {
        assertNotNull(dao.getCustomerByUsername(customer3.getUsername()));

        dao.saveCustomer(customer4);
        Customer customerCheck = dao.getCustomerByUsername(customer4.getUsername());
        assertNotNull(customerCheck);
        assertTrue(customer4.equals(customerCheck));
    }

    @Test
    public void testRemove() {
        assertNotNull(dao.getCustomerByUsername(customer1.getUsername()), "Customer 1 shouldn't be missing");
        assertNotNull(dao.getCustomerByUsername(customer2.getUsername()), "Customer 2 shouldn't be missing");
        assertNotNull(dao.getCustomerByUsername(customer3.getUsername()), "Customer 3 shouldn't be missing");

        // remove an item, check everything else is still there
        dao.removeCustomer(customer1.getUsername());
        assertNull(dao.getCustomerByUsername(customer1.getUsername()), "Customer 1 should've be removed");
        Collection<Customer> customers = new ArrayList<>();
        customers.add(dao.getCustomerByUsername(customer2.getUsername()));
        customers.add(dao.getCustomerByUsername(customer3.getUsername()));
        assertThat(customers, hasItems(customer2, customer3));

        dao.removeCustomer(customer2.getUsername());
        assertNull(dao.getCustomerByUsername(customer2.getUsername()), "Customer 2 should've be removed");
    }

    @Test
    public void testGetCustomerByUsername() {
        Customer customer = dao.getCustomerByUsername("PandioniaPerfume");
        assertThat(customer, is(notNullValue()));
        assertThat(customer, is(customer1));
    
        assertThat(customer, hasProperty("username", equalTo("PandioniaPerfume")));
        assertThat(customer, hasProperty("firstName", equalTo("Poppy")));
        assertThat(customer, hasProperty("surname", equalTo("Pendle")));
        assertThat(customer, hasProperty("password", equalTo("thePower")));
        assertThat(customer, hasProperty("emailAddress", equalTo("poppy@gmail.com")));
        assertThat(customer, hasProperty("shippingAddress", equalTo("123 SomeStreet, Lanceshire")));
        
        Customer fakeCustomer = dao.getCustomerByUsername("4545");
        assertNull(fakeCustomer);
    }

    @Test
    public void testCheckCustomerCredientials() {
        dao.saveCustomer(customer5);
        assertTrue(dao.checkCustomerCredentials("MrBagingy", "julien"));
        assertFalse(dao.checkCustomerCredentials("MrBagingy", "beans"));
        assertFalse(dao.checkCustomerCredentials("MrFake", "julien"));
        assertFalse(dao.checkCustomerCredentials("MrFake", "beans"));
    }
    
    @Test
    public void testEquals() {
        dao.saveCustomer(customer5);
        Customer customer = dao.getCustomerByUsername("MrBagingy");
        assertTrue(customer5.equals(customer), "The retrieved customer should be the same as customer5");
        assertFalse(customer4.equals(customer), "The retrieved customer should not be the same as customer4");
    }
}
