package swd.project.assetmanagement.api_util;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class TokenManagement {
    private static String token;
    public static void setAccessToken(@NonNull Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ACCESSTOKEN", token);
        editor.apply();
    }
    public static void removeAccessToken(@NonNull Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("ACCESSTOKEN");
        editor.apply();
    }
    public static String getAccessToken(@NonNull Context context) {
        if(token == null){
            SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            token = sharedPreferences.getString("ACCESSTOKEN", null);
        }
        return token;
    }
}
