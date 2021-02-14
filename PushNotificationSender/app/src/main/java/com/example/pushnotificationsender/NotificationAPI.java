package com.example.pushnotificationsender;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificationAPI {
    @Headers({"Authorization: key=" + Constants.SERVER_KEY, "Content-Type:" + Constants.CONTENT_TYPE})
    @POST("fcm/send")
    @Nullable
    Call<Response> postNotification(@Body PushNotification data);
}
