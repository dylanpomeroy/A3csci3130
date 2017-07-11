package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by elber on 7/10/2017.
 */

public class Business implements Serializable {
    public String id;
    public String number;
    public String name;
    public String primary;
    public String address;
    public String province;

    /**
     * Initializes a new instance of the Business class.
     */
    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Initializes a new instance of the Business class.
     * @param id The id of the business.
     * @param number The business number.
     * @param name The business name.
     * @param primary The business primary.
     * @param address The business address.
     * @param province The business province.
     */
    public Business(String id, String number, String name, String primary, String address, String province){
        this.id = id;
        this.number = number;
        this.name = name;
        this.primary = primary;
        this.address = address;
        this.province = province;
    }

    /**
     * Creates and returns a map of all the data in the object.
     * @return A hashmap containing all fields for Firebase.
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("number", number);
        result.put("name", name);
        result.put("primary", primary);
        result.put("address", address);
        result.put("province", province);

        return result;
    }
}
