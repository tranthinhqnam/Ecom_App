package com.example.appmobileclothes.UI.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.OrderDetailActivity;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.OrderAdapter;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private OrderAdapter orderAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_date, tv_price;
    private String id;

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
    }

    public TextView getTv_date() {
        return tv_date;
    }

    public void setTv_date(TextView tv_date) {
        this.tv_date = tv_date;
    }

    public TextView getTv_price() {
        return tv_price;
    }

    public void setTv_price(TextView tv_price) {
        this.tv_price = tv_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderViewHolder(@NonNull View itemView, OrderAdapter orderAdapter, Context context) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_date = itemView.findViewById(R.id.tv_date);

        this.mainActivity = context;
        this.orderAdapter = orderAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mainActivity, OrderDetailActivity.class);
        intent.putExtra("orderId", id);
        mainActivity.startActivity(intent);
    }
}
