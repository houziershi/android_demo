package com.example.admin.myapplication_app;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snack_bar_layout);
        TextView textView = findViewById(R.id.tv_btn);
//        Shader textShader = new LinearGradient(0, 0, textView.getPaint().getTextSize()* textView.getText().length(), 0,
//                new int[]{Color.parseColor("#FFF3DD"), Color.parseColor("#FDE1BB72")},
//                new float[]{0, 1}, Shader.TileMode.CLAMP);
//        textView.getPaint().setShader(textShader);
//        textView.invalidate();

        SpannableString string = new SpannableString("Text with a background color span");
        string.setSpan(new BackgroundColorSpan(R.drawable.test), 12, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(string);
//        textView.setText(getGradientSpan(textView.getText().toString(), Color.BLUE, Color.RED, true));
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mSnackBar = Snackbar.make(view, "Custom Color", Snackbar.LENGTH_LONG);
                View snackbarLayout = mSnackBar.getView();
                System.out.println("hgk........."+snackbarLayout.getClass().getSimpleName());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                // Layout must match parent layout type
                lp.setMargins(0, 600, 0, 0);
                // Margins relative to the parent view.
                // This would be 50 from the top left.
                snackbarLayout.setLayoutParams(lp);
                mSnackBar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Action!", Snackbar.LENGTH_SHORT).show();
                    }
                });
                mSnackBar.show();
            }
        });


    }

    /**
     * 动态设置TextView文字的横向或纵向渐变色
     * @param string
     * @param startColor
     * @param endColor
     * @return
     */
    public static SpannableStringBuilder getGradientSpan(String string, int startColor, int endColor, boolean isLeftToRight) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        LinearGradientFontSpan span = new LinearGradientFontSpan(startColor, endColor, isLeftToRight);
        spannableStringBuilder.setSpan(span, 0, spannableStringBuilder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 若有需要可以在这里用SpanString系列的其他类，给文本添加下划线、超链接、删除线...等等效果
        return spannableStringBuilder;
    }

}
