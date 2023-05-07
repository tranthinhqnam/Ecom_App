package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Category;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.Utilities.StorageUtils;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private ArrayList<Category> categories;
    private final LayoutInflater mInfalter;
    private Context context;
    private String userId;

    public CategoryAdapter(Context context) {
        mInfalter = LayoutInflater.from(context);
        this.categories = new ArrayList<Category>();
        this.context = context;
    }

    public void setCategories(ArrayList<Category> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setUserId(String userId){
        this.userId = userId;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.category_item, viewGroup, false);
        return new CategoryViewHolder(mItemView, this, context);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CategoryViewHolder viewHolder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Category mCurrent = categories.get(position);
        viewHolder.setUserId(userId);
        viewHolder.setId(mCurrent.getId());
        viewHolder.getTv_CategoryName().setText(mCurrent.getName());
        StorageUtils.loadStorageImageIntoImageView("category-img", mCurrent.getImg_name(), viewHolder.getIv_Image());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
