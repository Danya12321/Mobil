package com.example.pr27;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Draw2D extends View {

    private Paint mPaint = new Paint();
    private Rect mRect = new Rect();
    private Bitmap mBitmap;

    public Draw2D(Context context) {
        super(context);

        // Выводим значок из ресурсов
        Resources res = this.getResources();
        mBitmap = BitmapFactory.decodeResource(res, R.drawable.cat);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        // стиль Заливка
        mPaint.setStyle(Paint.Style.FILL);

        // закрашиваем холст белым цветом
        mPaint.setColor(Color.WHITE);
        canvas.drawPaint(mPaint);

        // Рисуем жёлтый круг
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.YELLOW);
        //canvas.drawCircle(950, 30, 25, mPaint);
        canvas.drawCircle(width - 30, 30, 400, mPaint);

        // Рисуем облако
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        //canvas.drawCircle(950, 30, 25, mPaint);
        canvas.drawCircle(width - 1000, 150, 100, mPaint);

        mPaint.setColor(Color.parseColor("#c81dd1")); // фиолетовый цвет
        canvas.drawRect(width - 998, 240, 1123, 680, mPaint);


        // Рисуем зелёный прямоугольник
        mPaint.setColor(Color.GREEN);
        //canvas.drawRect(20, 650, 950, 680, mPaint);
        canvas.drawRect(0, canvas.getHeight() - 250, width, height, mPaint);

        // Рисуем текст
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(80);
        canvas.drawText("Лужайка ", 30, height - 300, mPaint);

        // Текст под углом
        // int x = 810;

        int x ;
        int y = 190;
        if (isLandscape) {
            x = width - 300;
            y = y + 400;
        } else {
            x = width - 200;
            y = 200;
        }
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(60);
        String beam = "Лучик солнца!";

        canvas.save();
        // Создаём ограничивающий прямоугольник для наклонного текста
        // поворачиваем холст по центру текста
        canvas.rotate(-45, 1500 + mRect.exactCenterX(), 1000 + mRect.exactCenterY ());

        // Рисуем текст
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(beam, x, y, mPaint);

        // восстанавливаем холст
        canvas.restore();

        // Выводим изображение
        //canvas.drawBitmap(mBitmap, 450, 1620, mPaint);
        canvas.drawBitmap(mBitmap, width - mBitmap.getWidth(), height - mBitmap.getHeight() - 10, mPaint);
    }
}