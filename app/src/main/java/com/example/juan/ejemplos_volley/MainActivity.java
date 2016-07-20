package com.example.juan.ejemplos_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.juan.ejemplos_volley.controllers.MySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String URL_1 = "http://1234.hol.es/getField.php";
    private static final String URL_2 = "http://1234.hol.es/getObjeto.php";
    private static final String URL_3 = "http://1234.hol.es/getObjetos.php";
    private static final String URL_4 = "http://1234.hol.es/getImagen.php";

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;

    ImageView img_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = (Button) findViewById(R.id.btn_simple);
        btn_2 = (Button) findViewById(R.id.btn_objeto);
        btn_3 = (Button) findViewById(R.id.btn_array);
        btn_4 = (Button) findViewById(R.id.btn_imagen);
        img_picture = (ImageView) findViewById(R.id.iv_picture);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
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

    public void opcion_3() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, URL_3, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //PARSEAR SUS ATRIBUTOS..
                        Toast.makeText(MainActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);
    }

    public void opcion_4() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_4, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Picasso.with(getApplicationContext()).load(response).into(img_picture);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
            case R.id.btn_array:
                opcion_3();
                break;
            case R.id.btn_imagen:
                opcion_4();
                break;
        }
    }
}
