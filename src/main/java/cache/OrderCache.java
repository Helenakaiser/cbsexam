package cache;

import controllers.OrderController;
import java.util.ArrayList;
import model.Order;
import utils.Config;


//TODO: Build this cache and use it.              :FIXED
public class OrderCache {

    //Helenas notes: I create the product list
    private ArrayList<Order> orders;

    //Helenas notes: Ttl defines the time the cache should live
    private long ttl;

    //Helenas notes: Sets when the cache has been created
    private long created;

    //Helenas notes: I created a getOrders method in the Config.java class, that I use here.
    public OrderCache() {
        this.ttl = Config.getOrdersTtl();
    }

    public ArrayList<Order> getOrders(Boolean forceUpdate) {

        //Helenas notes: Normally I would look at the age of the cache to find out if I want to update.
        //Helenas notes: I can also use forceUpdate if i want to clear cache, and if there is no objects on the list I can check for new orders.
        if (forceUpdate
                || ((this.created + this.ttl) >= (System.currentTimeMillis() / 1000L))
                || this.orders == null) {

            //Helenas notes: Here I use the arraylist from OrderController to get orders , because I wish to update.
            ArrayList<Order> orders = OrderController.getOrders();

            //Helenas notes: I set created timestamp and orders for the instance.
            this.orders = orders;
            this.created = System.currentTimeMillis() / 1000L;
        }

        //Helenas notes: At last i return the orders.
        return this.orders;
    }

}
