package yunus.movieapp.activity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yunus.movieapp.R;
import yunus.movieapp.adapter.MovieAdapter;
import yunus.movieapp.model.Movie;
import yunus.movieapp.retrofit.Api;
import yunus.movieapp.retrofit.ApiInterface;
import yunus.movieapp.retrofit.Constant;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        getMovie("popular"); // param Api | apiinterface

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //penggunaan retrofit
    private void getMovie(String category){  //categori biar bisa dinamis || jika ingin menggunakan method getmovie harus menggunakan param catergori dengan default "popular"

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<Movie> call = apiInterface.getMovie(category, Constant.KEY, "en-Us", "1");
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body(); // sini
                List<Movie.Results> results = movie.getResults();
                recyclerView.setAdapter(new MovieAdapter(results, MainActivity.this));
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_popular) {
            getSupportActionBar().setTitle("Movie Populer");
            getMovie("popular"); // param api
            return true;
        }else if (id == R.id.action_playing){
            getSupportActionBar().setTitle("Movie now playing");
            getMovie("now_playing"); // param api
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
