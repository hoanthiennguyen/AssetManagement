package swd.project.assetmanagement.api_util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfiguration {
    private static Retrofit retrofitAdapter;


    public static Retrofit getRetrofitAdapter(){
        if(retrofitAdapter == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(80, TimeUnit.SECONDS).build();
            retrofitAdapter = new Retrofit.Builder().baseUrl(ConfigApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        }
        return retrofitAdapter;
    }
}
