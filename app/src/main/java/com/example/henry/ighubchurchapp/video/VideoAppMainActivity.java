package com.example.henry.ighubchurchapp.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.henry.ighubchurchapp.R;

import static com.example.henry.ighubchurchapp.R.id.action_settings;

public class VideoAppMainActivity extends AppCompatActivity {

    private Button mRecordView,mPlayView;
    private VideoView mVideoView;
    private int ACTIVITY_START_CAMERA_APP =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_app_main);

        mRecordView = findViewById(R.id.recordButton);
        mPlayView = findViewById(R.id.playButton);
        mVideoView = findViewById(R.id.videoView4);

        mRecordView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callVideoAppIntent = new Intent();
                callVideoAppIntent.setAction(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(callVideoAppIntent,ACTIVITY_START_CAMERA_APP);
            }
        });

        mPlayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mVideoView.start();
            }
        });
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK){
            Uri videoUri = data.getData();
            mVideoView.setVideoURI(videoUri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_video_app_main,menu);
        return true;
    }
  @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
