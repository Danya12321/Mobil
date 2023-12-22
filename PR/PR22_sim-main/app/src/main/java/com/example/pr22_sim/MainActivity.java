package com.example.pr22_sim;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    private GridView mGrid;
    private GridAdapter mAdapter;
    private TextView mStepScreen;
    private Chronometer mTimeScreen;

    private Integer StepCount;
    int GRID_SIZE = 6;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeScreen = (Chronometer) findViewById(R.id.timeview);
        mStepScreen = (TextView) findViewById(R.id.stepview);
//        Typeface type = Typeface.createFromAsset(getAssets(), "my-font.ttf");
        //       mTimeScreen.setTypeface(type);
        //     mStepScreen.setTypeface(type);

        StepCount = 0;
        mStepScreen.setText(StepCount.toString());

        mTimeScreen.start();

        mGrid = (GridView) findViewById(R.id.field);
        mGrid.setEnabled(true);

        mAdapter = new GridAdapter(this, GRID_SIZE, GRID_SIZE);
        mGrid.setAdapter(mAdapter);

        mGrid.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                mAdapter.checkOpenCells();
                if (mAdapter.openCell(position)) {
                    StepCount++;
                    mStepScreen.setText(StepCount.toString());
                }

                if (mAdapter.checkGameOver()) {
                    mTimeScreen.stop();
                    ShowGameOver();
                }
            }
        });
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            mGrid.setNumColumns(9);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            mGrid.setNumColumns(6);
    }

    private void ShowGameOver() {

        // Диалоговое окно
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        // Заголовок и текст
        alertbox.setTitle("Поздравляем!");
        String time = mTimeScreen.getText().toString();
        String TextToast = "Игра закончена \nХодов: " + StepCount.toString() + "\nВремя: " + time;
        alertbox.setMessage(TextToast);

        // Добавляем кнопку
        alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // закрываем текущюю Activity
                finish();
            }
        });

        // показываем окно
        alertbox.show();

    }

}