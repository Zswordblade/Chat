package com.qyb.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class chatActivity extends AppCompatActivity {
    private RecyclerView circleRecycle;
    private EditText inputEdit;
    private Button btn_send;
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        circleRecycle = (RecyclerView) findViewById(R.id.recyclerView);
        inputEdit = (EditText) findViewById(R.id.inputText);
        btn_send = (Button) findViewById(R.id.send);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        circleRecycle.setLayoutManager(layoutManager);
        msgAdapter = new MsgAdapter(msgList);
        circleRecycle.setAdapter(msgAdapter);
    }
}