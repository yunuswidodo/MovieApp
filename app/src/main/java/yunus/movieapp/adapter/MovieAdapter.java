package yunus.movieapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import yunus.movieapp.activity.DetailActivity;
import yunus.movieapp.R;
import yunus.movieapp.model.Movie;
import yunus.movieapp.retrofit.Constant;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    //data yang dikelompokan dalam main adapter
    private List<Movie.Results> models;

    //context == activity yang digunakan ||activity mana yang menggunakan adapter ini
    private Context context;


    //untuk menjalankan list dan context || constructor
    public MovieAdapter(List<Movie.Results> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override  //ViewHolder berfungsi untuk memanggil layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override //digunakan untuk model berdasarkan komponen view adapter_main
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Movie.Results model = models.get(position);

        //menaruh judul pada text view
        holder.txtTitle.setText(model.getTitle());
        holder.txtDate.setText(model.getRelease_date());


        //get image with picaso
        Picasso.get().load(Constant.BACKDROP_PATH + model.getBackdrop_path())
                .placeholder(R.drawable.ic_launcher_background) // jika loading ambil gambar sementara
                .fit().centerCrop() // untuk mengambil tengahnya
                .into(ViewHolder.imgBackdrop);


        //review
        holder.imgBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(Constant.INTENT_TITLE, model.getTitle()); // name value
                intent.putExtra(Constant.INTENT_bACKDROP,Constant.BACKDROP_PATH + model.getBackdrop_path());
                intent.putExtra(Constant.INTENT_DESCRIPTION,model.getOverview());
                context.startActivity(intent);


                Constant.MOVIE_ID = String.valueOf(model.getId());  // conver int to string || get id vidio trailer activity
                Constant.MOVIE_TITLE = model.getTitle();
            }
        });
    }

    @Override //menghitung berapa banyak data pada models
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDate;  // ambil dari xml
        static ImageView imgBackdrop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle); //casting adapter_main.xml
            txtDate  = itemView.findViewById(R.id.txtDate);
            imgBackdrop = itemView.findViewById(R.id.imgBackdrop);
        }
    }
}
