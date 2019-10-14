package aim.helmi.moviecatalogue.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aim.helmi.moviecatalogue.R;
import aim.helmi.moviecatalogue.model.TvModel;

public class DetailTvShow extends AppCompatActivity {
    TextView judul, deskripsi, rilis, des;
    ImageView gambar;

    public static final String EXTRA_JOS = "aim";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        judul = findViewById(R.id.jdl_detail);
        deskripsi = findViewById(R.id.deskripsi_detail);
        rilis = findViewById(R.id.rilis_detail);
        gambar = findViewById(R.id.img_detail);
        des = findViewById(R.id.des);
        progressBar = findViewById(R.id.progressDetailMovie);

        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(3000);
                }
                catch (Exception e) { }

                handler.post(new Runnable() {
                    public void run() {
                        TvModel tv= getIntent().getParcelableExtra(EXTRA_JOS);

                        String url_image = "https://image.tmdb.org/t/p/w185" + tv.getPoster_path();

                        judul.setText(tv.getName());
                        deskripsi.setText(tv.getOverview());
                        rilis.setText(tv.getFirst_air_date());
                        Glide.with(DetailTvShow.this)
                                .load(url_image)
                                .into(gambar);

                        if (getSupportActionBar() != null){
                            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                        }
                        String title = String.format(getResources().getString(R.string.detail));
                        setTitle(title);

                        String a = String.format(getResources().getString(R.string.des));
                        des.setText(a);

                        progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
