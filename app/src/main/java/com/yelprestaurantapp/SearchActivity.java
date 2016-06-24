package com.yelprestaurantapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
            String limit = limitTxt != null && limitTxt.getText() != null && limitTxt.getText().length() > 0 ? limitTxt.getText().toString() : "10";
            if(searchTxt != null && searchTxt.getText() != null && searchTxt.getText().length() > 0) {
                hideErrorMsg();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("searchLocation", searchTxt.getText().toString());
                intent.putExtra("searchLimit", limit);
                startActivity(intent);
            } else {
                showErrorMsg("Search Text can not be empty");
            }
        }

        private void hideErrorMsg() {
            TextView errorTextView = (TextView) findViewById(R.id.errorTxt);
            errorTextView.setVisibility(View.GONE);
        }

        private void showErrorMsg(String errorMsg) {
            TextView errorTextView = (TextView) findViewById(R.id.errorTxt);
            errorTextView.setTextColor(Color.RED);
            errorTextView.setText(errorMsg);
            errorTextView.setPadding(10, 10, 10, 10);
            errorTextView.setVisibility(View.VISIBLE);
            Log.e("Error", errorMsg);
        }
    }
}
