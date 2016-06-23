package com.yelprestaurantapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button searchBtn =(Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new SearchBtnOnClickListener());
    }

    class SearchBtnOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText searchTxt = (EditText) findViewById(R.id.searchTxt);
            EditText limitTxt = (EditText) findViewById(R.id.limitTxt);
            String limit = limitTxt != null && limitTxt.getText() != null ? limitTxt.getText().toString() : "10";
            if(searchTxt != null && searchTxt.getText() != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("searchLocation", searchTxt.getText().toString());
                intent.putExtra("searchLimit", limit);
                startActivity(intent);
            } else {
                // TODO: show error to user;
            }
        }
    }


}
