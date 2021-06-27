package com.example.flightgear10;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class JSV extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {
    private float pointX;
    private float pointY;
    private float radiusOfCircle;
    private float generalRadius;
    private listener joystickCallback;


    private void setupDimensions() {
        pointX = getWidth() / 2;
        pointY = getHeight() / 2;
        radiusOfCircle = Math.min(getWidth(), getHeight()) / 3;
        generalRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    public JSV(Context context) {
        super(context);


        if (context instanceof listener) {
            joystickCallback = (listener) context;
        }
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public JSV(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (context instanceof listener) {
            joystickCallback = (listener) context;
        }
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public JSV(Context context, AttributeSet attributeSet, int style) {
        super(context, attributeSet, style);
        if (context instanceof listener) {
            joystickCallback = (listener) context;
        }
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        setupDimensions();
        draw(pointX, pointY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public boolean onTouch(View view, MotionEvent me) {

        if (view.equals(this)) {
            if (me.getAction() != me.ACTION_UP) {
                float place = (float) Math.sqrt((Math.pow(me.getX() - pointX, 2)) + Math.pow(me.getY() - pointY, 2));
                if (place < radiusOfCircle) {
                    draw(me.getX(), me.getY());

                    if (joystickCallback != null) {

                        joystickCallback.onJoystickMoved((me.getX() - pointX) / radiusOfCircle, (me.getY() - pointY) / radiusOfCircle, getId());
                    }
                } else {
                    float ratio = radiusOfCircle / place;
                    float constrainedX = pointX + (me.getX() - pointX) * ratio;
                    float constrainedY = pointY + (me.getY() - pointY) * ratio;
                    draw(constrainedX, constrainedY);
                    if (joystickCallback != null) {

                        joystickCallback.onJoystickMoved((constrainedX - pointX) / radiusOfCircle, (constrainedY - pointY) / radiusOfCircle, getId());
                    }
                }
            } else {
                draw(pointX, radiusOfCircle);
                if (joystickCallback != null) {

                    joystickCallback.onJoystickMoved(0, 0, getId());
                }
            }
        }
        return true;
    }

    public interface listener {
        void onJoystickMoved(float x, float y, int id);
    }

    public void draw(float y, float x) {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = this.getHolder().lockCanvas();
            Paint painter = new Paint();
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            painter.setARGB(255, 50, 50, 50);
            canvas.drawCircle(pointX, pointY, radiusOfCircle, painter);
            painter.setARGB(150, 11, 156, 49);
            canvas.drawCircle(x, y, generalRadius, painter);
            getHolder().unlockCanvasAndPost(canvas);

        }
    }

}
