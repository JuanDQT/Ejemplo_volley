package com.example.juan.ejemplos_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.juan.ejemplos_volley.controllers.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_1 = "http://1234.hol.es/getField.php";
    private static final String URL_2 = "http://1234.hol.es/getObjeto.php";

    Button btn_1;
    Button btn_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = (Button) findViewById(R.id.btn_simple);
        btn_2 = (Button) findViewById(R.id.btn_objeto);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
    }


    public void opcion_1() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR DE CONEXION", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void opcion_2() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("R: "+ response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String lastname = jsonObject.getString("lastname");
                    Toast.makeText(MainActivity.this, "ID: "+ id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "NAME: "+ name, Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "LASTNAME: "+ lastname, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                opcion_1();
                break;
            case R.id.btn_objeto:
                opcion_2();
                break;
        }
    }
}
