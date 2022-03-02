package com.au.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name;
    RadioButton male,female;
    RadioGroup radioGroup;
    Spinner spi;
    String[] data={"Please Select Skills","Web Desinging","PHP","My SQL"," Android"};
    CheckBox ch1,ch2,ch3,ch4;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.name);

        spi=findViewById(R.id.spinner);
        ArrayAdapter<String> adpt=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data)
        {
            @Override
            public boolean isEnabled(int position) {
                if (position==0)
                {
                    return false;
                }
                else
                {
                        return true;
                }
            }
        };
        spi.setAdapter(adpt);

        ch1=findViewById(R.id.cricket);
        ch2=findViewById(R.id.tpass);
        ch3=findViewById(R.id.coding);
        ch4=findViewById(R.id.reading);

        male=findViewById(R.id.Male);
        female=findViewById(R.id.Female);

    }


    public void write(View view)
    {
        radioGroup=findViewById(R.id.rbg);
        int a=radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(a);

        String res="";
        if (ch1.isChecked()) {
            res+=ch1.getText().toString()+",";
        }
        if (ch2.isChecked()) {
            res+=ch2.getText().toString()+",";
        }
        if (ch3.isChecked()) {
            res+=ch3.getText().toString()+",";
        }
        if (ch4.isChecked()) {
            res+=ch4.getText().toString()+",";
        }

        sp=getSharedPreferences("File",MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("name",name.getText().toString());
        ed.putString("gender",radioButton.getText().toString());
        ed.putString("hobby",res);
        ed.putString("passion",spi.getSelectedItem().toString());


        if (ed.commit())
        {
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Eroor", Toast.LENGTH_SHORT).show();
        }

        name.setText("");
        radioButton.setChecked(false);
        ch1.setChecked(false);
        ch2.setChecked(false);
        ch3.setChecked(false);
        ch4.setChecked(false);
        spi.setSelection(0);
    }

    public void read(View view)
    {
        SharedPreferences sp1=getSharedPreferences("File",MODE_PRIVATE);
        name.setText(sp1.getString("name",""));
       String rb=sp1.getString("gender","");
       if (rb.equals(male.getText().toString()))
       {
           male.setChecked(true);
       }
       else
       {
           female.setChecked(true);
       }

        String chechk=sp1.getString("hobby","");
        String[] split=chechk.split(",");
        for (String s:split)
        {
         if (s.equals(ch1.getText().toString()))
            {
                ch1.setChecked(true);
            }
            if (s.equals(ch2.getText().toString()))
            {
                ch2.setChecked(true);
            }
            if (s.equals(ch3.getText().toString()))
            {
                ch3.setChecked(true);
            }
            if (s.equals(ch4.getText().toString()))
            {
                ch4.setChecked(true);
            }
        }
        String dataa=sp1.getString("passion","");
        for(int i = 0; i < data.length; i++){
            if(data[i].equals(dataa)){
                ((Spinner)findViewById(R.id.spinner)).setSelection(i);
            }
        }
    }
}
