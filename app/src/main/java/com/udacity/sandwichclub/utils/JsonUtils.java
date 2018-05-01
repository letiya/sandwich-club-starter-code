package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonUtils {
    private static final String JSON_OBJECT_NAME = "name";
    private static final String JSON_OBJECT_MAINNAME = "mainName";
    private static final String JSON_OBJECT_ALSOKNOWNAS = "alsoKnownAs";
    private static final String JSON_OBJECT_PLACEOFORIGIN = "placeOfOrigin";
    private static final String JSON_OBJECT_DESCRIPTION = "description";
    private static final String JSON_OBJECT_IMAGE = "image";
    private static final String JSON_OBJECT_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            String mainName = jsonObject.getJSONObject(JSON_OBJECT_NAME).getString(JSON_OBJECT_MAINNAME);

            JSONArray alsoKnownAs = jsonObject.getJSONObject(JSON_OBJECT_NAME).getJSONArray(JSON_OBJECT_ALSOKNOWNAS);
            List<String> alsoKnownAsList = new ArrayList<String>();
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAsList.add(alsoKnownAs.getString(i));
            }

            String placeOfOrigin = jsonObject.getString(JSON_OBJECT_PLACEOFORIGIN);
            String description = jsonObject.getString(JSON_OBJECT_DESCRIPTION);
            String image = jsonObject.getString(JSON_OBJECT_IMAGE);

            JSONArray ingredients = jsonObject.getJSONArray(JSON_OBJECT_INGREDIENTS);
            List<String> ingredientsList = new ArrayList<String>();
            for (int j = 0; j < ingredients.length(); j++) {
                ingredientsList.add(ingredients.getString(j));
            }
            sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sandwich;
    }
}
