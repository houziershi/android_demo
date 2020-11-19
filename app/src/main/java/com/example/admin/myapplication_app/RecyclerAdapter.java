package com.example.admin.myapplication_app;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.List;

/**
 * Discription:
 * Created by guokun on 2020/10/20.
 */
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Integer> normalList;

    RecyclerAdapter(List<Integer> normalList) {
        this.normalList = normalList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nomal_item, parent, false);
        return new NormalHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        System.out.println("hgk........position="+position);
        NormalHolder normalHolder = (NormalHolder) holder;
        normalHolder.textView.setText("位置：" + normalList.get(position));
    }


    @Override
    public int getItemCount() {
        return normalList.size();
    }

    /**
     * 正常布局的ViewHolder
     */
    public static class NormalHolder extends RecyclerView.ViewHolder {
        TextView textView;
        LottieAnimationView lav;

        public NormalHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            lav = itemView.findViewById(R.id.lottieLoadingV);
        }
    }
}
