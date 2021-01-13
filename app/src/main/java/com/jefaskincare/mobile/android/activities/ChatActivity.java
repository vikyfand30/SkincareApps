package com.jefaskincare.mobile.android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jefaskincare.mobile.android.MainActivity;
import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.fragment.chat.adapter.MessageListAdapter;
import com.sendbird.android.BaseMessage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    List<BaseMessage> messageList;

    @BindView(R.id.ivBackChat)
    ImageView ivBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        mMessageRecycler = (RecyclerView) findViewById(R.id.rvChat);
        mMessageAdapter = new MessageListAdapter(this,messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatActivity.super.onBackPressed();
//                Intent i = new Intent(ChatActivity.this, BaseChatActivity.class);
//                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
