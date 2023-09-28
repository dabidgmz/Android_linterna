package com.example.linterna;

import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnToggleFlashlight = findViewById(R.id.btnToggleFlashlight);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

            btnToggleFlashlight.setEnabled(false);
            return;
        }


        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        btnToggleFlashlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFlashlight();
            }
        });
    }

    private void toggleFlashlight() {
        try {
            if (isFlashlightOn) {

                cameraManager.setTorchMode(cameraId, false);
                isFlashlightOn = false;
            } else {
                cameraManager.setTorchMode(cameraId, true);
                isFlashlightOn = true;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}
