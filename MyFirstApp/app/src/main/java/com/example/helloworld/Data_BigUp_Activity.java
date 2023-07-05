package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data_BigUp_Activity extends AppCompatActivity {
    private Button btn1;
    private Button bigUp;
    private Button smallData;
    private TextView tv1;
    private EditText edt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_big_up);

        btn1 = (Button) findViewById(R.id.btn1);
        bigUp = (Button)findViewById(R.id.bigUp);
        tv1 = (TextView) findViewById(R.id.tv1);
        edt1 = (EditText) findViewById(R.id.edt1);
        smallData = (Button)findViewById(R.id.smallData);

        final int[] size = {30};
        smallData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smaller();
            }
            private void smaller() {
                if (size[0] > 30){
                    tv1.setTextSize(--size[0]);
                }
            }
        });

        bigUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bigger();
            }

            private void bigger() {
                tv1.setTextSize(++size[0]);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDate = edt1.getText().toString();
                tv1.setText(newDate);
            }
        });
    }
}