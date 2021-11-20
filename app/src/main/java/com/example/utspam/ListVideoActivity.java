package com.example.utspam;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.utspam.Adapter.AdapterListVideo;
import com.example.utspam.Model.Videos;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListVideoActivity extends AppCompatActivity {

    private final List<Videos> viewItems = new ArrayList<>();

    @BindView(R.id.lst_videos)
    RecyclerView lstVideos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        ButterKnife.bind(this);

        //mengset tampilan agar seperti ListView, jika ingin terlihat seperti album gunakan GridLyoutManager
        lstVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstVideos.setHasFixedSize(true);
        //memberikan dekorasi pembatas antar tiap item
        lstVideos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //menginisiasi adapter untuk menjalankan vie item_video_layout
        AdapterListVideo adapterListVideo = new AdapterListVideo(this, viewItems);
        lstVideos.setAdapter(adapterListVideo);

        addItemFromJSON();
    }

    //disini memparse json untuk dimasukkan ke dalam viewItems menggunakan method set
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void addItemFromJSON() {
        try {
            String jsonDataString = readJSONDataFromFIle();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0;i <jsonArray.length(); i++){
                JSONObject itemObj = jsonArray.getJSONObject(i);

                Videos videos = new Videos();
                videos.setPopularity(itemObj.getDouble("popularity"));
                videos.setVideo(itemObj.getBoolean("video"));
                videos.setPosterPath(itemObj.getString("poster_path"));
                videos.setId(itemObj.getInt("id"));
                videos.setAdult(itemObj.getBoolean("adult"));
                videos.setTitle(itemObj.getString("title"));
                videos.setReleaseDate(itemObj.getString("release_date"));
                videos.setOverview(itemObj.getString("overview"));

                viewItems.add(videos);

            }

        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }

    private String readJSONDataFromFIle() throws IOException{
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();

        try{
            String jsonString;
            //ambil file json list_film.json di folder raw
            inputStream = getResources().openRawResource(R.raw.list_film);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            //bufferedReader.reaLine() akan membaca setiap line di list_film.json
            while ((jsonString = bufferedReader.readLine()) != null ){
                stringBuilder.append(jsonString);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (inputStream != null){
                inputStream.close();
            }
        }

        return new String(stringBuilder);
    }
}