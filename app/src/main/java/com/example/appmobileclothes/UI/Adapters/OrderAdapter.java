package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderViewHolder> {
    private ArrayList<Order> orders;
    private Context context;
    private String user_id;
    private final LayoutInflater mInfalter;

    public OrderAdapter(Context context) {
        this.orders = new ArrayList<>();
        this.context = context;
        mInfalter = LayoutInflater.from(context);
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View mItemView = mInfalter.inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(mItemView, this, context);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        Order mCurrent = orders.get(position);
        holder.setId(mCurrent.getId());

        holder.getTv_price().setText(mCurrent.getTotal());
        holder.getTv_date().setText(mCurrent.getDate());
        holder.getTv_title().setText(mCurrent.getTitle());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
