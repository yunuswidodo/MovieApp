package yunus.movieapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yunus.movieapp.R;
import yunus.movieapp.adapter.VideoAdapter;
import yunus.movieapp.model.Movie;
import yunus.movieapp.model.Video;
import yunus.movieapp.retrofit.Api;
import yunus.movieapp.retrofit.ApiInterface;
import yunus.movieapp.retrofit.Constant;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.List;

public class TrailerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        progressBar = findViewById(R.id.ProgressBar_trailer);
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getVideo();

        YouTubePlayerFragment youtubeFragment = (YouTubePlayerFragment)  //harus sama dengan xmlnya
                getFragmentManager().findFragmentById(R.id.youtubeFragment);
        youtubeFragment.initialize("YOUR API KEY",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        // do any work here to cue video, play video, etc.
                        youTubePlayer.cueVideo("0LHxvxdRnYc");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });

    getSupportActionBar().setTitle(Constant.MOVIE_TITLE);  //judul diatas
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // panah kembali keaktivity sebelumnya

}

    @Override
    public boolean onSupportNavigateUp() {
        finish();  // menutup activity jika activity dihapus
        return super.onSupportNavigateUp();
    }


    private void getVideo(){

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Video> call = apiInterface.getVideo( String.valueOf(Constant.MOVIE_ID), Constant.KEY);
        call.enqueue(new Callback<Video>() {
            @Override
            public void onResponse(Call<Video> call, Response<Video> response) {

                Video video = response.body();
                List<Video.Results> results = video.getResults();
                recyclerView.setAdapter(new VideoAdapter(results, TrailerActivity.this));

              progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Video> call, Throwable t) {

            }
        });
    }

}