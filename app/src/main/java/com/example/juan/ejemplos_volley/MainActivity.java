package com.example.juan.ejemplos_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void opcion_1() {
        
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple:
                opcion_1();
                break;
        }
    }
}
