package com.example.utspam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_video);
        TextView txttitle, txtrelease, txtoverview, txtpopularity;
        ImageView image;

        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String released = extras.getString("released");
        String overview = extras.getString("overview");
        String popularity = extras.getString("popularity");
        String imageUrl = extras.getString("imageUrl");

        txttitle = findViewById(R.id.textTitle);
        txttitle.setText(title);

        txtrelease = findViewById(R.id.textRelease2);
        String rilis = String.format(getResources().getString(R.string.released), released);
        txtrelease.setText(rilis);

        txtoverview = findViewById(R.id.textOverview);
        txtoverview.setText(overview);

        txtpopularity = findViewById(R.id.textPopularity);
        txtpopularity.setText(popularity);

        image = findViewById(R.id.imageView);
        Picasso.with(DetailVideoActivity.this).load(imageUrl).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground).into(image);
    }
}