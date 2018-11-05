package com.cbsexam;
import com.google.gson.Gson;
import controllers.ReviewController;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Review;
//Helenas notes: I import the Encryption class from utils under imports, to get acces to the method encryptDecryptXOR().
import utils.Encryption;

@Path("search")
public class ReviewEndpoints {

  /**
   * @param reviewTitle
   * @return Responses
   */
  @GET
  @Path("/title/{title}")
  public Response search(@PathParam("title") String reviewTitle) {

    // Call our controller-layer in order to get the order from the DB
    ArrayList<Review> reviews = ReviewController.searchByTitle(reviewTitle);

    // TODO: Add Encryption to JSON    :FIXED
    // We convert the java object to json with GSON library imported in Maven
    String json = new Gson().toJson(reviews);
    //Helenas notes: I use the method "encryptDecryptXOR()" from the Encryption class to add encyption to JSON.
    json = Encryption.encryptDecryptXOR(json);

    // Return a response with status 200 and JSON as type
    return Response.status(200).type(MediaType.APPLICATION_JSON).entity(json).build();
  }


}
