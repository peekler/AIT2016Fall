package hu.ait.camerastream;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CameraTextureView.PreviewUploader {

	private FrameLayout camHolder;
	private CameraTextureView camTextureView;
	private LinearLayout layoutMain;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        layoutMain = (LinearLayout) findViewById(R.id.layoutMain);
		camHolder = (FrameLayout) findViewById(R.id.camHolder);
        
        Button btnTakePicture = (Button) findViewById(R.id.btnPhoto);
        btnTakePicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				camTextureView.takePhoto(pc);
			}
		});
        


		requestNeededPermission();
    }

	public void requestNeededPermission() {
		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.CAMERA)
				!= PackageManager.PERMISSION_GRANTED) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(this,
					Manifest.permission.CAMERA)) {
				Toast.makeText(MainActivity.this,
						"I need it for camera", Toast.LENGTH_SHORT).show();
			}

			ActivityCompat.requestPermissions(this,
					new String[]{Manifest.permission.CAMERA},
					101);
		} else {
			addCamTextureView();
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
										   String permissions[], int[] grantResults) {
		switch (requestCode) {
			case 101: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					addCamTextureView();
				} else {
					Toast.makeText(MainActivity.this,
							"CAMERA perm NOT granted", Toast.LENGTH_SHORT).show();
				}
				return;
			}
		}
	}

	private void addCamTextureView() {
		camTextureView = new CameraTextureView(this);

		camHolder.addView(camTextureView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

		camTextureView.setPreviewUploader(this);
	}
    
    private PictureCallback pc =new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			ImageView v = new ImageView(MainActivity.this);
	        v.setImageBitmap(bitmap);
	        layoutMain.addView(v);
		}
	};

	@Override
	public void previewReady(byte[] preview) {
		AsyncTaskUploadImage taskUpload = new AsyncTaskUploadImage(
				MainActivity.this, preview);
		taskUpload.execute("http://atleast.aut.bme.hu/EklerP/preview/upload.php");
	}

}