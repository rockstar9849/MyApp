package com.example.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera.Face;
import android.util.AttributeSet;
import android.view.View;



public class CameraOverlay extends View{


    /** 塗りつぶし設定の {@link Paint} です。 */
    private Paint mPaint;

    /** 検出した顔情報の配列です。 */
    private Face[] mFaces;

    public CameraOverlay(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public CameraOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CameraOverlay(Context context) {
        super(context);
        initialize();
    }

    /** 初期化します。 */
    private void initialize() {
        // 塗りつぶしの設定
        mPaint = new Paint();
        //線の太さ
        mPaint.setStrokeWidth(20.0f);

        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setAlpha(128);

        mPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 検出した顔情報の配列をセットします。
     * @param faces 検出した顔情報の配列
     */
    public void setFaces(Face[] faces) {
        mFaces = faces;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mFaces == null) {
            return;
        }
        for (Face face : mFaces) {
            if (face == null) {
                continue;
            }
            // 短形で塗りつぶす
            Matrix matrix = new Matrix();
            matrix.postScale(getWidth() / 2000f, getHeight() / 2000f);
            matrix.postTranslate(getWidth() / 2f, getHeight() / 2f);
            int saveCount = canvas.save();
            canvas.concat(matrix);
            canvas.drawRect(face.rect, mPaint);
            canvas.restoreToCount(saveCount);


        }

    }

}
