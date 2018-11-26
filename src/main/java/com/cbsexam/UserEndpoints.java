package com.cbsexam;

import com.google.gson.Gson;
import controllers.UserController;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.User;
//Helenas notes: I import the Encryption class from utils under imports, to get acces to the method encryptDecryptXOR().
import utils.Encryption;
import utils.Log;

@Path("user")
public class UserEndpoints {

  /**
   * @param idUser
   * @return Responses
   */
  @GET
  @Path("/{idUser}")
  public Response getUser(@PathParam("idUser") int idUser) {

    // Use the ID to get the user from the controller.
    User user = UserController.getUser(idUser);

    // TODO: Add Encryption to JSON      :FIXED
    // Convert the user object to json in order to return the object
    String json = new Gson().toJson(user);
    //Helenas notes: I use the method "encryptDecryptXOR()" from the Encryption class to add encyption to JSON.
    json = Encryption.encryptDecryptXOR(json);

    // TODO: What should happen if something breaks down?     :FIXED
    // Return the data to the user
    //Helenas notes: I code an if statement to check the method getUser.
    if (user != null) {
      // Return the user with the status code 200
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      //Helenas notes: I return the response status 400 which is a http message for “failure”.
      return Response.status(400).entity("Could not get user").build();
    }
  }


  /** @return Responses */
  @GET
  @Path("/")
  public Response getUsers() {

    // Write to log that we are here
    Log.writeLog(this.getClass().getName(), this, "Get all users", 0);

    // Get a list of users
    ArrayList<User> users = UserController.getUsers();

    // TODO: Add Encryption to JSON      :FIXED
    // Transfer users to json in order to return it to the user
    String json = new Gson().toJson(users);
    //Helenas notes: I use the method "encryptDecryptXOR()" from the Encryption class to add encyption to JSON.
    json = Encryption.encryptDecryptXOR(json);

    // Return the users with the status code 200
    return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json).build();
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createUser(String body) {

    // Read the json from body and transfer it to a user class
    User newUser = new Gson().fromJson(body, User.class);

    // Use the controller to add the user
    User createUser = UserController.createUser(newUser);

    // Get the user back with the added ID and return it to the user
    String json = new Gson().toJson(createUser);

    // Return the data to the user
    if (createUser != null) {
      // Return a response with status 200 and JSON as type
      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(json).build();
    } else {
      return Response.status(400).entity("Could not create user").build();
    }
  }

  // TODO: Make the system able to login users and assign them a token to use throughout the system.      :FIXED
  @POST
  @Path("/login")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response loginUser(String body) {

    //Helenas notes: (related to the login to-do)
    User user = new Gson().fromJson(body, User.class);

    String token = UserController.loginUser(user);

    if(token!="User could not be created"){

      return
              Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity(token).build();
    }
      else {

    }

    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("Endpoint not implemented yet").build();
  }

  // TODO: Make the system able to delete users       :FIXED
  //Helenas notes: Creating a reference to postman
  @POST
  @Path("/delete")
  @Consumes(MediaType.APPLICATION_JSON)

  public Response deleteUser(String body) {

    User user = new Gson().fromJson(body, User.class);

    String token = UserController.getTokenVerifier(user);

    if (token != null) {
      UserController.deleteUser(user);

      return Response.status(200).type(MediaType.APPLICATION_JSON_TYPE).entity("The user has been deleted").build();
    } else
    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("  ").build();
  }


  // TODO: Make the system able to update users
  public Response updateUser(String x) {

    // Return a response with status 200 and JSON as type
    return Response.status(400).entity("Endpoint not implemented yet").build();
  }
}
