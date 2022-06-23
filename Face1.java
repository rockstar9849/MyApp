package com.example.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;

import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

//カメラプレビューで顔を検出する編集する
//FaceDetection
//
// その１
// https://seesaawiki.jp/w/moonlight_aska/d/%a5%d7%a5%ec%a5%d3%a5%e5%a1%bc%b2%e8%c1%fc%a4%f2%c9%bd%bc%a8%a4%b9%a4%eb

// その２
// https://seesaawiki.jp/w/moonlight_aska/d/%a5%ab%a5%e1%a5%e9%a5%d7%a5%ec%a5%d3%a5%e5%a1%bc%a4%c7%b4%e9%a4%f2%b8%a1%bd%d0%a4%b9%a4%eb

// プレビューを縦向きに表示する
// https://seesaawiki.jp/w/moonlight_aska/d/%A5%D7%A5%EC%A5%D3%A5%E5%A1%BC%A4%F2%BD%C4%B8%FE%A4%AD%A4%CB%C9%BD%BC%A8%A4%B9%A4%EB

//【重要】事前にスマホ端末の[設定]-[アプリと通知]-[FaceDetection_SMP]-[権限]で
//       カメラの権限を有効にすること。

public class Face1 extends Activity
        implements SurfaceHolder.Callback, Camera.FaceDetectionListener {
    private Camera mCamera = null;
    private SurfaceView mView = null;
    private CameraOverlay mCameraOverlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SurfaceView preview = (SurfaceView) findViewById(R.id.preview_id);
        mView = new SurfaceView(this);
        setContentView(mView);

        SurfaceHolder holder = mView.getHolder();
        holder.addCallback(this);
        mCameraOverlayView = new CameraOverlay(this);
        addContentView(mCameraOverlayView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        //-----------画面の明るさを調整---------------------------
        final WindowManager.LayoutParams lp = getWindow().getAttributes();
        //------------------------------------------------------

        //---------------画面明るさを設定-------------
        lp.screenBrightness = 1.0f;
        getWindow().setAttributes(lp);
        //-------------------------------------------




    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        int cameraId = 0;
        mCamera = Camera.open(1);
        // リスナーの登録
        mCamera.setFaceDetectionListener(this);

        // ディスプレイの向き設定
        setCameraDisplayOrientation(cameraId);


        try {
            // プレビューの設定
            mCamera.setPreviewDisplay(holder);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        mCamera.stopFaceDetection();
        mCamera.stopPreview();

        // プレビュー画面のサイズ設定
        Camera.Parameters params = mCamera.getParameters();


        // プレビュー開始
        mCamera.startPreview();

        // 顔検出対応か?
        if (params.getMaxNumDetectedFaces() > 0) {
            // 顔検出開始
            mCamera.startFaceDetection();
        } else {
            Log.e("FaceDetector", "Face detection is not supported.");
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.release();
        mCamera = null;
    }

    @Override
    public void onFaceDetection(Face[] faces, Camera camera) {
        mCameraOverlayView.setFaces(faces);

        if (faces.length > 0) {
            Log.v("FaceDetection", faces.length + " faces");

            camera.stopFaceDetection();
            camera.stopPreview();
            camera.release();
            camera = null;

            //Face1 から password2 へ
            Intent intent = new Intent(Face1.this, Password2.class);
            startActivity(intent);
            finish();
        }
    }

    // ディスプレイの向き設定
    public void setCameraDisplayOrientation(int cameraId) {
        // カメラの情報取得
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);

        // ディスプレイの向き取得
        int rotation = getWindowManager().getDefaultDisplay().getRotation();

        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        // プレビューの向き計算
        int result;
        if (cameraInfo.facing == cameraInfo.CAMERA_FACING_FRONT) {
            result = (cameraInfo.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (cameraInfo.orientation - degrees + 360) % 360;
        }

        // ディスプレイの向き設定
        mCamera.setDisplayOrientation(result);
    }

}