package yunus.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import yunus.movieapp.R;
import yunus.movieapp.activity.TrailerActivity;
import yunus.movieapp.model.Video;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    //data yang dikelompokan dalam main adapter
    private List<Video.Results> models;

    //context == activity yang digunakan ||activity mana yang menggunakan adapter ini
    private Context context;


    //untuk menjalankan list dan context || constructor
    public VideoAdapter(List<Video.Results> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override  //ViewHolder berfungsi untuk memanggil layout
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_video, parent, false);
        return new ViewHolder(view);
    }

    @Override //digunakan untuk model berdasarkan komponen view adapter_main
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Video.Results model = models.get(position);

        //list trailer
        holder.textView.setText(model.getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TrailerActivity.youTubePlayer.loadVideo(model.getKey());   // cuevideo = tampilin dulu loadvideo = langsung putar
            }
        });

        // ketika blum diapa"in baru klik vidio trailer dari activity detail || sebagai tumbnail agar tidak hitam
        TrailerActivity.youTubePlayer.cueVideo(models.get(0).getKey());


    }

    @Override //menghitung berapa banyak data pada models
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;  // ambil dari xml



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView); //casting adapter_main.xml

        }
    }
}

