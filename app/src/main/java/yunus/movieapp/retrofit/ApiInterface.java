package yunus.movieapp.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yunus.movieapp.model.Movie;
import yunus.movieapp.model.Video;

//end point terusan dari base url || base constant
public interface ApiInterface {


    //play_now
    @GET("movie/{category}")   // end point dari movie popular
    Call<Movie> getMovie(   //call-> isi method api(model), kemudian nama api methoonya

                            //api_key=b4737e6ff1930152a62527447b5e3bf0&language=en-US&page=1

    @Path("category") String movie_category,
     // param
     @Query("api_key") String key,
     @Query("language") String language,
     @Query("page") String page
    );


    //videos
    @GET("movie/{id}/videos")
    Call<Video> getVideo(

            //ada id ditengah" /(slice) pakai path
     @Path("id") String movie_id,
     @Query("api_key") String key
    );

}
