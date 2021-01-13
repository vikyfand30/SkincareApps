package com.jefaskincare.mobile.android.fragment.chat.adapter;

import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.recyclerview.widget.RecyclerView;

import com.jefaskincare.mobile.android.R;
import com.jefaskincare.mobile.android.activities.View.LoginView;
import com.sendbird.android.UserMessage;
import com.sendbird.android.shadow.okhttp3.internal.Util;

import butterknife.internal.Utils;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {

    TextView messageText, timeText, nameText;

    ReceivedMessageHolder(View itemView){
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        nameText = (TextView) itemView.findViewById(R.id.text_message_name);


    }

    void bind(UserMessage message){
        messageText.setText(message.getMessage());

//        timeText.setText(Utils.formatDateTime(message.getCreatedAt()));
    }
}
