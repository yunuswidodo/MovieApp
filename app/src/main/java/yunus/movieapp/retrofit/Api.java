package yunus.movieapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;   // variabel nilai awal nul
    public static Retrofit getUrl() {

        retrofit = new Retrofit.Builder()
                .baseUrl( Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) // gsonconverter ammbil dari implementasi yang berfungsi convert data dari json ke data
                .build();

        return retrofit;

    }
}
