package com.example.passengeractivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;

    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_message, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bot_message, parent, false);
        }
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        ((MessageViewHolder) holder).bind(message);
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).isUserMessage() ? 0 : 1;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        public MessageViewHolder(View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.messageText);
        }

        public void bind(Message message) {
            messageText.setText(message.getText());
        }
    }
}
