package com.example.admin.myapplication_app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.myapplication_app.custom.AVLoadingIndicatorView;
import com.example.admin.myapplication_app.custom.LineScaleIndicator;

public class MainActivity extends AppCompatActivity {

    private AVLoadingIndicatorView avi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        LineScaleIndicator lineScaleIndicator = new LineScaleIndicator();
        avi.setIndicator(lineScaleIndicator);

        // TODO: 2018/12/31
        /*
        test git revert
         */
    }

    public void hideClick(View view) {
        avi.hide();
        // or avi.smoothToHide();
    }

    public void showClick(View view) {
        avi.show();
        // or avi.smoothToShow();
    }
}
