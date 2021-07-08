package com.sngur.khublei;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.khublei_logo);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                /* ... */
            }

            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();

    }
    public void openCardDesign(View view) {
        int i=view.getId();
        Intent intent= new Intent(this,CardDesignList.class);
        switch (i)
        {
            case R.id.khasi:
                intent.putExtra("Language","Khasi");
                break;
            case R.id.garo:
                intent.putExtra("Language","Garo");
                break;
            case R.id.pnar:
                intent.putExtra("Language","Pnar");
                break;
            case R.id.hindi:
                intent.putExtra("Language","Hindi");
                break;
            case R.id.english:
                intent.putExtra("Language","English");
                break;
        }
        startActivity(intent);
    }
    public void openAbout(View view) {
        Intent intent= new Intent(this,AboutActivity.class);
        startActivity(intent);
    }
}
