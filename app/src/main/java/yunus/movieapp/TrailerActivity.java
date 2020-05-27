package yunus.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class TrailerActivity extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

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

    getSupportActionBar().setTitle("trailer");  //judul diatas
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // panah kembali keaktivity sebelumnya

}

    @Override
    public boolean onSupportNavigateUp() {
        finish();  // menutup activity jika activity dihapus
        return super.onSupportNavigateUp();
    }
}
