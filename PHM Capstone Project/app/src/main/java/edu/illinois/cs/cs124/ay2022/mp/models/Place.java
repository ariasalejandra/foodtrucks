package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.List;
import java.util.ArrayList;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude = 1000;

  public double getLatitude() {
    return latitude;
  }

  private double longitude = 1000;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  public static List<Place> search(final List<Place> places, final String search) {
    // Reject null and empty values for both parameters
    if (places == null || search == null) {
      throw new IllegalArgumentException();
    } else if (search.trim().length() == 0 || places.size() == 0) {
      return places;
    }
    // Create a new list that will eventually contain the specified information
    List<Place> newPlaces = new ArrayList<>();
    // Trim the string parameter
    String trimSearch = search.trim().toLowerCase();
    // Go through each place in the list
    for (int i = 0; i < places.size(); i++) {
      // Get the description of each place
      String des = places.get(i).getDescription();
      // Create a new description with the correct modifications
      StringBuilder newDes = new StringBuilder();
      // Go through each character in the description
      for (int j = 0; j < des.length(); j++) {
        char x = des.charAt(j);
        // Change the characters to appropiate values
        if (x == '.' || x == '!' || x == '?' || x == ',' || x == ':' || x == ';' || x == '/') {
          newDes.append(' ');
        } else if (Character.isAlphabetic(x) || x == ' ' || Character.isDigit(x)) {
          newDes.append(x);
        }
      }
      // Change the new description to all lowercase
      String y = newDes.toString().toLowerCase();
      // Split the lines of the description into individual strings
      String[] z = y.split("\n");
      for (String j : z) {
        // Split the individual strings into words
        String[] a = j.split(" ");
        for (String k : a) {
          // Add the place into the list if the word and string parameter are equal
          if (k.equals(trimSearch)) {
            newPlaces.add(places.get(i));
            // Immediately exit the loop once one instance of the word is found
            break;
          }
        }
      }
    }
    return newPlaces;
  }
}

