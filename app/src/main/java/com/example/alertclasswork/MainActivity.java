package com.example.alertclasswork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean[]test={false,false,false,false,false};
    private Button dialog,text_dialog;
    String[] str_list = {"葡萄", "香蕉", "水蜜桃","蘋果","鳳梨"};
    private AlertDialog das = null;
    AlertDialog.Builder builder = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View item = LayoutInflater.from(MainActivity.this).inflate(R.layout.ccc, null);

        final List<Boolean> checkedStatusList = new ArrayList<>();{
            for(String s : str_list){
                checkedStatusList.add(false);
            }};
        dialog=findViewById(R.id.button);
        text_dialog=findViewById(R.id.button2);

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMultiChoiceItems(str_list,test, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(MainActivity.this, "你選擇了" + str_list[i], Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "你取消了" + str_list[i], Toast.LENGTH_LONG).show();
                        }
                        checkedStatusList.set(i, isChecked);
                    }
                })
                .setPositiveButton("送出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < checkedStatusList.size(); i++) {
                            if (checkedStatusList.get(i)) {
                                sb.append(str_list[i]);
                                sb.append(" ");
                            }
                        }
                        Toast.makeText(MainActivity.this, "你選擇的是"+sb.toString(), Toast.LENGTH_LONG).show();
                    } });
                das=builder.create();
                das.show();
            }
        });

        text_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.yourname)
                        .setView(item)
                        .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = (EditText) item.findViewById(R.id.aaatest);
                                String name = editText.getText().toString();
                                if(TextUtils.isEmpty(name)){
                                    Toast.makeText(getApplicationContext(), R.string.noname, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.okname) + name, Toast.LENGTH_LONG).show();
                                }
                            }

                        });
                das=builder.create();
                das.show();

            }
        });


        }

    }

