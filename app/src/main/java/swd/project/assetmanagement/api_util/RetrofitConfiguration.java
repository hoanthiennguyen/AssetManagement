package swd.project.assetmanagement.api_util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfiguration {
    private static Retrofit retrofitAdapter;


    public static Retrofit getRetrofitAdapter(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6IlJPTEVfRklSTV9BRE1JTixST0xFX0ZJUk1fRU1QTE9ZRUUiLCJzdWIiOiJraGFuaDQiLCJpc3MiOiJBc3NldCBNYW5hZ2VtZW50IFN5c3RlbSBBUEkiLCJpYXQiOjE1NzYwNzI0OTQsImV4cCI6MTU3NjY3NzI5NH0.s8B-oLFbNGjEuhwj5h_i-9J2qvFgnNtsciChX_icTebStr7jbaLkjE6h9U3Q-Y5t-rndsvtMC6Utc4Av_X9VhA")
                        .build();
                return chain.proceed(newRequest);
            }
        };

//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if(retrofitAdapter == null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .readTimeout(80, TimeUnit.SECONDS).build();

            retrofitAdapter = new Retrofit.Builder()
                    .baseUrl(ConfigApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitAdapter;
    }
}
