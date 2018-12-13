////////////////////////////////////////////////////////////////////
// Enrico Muraro 1143775
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;

import java.util.List;

public class BillImplementation implements RestaurantBill {

    @Override
    public double getOrderPrice(List<MenuItem> itemsOrdered) throws RestaurantBillException {
        if(itemsOrdered.size() >= 20) {
            throw new RestaurantBillException("Error. More than 20 items in the order");
        }

        double total = 0;
        int pizzaCount = 0;
        double cheapestPizza = -1;

        for (MenuItem i : itemsOrdered) {
            //count the number of pizzas
            if(i.getType()==MenuItem.itemType.PIZZA) {
                //find the cheapest pizza
                if(i.getPrice() < cheapestPizza || cheapestPizza == -1) {
                    cheapestPizza = i.getPrice();
                }
                pizzaCount++;
            }
            //sum the total price
            total += i.getPrice();
        }

        //discount the cheapest pizza if there are 10 or more
        if(pizzaCount >= 10) {
            total -= cheapestPizza;
        }

        //if the total is over 100 discount 5%
        if(total > 100) {
            total *= 0.95;
        }

        return total;

    }
}
