package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {
    private String error = null;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        refreshErrorMessage();
        intent = new Intent(this, MainActivity.class);
    }

    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

    public void sendMessage(View view) {
        error = "";
        refreshErrorMessage();
        final EditText tvEmail = (EditText) findViewById(R.id.email);
        final EditText tvPassword = (EditText) findViewById(R.id.password);
        RequestParams params = new RequestParams();
        System.out.println(tvEmail.getText().toString());
        System.out.println(tvPassword.getText().toString());
        params.put("tutorEmail", tvEmail.getText().toString());
        params.put("password", tvPassword.getText().toString());

        HttpUtils.post("login/" + tvEmail.getText().toString(), params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("Success!!");
//                tvEmail.setText("");
//                tvPassword.setText("");


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String string, Throwable throwable){

            }
            public void onFinish(){
                if (error.equals("")){
                    System.out.println("Success!!");
                    startActivity(intent);
                }
            }
        });

    }

}