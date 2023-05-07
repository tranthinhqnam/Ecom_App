package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;

public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
    private OrderDetailAdapter orderDetailAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_total, tv_price, tv_quantity;
    private ImageView iv_img;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public TextView getTv_total() {
        return tv_total;
    }

    public void setTv_total(TextView tv_total) {
        this.tv_total = tv_total;
    }

    public TextView getTv_price() {
        return tv_price;
    }

    public void setTv_price(TextView tv_price) {
        this.tv_price = tv_price;
    }

    public TextView getTv_quantity() {
        return tv_quantity;
    }

    public void setTv_quantity(TextView tv_quantity) {
        this.tv_quantity = tv_quantity;
    }

    public ImageView getIv_img() {
        return iv_img;
    }

    public void setIv_img(ImageView iv_img) {
        this.iv_img = iv_img;
    }

    public OrderDetailViewHolder(@NonNull View itemView, OrderDetailAdapter orderDetailAdapter, Context context) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_quantity = itemView.findViewById(R.id.tv_quantity);
        tv_total = itemView.findViewById(R.id.tv_total);
        iv_img = itemView.findViewById(R.id.iv_photo);

        this.mainActivity = context;
        this.orderDetailAdapter = orderDetailAdapter;
    }
}
