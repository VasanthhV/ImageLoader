package com.example.imageloader;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.mukesh.image_processing.ImageProcessor;

public class MainActivity extends Activity implements View.OnClickListener {


        private static int IMG_RESULT = 1;
        String ImageDecode;
        ImageView imageViewLoad;
        Button LoadImage,capture,btnefct1,btnefct2,btnefct3,btnefct4;
        Intent intent;
        String[] FILE;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            imageViewLoad = (ImageView) findViewById(R.id.imageView1);
            LoadImage = (Button) findViewById(R.id.button1);
            capture=(Button)findViewById(R.id.capture);
            btnefct1=(Button)findViewById(R.id.btnefct1);
            btnefct2=(Button)findViewById(R.id.btnefct2);
            btnefct3=(Button)findViewById(R.id.btnefct3);
            btnefct4=(Button)findViewById(R.id.btnefct4);
            capture.setOnClickListener(this);
            LoadImage.setOnClickListener(this);
            btnefct1.setOnClickListener(this);
            btnefct2.setOnClickListener(this);
            btnefct3.setOnClickListener(this);
            btnefct4.setOnClickListener(this);



        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            try {

                if (requestCode == IMG_RESULT && resultCode == RESULT_OK
                        && null != data) {


                    Uri URI = data.getData();
                    String[] FILE = {MediaStore.Images.Media.DATA};


                    Cursor cursor = getContentResolver().query(URI,
                            FILE, null, null, null);

                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(FILE[0]);
                    ImageDecode = cursor.getString(columnIndex);
                    cursor.close();
                    final Bitmap bitmapp = BitmapFactory.decodeFile(ImageDecode);

                    imageViewLoad.setImageBitmap(bitmapp);
                    btnefct1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageProcessor img1=new ImageProcessor();
                         Bitmap b1=img1.applyGaussianBlur(bitmapp);
                            Log.i("b1", String.valueOf(b1));
                            imageViewLoad.setImageBitmap(b1);
                        }
                    });


                }
            } catch (Exception e) {
                Toast.makeText(this, "Please try again", Toast.LENGTH_LONG)
                        .show();
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.button1:
                    intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMG_RESULT);
                    break;
                case R.id.capture:
                    Toast.makeText(getApplicationContext(),"You clicked me",Toast.LENGTH_LONG).show();
                    break;


            }

        }

    }


