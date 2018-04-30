package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView alsoKnownAs;
    private TextView placeOfOrigin;
    private TextView ingredients;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        alsoKnownAs = findViewById(R.id.also_known_tv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        ingredients = findViewById(R.id.ingredients_tv);
        description = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        } else {
            ArrayList<String> alsoKnownAsList = (ArrayList<String>) sandwich.getAlsoKnownAs();
            for (int i = 0; i < alsoKnownAsList.size(); i++) {
                String name = alsoKnownAsList.get(i);
                name = name.replace("[","");
                name = name.replace("]", "");
                name = name.replace("\"", "");
                if (i == alsoKnownAsList.size() - 1) {
                    alsoKnownAs.append(name);
                } else {
                    alsoKnownAs.append(name + ", ");
                }
            }
            placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
            ArrayList<String> ingredientsList = (ArrayList<String>) sandwich.getIngredients();
            for (int j = 0; j < ingredientsList.size(); j++) {
                String ingredient = ingredientsList.get(j);
                ingredient = ingredient.replace("[", "");
                ingredient = ingredient.replace("]", "");
                ingredient = ingredient.replace("\"", "");
                if (j == ingredientsList.size() - 1) {
                    ingredients.append(ingredient);
                } else {
                    ingredients.append(ingredient + ", ");
                }
            }
            description.setText(sandwich.getDescription());
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
