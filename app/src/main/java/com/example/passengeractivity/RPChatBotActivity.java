package com.example.passengeractivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class RPChatBotActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private EditText userInput;
    private ChatAdapter chatAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpchat_bot);

        recyclerView = findViewById(R.id.recyclerView);
        userInput = findViewById(R.id.userInput);
        Button sendButton = findViewById(R.id.sendButton); // Replace "R.id.sendButton" with the actual ID of your send button

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(chatAdapter);

        // Set an OnClickListener for the send button
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v);
            }
        });
    }

    // Handle the "Send" button click event
    public void sendMessage(View view) {
        String userMessage = userInput.getText().toString().trim();

        if (!userMessage.isEmpty()) {
            // Add user message to the chat
            messageList.add(new Message(userMessage, true));
            chatAdapter.notifyDataSetChanged();

            // Show typing animation

            // Simulate a delay (e.g., 2 seconds) for the typing animation
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Hide typing animation when the response is ready

                    // Get a response from the chatbot
                    String botResponse = getChatbotResponse(userMessage);

                    // Add chatbot response to the chat
                    messageList.add(new Message(botResponse, false));
                    chatAdapter.notifyDataSetChanged();

                    // Clear the user input field
                    userInput.setText("");
                }
            }, 800); // Adjust the delay duration as needed
        }
    }

    // Simulate chatbot responses (replace with actual chatbot logic)
    // Replace the existing getChatbotResponse method
    private String getChatbotResponse(String userMessage) {
        String botResponse;

        // Check for greetings
        if (userMessage.contains("hello") || userMessage.contains("hi") || userMessage.contains("hey")) {
            botResponse = "Hello there, How can I assist you today?";
        }
        // Check for asking for help or problems
        else if (userMessage.contains("help") || userMessage.contains("problem") || userMessage.contains("issue")) {
            botResponse = "Sure, What seems to be the problem?";
        }
        // Check for specific problem #1 keywords
        else if (userMessage.contains("cant") && userMessage.contains("find") &&
                (userMessage.contains("QR Code") || userMessage.contains("Current Location")) &&
                (userMessage.contains("load") || userMessage.contains("does") || userMessage.contains("not"))) {
            botResponse = "It seems like you are having a connection issue, please check your internet.";
        }
        // Check for Question #1 keywords
        else if (userMessage.contains("What") && userMessage.contains("benefits") && userMessage.contains("Ridepay") &&
                userMessage.contains("between") && userMessage.contains("other") &&
                (userMessage.contains("similar") || userMessage.contains("systems") || userMessage.contains("using"))) {
            botResponse = "Unlike other similar systems, our solution is uniquely designed with a strong emphasis on data-driven insights,\n" +
                    "ensuring that your travel experience is not only convenient but also optimized for your needs. \n" +
                    "With features like journey tracing that enhance passenger safety,\n" +
                    "and our user-friendly mobile app that empowers you to manage your account, \n" +
                    "check balances, and receive real-time updates, making your transit experience more connected and efficient.\n";
        }
        // Check for asking for help or problems
        else if (userMessage.contains("add")
                || userMessage.contains("Ridecoins") || userMessage.contains("ridecoin") || userMessage.contains("balance")
                || userMessage.contains("top up") || userMessage.contains("funds") || userMessage.contains("deposit")) {
            botResponse = "By using the \"Top up Locations\" feature, locate a nearby RIDEPAY top-up location, which may include transit stations, kiosks, or partnering retail outlets.\n" +
                    "Approach the top-up machine or counter.\n";
        } else if (userMessage.contains("Expiry") || userMessage.contains("when")
                || userMessage.contains("expire")
                || userMessage.contains("renew")) {
            botResponse = "Discounted cards have an expiration of one (1) year. If your card expires, you may go to your nearest top-up location\n" +
                    "and present the requirements to avail the renewal of your card.\n" +
                    "If you fail to present any validations, your account will be converted to a regular card.\n";
        } else if (userMessage.contains("lost") || userMessage.contains("qr code") || userMessage.contains("cant")
                || userMessage.contains("qr")
                || userMessage.contains("find")) {
            botResponse = "If you lost your QR code you may freeze your account in your account settings\n" +
                    "and go to the nearest Top up Location for further instructions\n";
        } else if (userMessage.contains("Who") || userMessage.contains("built")
                || userMessage.contains("developers")) {
            botResponse = "Ridepay was developed by a group of students from Southwestern University PHINMA.";
        } else if (userMessage.contains("regular") || userMessage.contains("expire")
                || userMessage.contains("expiry")
                || userMessage.contains("expiration")) {
            botResponse = "Regular cards do not have an expiration date \n" +
                    "but on the other hand, discounted cards expires one year after the purchase date.\n";
        } else if (userMessage.contains("minimum") || userMessage.contains("fare")
                || userMessage.contains("much") || userMessage.contains("cost")
                || userMessage.contains("trip")) {
            botResponse = "As of now, all RIDEPAY integrated buses have a minimum fare of 15 Pesos.";
        } else if (userMessage.contains("does") || userMessage.contains("ridepay")
                || userMessage.contains("system") || userMessage.contains("work")
                || userMessage.contains("explain")) {
            botResponse = "RIDEPAY system streamlines fare collection and offers passengers a convenient, cashless, and secure way to pay for their bus rides.\n" +
                    "It also provides valuable data for transit authorities to improve service and enhances the overall passenger experience through features like \n" +
                    "real-time updates and account management via a user-friendly mobile app.\n"
            ;
        } else if (userMessage.contains("What") || userMessage.contains("if") || userMessage.contains("battery")
                || userMessage.contains("dies") || userMessage.contains("phone") || userMessage.contains("lowbat")
                || userMessage.contains("dies") || userMessage.contains("low") || userMessage.contains("battery")) {
            botResponse = "If your phone dies, don't worry you still have that card we gave you from registration\n" +
                    "you may use that to your journey with us!";
        }


        // Add more conditions for other responses here
        else {
            botResponse = "I'm not sure how to respond to that. Can you please rephrase?";
        }

        return botResponse;
    }
}
