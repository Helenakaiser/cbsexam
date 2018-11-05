package cache;

import controllers.UserController;
import model.User;
import utils.Config;

import java.util.ArrayList;

//TODO: Build this cache and use it.      :FIXED
public class UserCache {

        //Helenas notes: I create the product list
        private ArrayList<User> users;

        //Helenas notes: Ttl defines the time the cache should live
        private long ttl;

        //Helenas notes: Sets when the cache has been created
        private long created;

        //Helenas notes: I created a getOrders method in the Config.java class, that I use here.
        public UserCache() {
            this.ttl = Config.getUsersTtl();
        }

        public ArrayList<User> getUsers(Boolean forceUpdate) {

            //Helenas notes: Normally I would look at the age of the cache to find out if I want to update.
            //Helenas notes: I can also use forceUpdate if i want to clear cache, and if there is no objects on the list I can check for new users.
            if (forceUpdate
                    || ((this.created + this.ttl) >= (System.currentTimeMillis() / 1000L))
                    || this.users.isEmpty()) {

                //Helenas notes: Here I use the arraylist from UserController to get users , because I wish to update.
                ArrayList<User> users = UserController.getUsers();

                //Helenas notes: I set created timestamp and users for the instance.
                this.users = users;
                this.created = System.currentTimeMillis() / 1000L;
            }

            //Helenas notes: At last i return the users.
            return this.users;
        }

    }

