package com.example.designlabassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.designlabassignment.databinding.ActivityDetailBinding;

import java.io.File;

public class DetailActivity extends AppCompatActivity  {


    private static final int REQUESTCALL=1;
    private static final String AUDIO_RECORDER_FILE_EXT_3GP = ".3gp";
    private static final String AUDIO_RECORDER_FILE_EXT_MP4 = ".mp4";
    private static final String AUDIO_RECORDER_FOLDER = "AudioRecorder";
    private MediaRecorder recorder = null;
    private int currentFormat = 0;
    private int output_formats[] = { MediaRecorder.OutputFormat.MPEG_4,
            MediaRecorder.OutputFormat.THREE_GPP };
    private String file_exts[] = { AUDIO_RECORDER_FILE_EXT_MP4,
            AUDIO_RECORDER_FILE_EXT_3GP };

    AudioManager audioManager;

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

binding.record.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        Intent intent = new Intent(DetailActivity.this,record.class);
//        startActivity(intent);
    }
});


//************************************************


//*********************************************************
        DbHelper dbHelper = new DbHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1){

        final int image = getIntent().getIntExtra("image", 0);
        final int price = Integer.parseInt(getIntent().getStringExtra("price" ));
        String name = getIntent().getStringExtra("name" );
        String desc = getIntent().getStringExtra("desc" );

        binding.detailimage.setImageResource(image);
        binding.detailfoodname.setText(name);
        binding.detailDescription.setText(desc);
        binding.detailprice.setText(String.valueOf(price));

        binding.insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = dbHelper.insertOrder(
                        binding.namebox.getText().toString(),
                        binding.phonebox.getText().toString(),
                        price,
                        image,
                        desc,
                        name,
                        Integer.parseInt(binding.quantityItem.getText().toString())
                );
                if (isInserted)
                    Toast.makeText(DetailActivity.this, "Success", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }else{

            int  id = getIntent().getIntExtra("id",0);
            Cursor cursor = dbHelper.getOrderbyId(id);

                int image = cursor.getInt(5);
                String description = cursor.getString(6);
                String foodname = cursor.getString(7);
                binding.detailimage.setImageResource(image);
                binding.detailfoodname.setText(foodname);
                binding.detailDescription.setText(description);
                binding.detailprice.setText(String.valueOf(cursor.getInt(3)));
                binding.namebox.setText(cursor.getString(1));
                binding.phonebox.setText(cursor.getString(2));
                binding.insertbtn.setText("Update");
                binding.btncall.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        makePhoneCall();
                    }
                });

            binding.insertbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isUpdated = dbHelper.updateOrder(//img desc food qua,id
                                binding.namebox.getText().toString(),
                                binding.phonebox.getText().toString(),
                                Integer.parseInt(binding.detailprice.getText().toString()),
                                image,
                                description,
                                foodname,
                                1,
                                id
                        );

                        if (isUpdated)
                            Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DetailActivity.this, "Err", Toast.LENGTH_SHORT).show();


                    }
                });
        }
    }

    private String getFilename() {
        String filepath = Environment.getExternalStorageDirectory().getPath();
        File file = new File(filepath, AUDIO_RECORDER_FOLDER);

        if (!file.exists()) {
            file.mkdirs();
        }

        return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + file_exts[currentFormat]);
    }

    private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
        @Override
        public void onError(MediaRecorder mr, int what, int extra) {
            Toast.makeText(DetailActivity.this,
                    "Error: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
        }
    };

    private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
        @Override
        public void onInfo(MediaRecorder mr, int what, int extra) {
            Toast.makeText(DetailActivity.this,
                    "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT)
                    .show();
        }
    };

    private void makePhoneCall(){

        DbHelper dbHelper = new DbHelper(this);
        int  id = getIntent().getIntExtra("id",0);
        Cursor cursor = dbHelper.getOrderbyId(id);
        String dial = "tel:"+cursor.getString(2);
        Toast.makeText(this, "calling"+dial, Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(DetailActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},REQUESTCALL);
            
        }
        else{
            Toast.makeText(this, "Request Granted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESTCALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }
        }

    }

}