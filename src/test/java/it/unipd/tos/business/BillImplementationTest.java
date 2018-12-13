////////////////////////////////////////////////////////////////////
// Enrico Muraro 1143775
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

public class BillImplementationTest {

    @Test
    public void getOrderPrice_moreThan10Pizzas_cheapestIsFree()
            throws RestaurantBillException {

        List<MenuItem> orders = new ArrayList<>();
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",5.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"diavola",6.50));
        orders.add(new MenuItem(MenuItem.itemType.PRIMO,"pasta",2.00));
        orders.add(new MenuItem(MenuItem.itemType.PRIMO,"marinara",4.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"quattro stagioni",7.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"viennese",6.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"bufala",8.50));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",7.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",2.50));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",6.50));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",8.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",8.50));

        BillImplementation bill = new BillImplementation();

        Assert.assertEquals(69, bill.getOrderPrice(orders),0.0f);
    }

    @Test
    public void getOrderPrice_lessThan10Pizzas_normalSum()
            throws RestaurantBillException {

        List<MenuItem> orders = new ArrayList<>();
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",5.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"diavola",6.50));
        orders.add(new MenuItem(MenuItem.itemType.PRIMO,"pasta",2.00));
        orders.add(new MenuItem(MenuItem.itemType.PRIMO,"marinara",4.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"quattro stagioni",7.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"viennese",6.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"bufala",8.50));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",7.00));
        orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",2.50));

        BillImplementation bill = new BillImplementation();

        Assert.assertEquals(48.5, bill.getOrderPrice(orders),0.0f);
    }

    @Test
    public void getOrderPrice_moreThan100Euros_discountTotal()
            throws RestaurantBillException {

        List<MenuItem> orders = new ArrayList<>();
        for (int i=0; i<12; i++) {
            orders.add(new MenuItem(MenuItem.itemType.PRIMO,"carbonara",10.00));
        }

        BillImplementation bill = new BillImplementation();
        Assert.assertEquals(114, bill.getOrderPrice(orders),0.0f);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getOrderPrice_moreThan20Items_throwsException()
            throws RestaurantBillException {

        expectedException.expect(RestaurantBillException.class);
        expectedException.expectMessage("Error. More than 20 items in the order");


        List<MenuItem> orders = new ArrayList<>();
        for (int i=0; i<21; i++) {
            orders.add(new MenuItem(MenuItem.itemType.PIZZA,"margherita",5.00));
        }

        BillImplementation bill = new BillImplementation();
        bill.getOrderPrice(orders);

    }
}