package ca.mcgill.ecse321.tutoringservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
        final EditText tvEmail = (EditText) findViewById(R.id.email);
        final EditText tvPassword = (EditText) findViewById(R.id.password);
        RequestParams params = new RequestParams();
        params.put("tutorEmail", "tvEmail.getText().toString()");
        params.put("password", "tvPassword.getText().toString()");

        HttpUtils.post("login/" + tvEmail.getText().toString(), params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                refreshErrorMessage();
                tvEmail.setText("");
                tvPassword.setText("");
                startActivity(intent);

            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });

    }

}