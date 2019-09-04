package com.filzah.moviedbsub3duty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.filzah.moviedbsub3duty.model.Movie;

public class DetailActivity extends AppCompatActivity {

    public static final String Extra = "extra";

    Movie mModel;
    ImageView imgDetail;
    TextView tvName, tvDate, tvOverview, tvRating;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetail = findViewById(R.id.detail_img);
        tvName = findViewById(R.id.detail_title);
        tvDate = findViewById(R.id.detail_date);
        tvOverview = findViewById(R.id.detail_title_overview);
        ratingBar = findViewById(R.id.rating_ar);
        tvRating = findViewById(R.id.rating_lael);

        mModel = getIntent().getParcelableExtra(Extra);
        if (mModel != null){
            if (getSupportActionBar() != null){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(mModel.getTitle());
            }
            Glide.with(this)
                    .load("http://image.tmdb.org/t/p/w185"+mModel.getPosterPath())
                    .apply(new RequestOptions()
                    .override(350, 550))
                    .placeholder(R.drawable.default_pict)
                    .error(R.drawable.default_pict)
                    .into(imgDetail);

            tvName.setText(mModel.getTitle());
            tvDate.setText(mModel.getReleaseDate());
            tvOverview.setText(mModel.getOverview());

            float rat = mModel.getVoteAverage().floatValue()/2;
            String laelRating = Float.toString(rat);

            tvRating.setText(getResources().getString(R.string.lael_rating, laelRating));
            ratingBar.setRating(rat);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
