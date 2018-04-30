package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String mainName = jsonObject.getJSONObject("name").getString("mainName");

            String alsoKnownAs = jsonObject.getJSONObject("name").getString("alsoKnownAs");
            List<String> alsoKnownAsList = null;
            String[] alsoKnownAsArray = alsoKnownAs.split(",");
            if (alsoKnownAsArray.length != 0) {
                alsoKnownAsList = new ArrayList<String>();
                for (String s:alsoKnownAsArray) {
                    alsoKnownAsList.add(s);
                }
            }

            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");

            String ingredients = jsonObject.getString("ingredients");
            List<String> ingredientsList = null;
            String[] ingredientsArray = ingredients.split(",");
            if (ingredientsArray.length != 0) {
                ingredientsList = new ArrayList<String>();
                for(String s:ingredientsArray) {
                    ingredientsList.add(s);
                }
            }
            sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
