package com.example.helloworld.ZuoYe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.helloworld.R;

public class ZuoYe_2_Activity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox rdb1;
    private CheckBox rdb2;
    private CheckBox rdb3;
    private CheckBox rdb4;
    private TextView txv1;
    private String txv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuo_ye2);

        rdb1 = (CheckBox)findViewById(R.id.RadioButton1);
        rdb2 = (CheckBox) findViewById(R.id.RadioButton2);
        rdb3 = (CheckBox)findViewById(R.id.RadioButton3);
        rdb4 = (CheckBox) findViewById(R.id.RadioButton4);
        txv1 = (TextView) findViewById(R.id.TextView2);
        txv2 = new String();

        rdb1.setOnCheckedChangeListener(this);
        rdb2.setOnCheckedChangeListener(this);
        rdb3.setOnCheckedChangeListener(this);
        rdb4.setOnCheckedChangeListener(this);



    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {
        String motion= buttonView.getText().toString();
        if (b){
            if (!txv2.contains(motion)){
                txv2 = txv2 + motion;
                txv1.setText(txv2);
            }
        }else{
            if (txv2.contains(motion)){
                txv2 = txv2.replace(motion,"");
                txv1.setText(txv2);
            }
        }
    }
}