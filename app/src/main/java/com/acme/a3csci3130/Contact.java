package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class Contact implements Serializable {

    public  String uid;
    public  String name;
    public  String email;

    /**
     * Initializes a new instance of the Contact class.
     */
    public Contact() {
        // Default constructor required for calls to DataSnapshot.getValue
    }


    /**
     * Initializes a new instance of the Contact class.
     * @param uid The user ID of the contact.
     * @param name The name fo the contact.
     * @param email The email of the contact.
     */
    public Contact(String uid, String name, String email){
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    /**
     * Creates and returns a map of all the data in the object.
     * @return A hashmap containing all fields for Firebase.
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("email", email);

        return result;
    }
}
