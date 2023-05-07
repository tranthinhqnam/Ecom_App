package com.example.appmobileclothes.UI.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.R;
import com.example.appmobileclothes.ViewModels.CartViewModel;

public class CartViewHolder extends RecyclerView.ViewHolder {
    private CartAdapter cartAdapter;
    private Context mainActivity;
    private TextView tv_title, tv_price, tv_quantity, btn_add, btn_sub;
    private ImageView iv_image;
    Button btn_delete;
    private String id;
    private int tempQuan, maxQuan;

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(TextView tv_title) {
        this.tv_title = tv_title;
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

    public ImageView getIv_image() {
        return iv_image;
    }

    public void setIv_image(ImageView iv_image) {
        this.iv_image = iv_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaxQuan() {
        return maxQuan;
    }

    public void setMaxQuan(int maxQuan) {
        this.maxQuan = maxQuan;
    }

    public void setTempQuan(int tempQuan) {
        this.tempQuan = tempQuan;
    }

    public CartViewHolder(@NonNull View itemView, CartAdapter cartAdapter, Context context) {
        super(itemView);

        tv_title = itemView.findViewById(R.id.tv_title);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_quantity = itemView.findViewById(R.id.tv_quantity);
        iv_image = itemView.findViewById(R.id.iv_cart_item);
        btn_delete = itemView.findViewById(R.id.btn_delete);
        btn_add = itemView.findViewById(R.id.btn_add);
        btn_sub = itemView.findViewById(R.id.btn_sub);

        this.mainActivity = context;
        this.cartAdapter = cartAdapter;
        btn_delete.setOnClickListener(deleteProdCart);
        btn_sub.setOnClickListener(quantityChange);
        btn_add.setOnClickListener(quantityChange);
    }

    public View.OnClickListener deleteProdCart = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CartViewModel.deleteCart(id);
            cartAdapter.notifyDataSetChanged();
        }
    };

    public View.OnClickListener quantityChange = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_sub:
                    if (tempQuan > 1) {
                        tempQuan -= 1;
                        CartViewModel.updateCart(id, tempQuan);
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);

                        builder.setMessage("Do you want to remove this product?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        CartViewModel.deleteCart(id);
                                        cartAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog);
                        dialog.show();
                    }
                    break;
                case R.id.btn_add:
                    if (tempQuan < maxQuan) {
                        tempQuan += 1;
                        CartViewModel.updateCart(id, tempQuan);
                        cartAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
}
