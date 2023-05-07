package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.OrderDetail;
import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.Utilities.StorageUtils;
import com.example.appmobileclothes.ViewModels.ProductViewModel;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailViewHolder> {
    private ArrayList<OrderDetail> orderDetails;
    private ArrayList<Product> products;
    private Context context;
    private final LayoutInflater mInfalter;

    public OrderDetailAdapter(Context context) {
        this.products = new ArrayList<>();
        this.orderDetails = new ArrayList<>();
        this.context = context;
        this.mInfalter = LayoutInflater.from(context);
    }

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
        notifyDataSetChanged();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.order_detail_item, parent, false);
        return new OrderDetailViewHolder(mItemView, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetail mCurrent = orderDetails.get(position);
        holder.setId(mCurrent.getId());

        Product product = ProductViewModel.getProductByIdFromList(products, mCurrent.getProd_id());

        holder.getTv_price().setText(mCurrent.getPrice() + "");
        holder.getTv_quantity().setText(mCurrent.getQuantity() + "");
        holder.getTv_title().setText(mCurrent.getName());
        holder.getTv_total().setText(String.valueOf((mCurrent.getPrice() * mCurrent.getQuantity())));
        StorageUtils.loadStorageImageIntoImageView("product-img", product.getImg(), holder.getIv_img());
    }

    @Override
    public int getItemCount() {
        return orderDetails.size();
    }
}
