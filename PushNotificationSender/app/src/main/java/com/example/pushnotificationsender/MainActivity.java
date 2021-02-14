package com.example.pushnotificationsender;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static final String TOPIC = "/topics/deals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.send);
        EditText titleText = findViewById(R.id.title);
        EditText contentText = findViewById(R.id.content);

        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String title = titleText.getText().toString();
                String content = contentText.getText().toString();

                if(!title.isEmpty() && !content.isEmpty()){
                    Notification message = new Notification(title, content);
                    PushNotification data = new PushNotification(message, TOPIC);
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    NotificationAPI apiService =
                            retrofit.create(NotificationAPI.class);
                    Call<Response> call = apiService.postNotification(data);
                    call.enqueue(new Callback<Response>() {
                        @Override
                        public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                            if (!response.isSuccessful()) {
                                Log.d(TAG, String.valueOf(response.code()));
                                return;
                            }
                            titleText.setText("");
                            contentText.setText("");
                            Toast.makeText(MainActivity.this, "Message Pushed", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<Response> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });
                }
            }
        });
    }
}