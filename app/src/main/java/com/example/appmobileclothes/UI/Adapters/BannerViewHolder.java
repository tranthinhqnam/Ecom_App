package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;
import com.makeramen.roundedimageview.RoundedImageView;

public class BannerViewHolder extends RecyclerView.ViewHolder {
    private BannerAdapter bannerAdapter;
    private Context mainActivity;
    private int id;
    private RoundedImageView imageView;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoundedImageView getImageView() {
        return imageView;
    }

    public void setImageView(RoundedImageView imageView) {
        this.imageView = imageView;
    }

    public BannerViewHolder(@NonNull View itemView, BannerAdapter bannerAdapter, Context mainActivity) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.imageSlide);
        this.mainActivity = mainActivity;
        this.bannerAdapter = bannerAdapter;
    }
}
