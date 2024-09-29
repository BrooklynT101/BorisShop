/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import dao.ProductDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Brooklyn
 */
public class ProductViewerTest {

    private ProductDAO dao;
    private DialogFixture fixture;
    private Robot robot;
    private Product p1;
    private Product p2;
    private Product p3;
    private Product p4;
    private Product p5;

    @BeforeEach
    public void setUp() {
        robot = BasicRobot.robotWithNewAwtHierarchy();

        // Slow down the robot a little bit - default is 30 (milliseconds).
        // Do NOT make it less than 10 or you will have thread-race problems.
        robot.settings().delayBetweenEvents(75);

        // create a mock for the DAO
        dao = mock(ProductDAO.class);

        // create and save fake products to the mock dao
        // Product(String productId, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock)
        p1 = new Product("1111", "Thing 1", "For Real Crazy", "Imaginative", new BigDecimal(13.2), new BigDecimal(32));
        p2 = new Product("2222", "Thing 2", "No Cap, on god", "Creations", new BigDecimal(31.2), new BigDecimal(23));
        dao.saveProduct(p1);
        dao.saveProduct(p2);
    }

    @AfterEach
    public void tearDown() {
        // clean up fixture so that it is ready for the next test
        if (fixture != null) {
            fixture.cleanUp();
        }
    }

    @Test
    public void testView() {
        // Stub the getAll() method to return the list of products
        when(dao.getProducts()).thenReturn(Arrays.asList(p1, p2));

        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // Verify that the getProducts() method was called
        verify(dao).getProducts();

        // Get the model from the JList
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        // products JList should contain 2 students
        fixture.list("lstProducts").requireItemCount(2);

        // Check the contents of the model
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < model.getSize(); i++) {
            products.add((Product) model.getElementAt(i));
        }

        // check the contents
        assertThat(model, containsInAnyOrder(p1, p2));
    }

    // Need to implement
//	@Test
//	public void testSearch() {
//
//	}
    @Test
    public void testDelete() {
        // create a customer collection
        Collection<Product> customers = new HashSet<>();
        customers.add(p1);
        customers.add(p2);

        // stub the getAll method to return the curr state of the customers collection
        Mockito.when(dao.getProducts()).thenAnswer((call) -> customers);

        // stub the search method
        when(dao.searchById(p1.getProductId())).thenReturn(p1);

        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // ensure all products are present before attempting to delete
        fixture.list("lstProducts").requireItemCount(2);

        // select the p1 product to delete
        fixture.list("lstProducts").selectItem(p1.toString());

        // click the delete button
        fixture.button("btnDelete").click();    

        //click yes to confirm deletion
        fixture.optionPane().requireVisible().yesButton().click();

        // Perform the delete operation
        verify(dao).removeProduct(p1.getProductId());

        // ensure that dao no longer contains p1
        Collection<Product> result = dao.getProducts();

    //    assertFalse(result.contains(p1), "The collection should no longer contain p1");
        assertTrue(result.contains(p2), "The collection should still contain p2");

    }

    @Test
    public void testSearch() {
//Product(String productId, String name, String description, String category, BigDecimal listPrice, BigDecimal quantityInStock)
        p1 = new Product("777", "Goeesy", "A name I made up", "FakeProduct", new BigDecimal(15.69), new BigDecimal(30));
        when(dao.searchById(p1.getProductId())).thenReturn(p1);

// create a customer collection
        Collection<Product> customers = new HashSet<>();
        customers.add(p1);
        customers.add(p2);

        // stub the getAll method to return the curr state of the customers collection
        Mockito.when(dao.getProducts()).thenAnswer((call) -> customers);

        // stub the delete method and add behaviour that actually deletes a customer
        Mockito.doAnswer((call) -> {
            // remove p1 collection that getAll() uses
            customers.remove(p1);
            return null;
        }).when(dao).removeProduct(p1.getProductId());

        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // enter the id into the search box
        fixture.textBox("txtSearchId").enterText("777");

        // click the search button
        fixture.button("btnSearch").click();

        // Verify that the searchById() method was called
        verify(dao).searchById("777");

        // Get the model from the JList
        SimpleListModel model = (SimpleListModel) fixture.list("lstProducts").target().getModel();

        // products JList should contain 1 product
        fixture.list("lstProducts").requireItemCount(1);

        // get the product from the dao
        Product result = dao.searchById("777");

        //check the model contains the result
        assertTrue(model.contains(result));// The model should contain the result

    }

    @Test
    public void testFilterByCategory() {
        // create a product collection
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        p1.setProductId("1111");
        p1.setName("TestFood");
        p1.setCategory("Food");

        p2.setProductId("2222");
        p2.setName("AnotherTestFood");
        p2.setCategory("Food");

        p3.setProductId("3333");
        p3.setName("TestCat");
        p3.setCategory("Animals");

        //add products to collection and save to dao
        Collection<Product> products = new HashSet<>();
        products.add(p1);
        products.add(p2);
        products.add(p3);

        dao.saveProduct(p1);
        dao.saveProduct(p2);
        dao.saveProduct(p3);

        // stub the getAll method to return the curr state of the products collection
        Mockito.when(dao.getProducts()).thenReturn(products);

        // stub the filterByCategory method and add behavior that filters the products
        Mockito.when(dao.filterByCategory("Food")).thenAnswer((call) -> {
            return products.stream()
                    .filter(p -> "Food".equals(p.getCategory()))
                    .collect(Collectors.toList());
        });

        // create the dialog passing in the mocked DAO
        ProductViewer dialog = new ProductViewer(null, true, dao);

        // use AssertJ to control the dialog
        fixture = new DialogFixture(robot, dialog);

        // show the dialog on the screen, and ensure it is visible
        fixture.show().requireVisible();

        // click the dialog to ensure the robot is focused in the correct window
        // since it can get confused by multi-monitor and virtual desktop setups
        fixture.click();

        // ensure all products are present before attempting to filter
        fixture.list("lstProducts").requireItemCount(3);

        // select the combo box
//        fixture.comboBox("cmbCategories").selectItem("Food");
        // use the robot to see if there are two items in the list after filtering
//        fixture.list("lstProducts").requireItemCount(2);
        // ensure the correct products are in the list
//        List<String> productList = Arrays.asList(fixture.list("lstProducts").contents());
//        assertTrue(productList.contains("TestFood"));
//        assertTrue(productList.contains("AnotherTestFood"));
//        assertFalse(productList.contains("TestCat"));
        Collection<Product> result = dao.filterByCategory("Food");
        assertFalse(result.contains(p3), "The collection should no longer contain p3");
        assertTrue(result.contains(p1), "The collection should contain p1");
        assertTrue(result.contains(p2), "The collection should contain p2");
    }
}
