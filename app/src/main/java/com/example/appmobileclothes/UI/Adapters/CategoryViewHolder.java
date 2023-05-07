package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;
import com.example.appmobileclothes.SearchActivity;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private CategoryAdapter categoryAdapter;
    private Context context;
    private TextView tv_CategoryName;
    private ImageView iv_Image;
    private int id;
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public TextView getTv_CategoryName() {
        return tv_CategoryName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTv_CategoryName(TextView tv_CategoryName) {
        this.tv_CategoryName = tv_CategoryName;
    }

    public ImageView getIv_Image() {
        return iv_Image;
    }

    public void setIv_Image(ImageView iv_Image) {
        this.iv_Image = iv_Image;
    }

    public CategoryViewHolder(View itemView, CategoryAdapter categoryAdapter, Context context) {
        super(itemView);

        tv_CategoryName = itemView.findViewById(R.id.tv_CategoryName);
        iv_Image = itemView.findViewById(R.id.iv_category_item);

        this.context = context;
        this.categoryAdapter = categoryAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(context, SearchActivity.class);
        i.putExtra("categoryId", id);
        i.putExtra("userId", userId);
        context.startActivity(i);
    }
}