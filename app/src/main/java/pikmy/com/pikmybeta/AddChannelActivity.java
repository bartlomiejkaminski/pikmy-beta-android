package pikmy.com.pikmybeta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class AddChannelActivity extends AppCompatActivity implements View.OnClickListener {

    Button create_channel;
    EditText txt_channel;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_channel);

        create_channel = (Button) findViewById(R.id.btn_add_pik);
        create_channel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        requestQueue = Volley.newRequestQueue(AddChannelActivity.this);
        String URL = "http://pikmybeta.x3pg3pxqri.eu-central-1.elasticbeanstalk.com/pikmy/channel/create";
        JSONObject json = new JSONObject();
        txt_channel = (EditText)findViewById(R.id.txt_channel);
        try {
            json.accumulate("name_channel", txt_channel.getText().toString());
            json.accumulate("channel_date", new Date().toString());
            JSONArray jsonArray = new JSONArray();
            JSONObject idJSON = new JSONObject();
            idJSON.accumulate("id",MainActivity.id_user);
            jsonArray.put(idJSON);
            json.accumulate("list_users", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = json.toString();
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq" + requestBody);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };

        requestQueue.add(stringRequest);
    }
}