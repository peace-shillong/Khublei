package com.sngur.khublei;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class GenerateGreetingCard extends AppCompatActivity {

    Bundle data;
    String language,personName;

    private static int RESULT_LOAD_IMAGE = 1;
    static Button btn2;
    static LinearLayout myView;
    TextView greetings;
    private String design;
    private String greet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_greeting_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_hand);

        btn2= findViewById(R.id.shareMyView);
        myView= findViewById(R.id.myView);
        greetings= findViewById(R.id.greeting);

        data=getIntent().getExtras();
        language=data.getString("Language");
        personName=data.getString("Name");
        design=data.getString("Card");
        greet=data.getString("Greet");

        if(greet!=null)
        greetings.setText( greet+" "+personName);

        switch (design)
        {
            case "BCard1":
                greetings.setText("Happy Birthday "+personName);
                break;
        }

    }

    public void openGallery(View view) {
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.photo);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            //Enable btn2 and set onCLickListener
            btn2.setEnabled(true);
            btn2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    //save and share image
                    Toast.makeText(GenerateGreetingCard.this, "Preparing to share Greeting Card", Toast.LENGTH_SHORT).show();
                    myView.setDrawingCacheEnabled(true);
                    Bitmap b = myView.getDrawingCache();
                    try {
                        //Create File and compress it
                        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "shared_image.png");
                        FileOutputStream stream = new FileOutputStream(file);
                        b.compress(Bitmap.CompressFormat.JPEG, 95, stream);
                        stream.flush();
                        stream.close();
                        //if SDK below else
                        //Uri uri = Uri.fromFile(file);
                        Uri uri  = FileProvider.getUriForFile(GenerateGreetingCard.this, getApplicationContext().getPackageName()+ ".provider", file);
                        //share
                        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_STREAM, uri);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        intent.setType("image/*");
                        intent.putExtra(Intent.EXTRA_TEXT, "Happy Birthday "+personName+"!");
                        startActivity(Intent.createChooser(intent, "Share"));


                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });


        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    //Back Button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
