package yunus.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import yunus.movieapp.retrofit.Constant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    TextView txtTitle, txtDesc, txtMore;
    ImageView imageView;
    Button button;

    //buat intent put extra
    Bundle bundle;

    //untuk mendapatkan jumlah baris
    private int line_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        bundle = getIntent().getExtras();

        txtTitle = findViewById(R.id.txtTitle);
        txtDesc = findViewById(R.id.txtDesc);
        txtMore = findViewById(R.id.txtMore);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.Button);

        Picasso.get().load(bundle.getString(Constant.INTENT_bACKDROP))
                .placeholder(R.drawable.placeholder)
                .into(imageView);
        txtTitle.setText(bundle.getString(Constant.INTENT_TITLE));
        txtDesc.setText(bundle.getString(Constant.INTENT_DESCRIPTION));

        //prosec more
        txtDesc.post(new Runnable() {
            @Override
            public void run() {
                line_count = txtDesc.getLineCount();
            }
        });

        txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDesc.getMaxLines() <= 2){
                    txtMore.setText("les");
                    txtDesc.setMaxLines(line_count);
                }else {
                    txtMore.setText("..more");
                    txtDesc.setMaxLines(2);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DetailActivity.this, TrailerActivity.class));
            }
        });


        getSupportActionBar().setTitle("");  //judul diatas
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // panah kembali keaktivity sebelumnya

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();  // menutup activity jika activity dihapus
        return super.onSupportNavigateUp();
    }
}

