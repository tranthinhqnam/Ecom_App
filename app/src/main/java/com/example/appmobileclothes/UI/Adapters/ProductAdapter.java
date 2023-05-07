package com.example.appmobileclothes.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.Utilities.StorageUtils;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    private ArrayList<Product> products;
    private Context context;

    public ProductAdapter(Context context) {
        this.products = new ArrayList<Product>();
        this.context = context;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final MyView dataItem;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            dataItem = new MyView();
            convertView = inflater.inflate(R.layout.product_item, null);
            dataItem.iv_product_item_home = convertView.findViewById(R.id.iv_product_item_home);
            dataItem.tv_product_name_home = convertView.findViewById(R.id.tv_product_name_home);
            dataItem.tv_product_price_home = convertView.findViewById(R.id.tv_product_price_home);
            convertView.setTag(dataItem);
        } else {
            dataItem = (MyView) convertView.getTag();
        }

        if (products.size() > 0) {
            dataItem.tv_product_name_home.setText(products.get(i).getName());
            dataItem.tv_product_price_home.setText(products.get(i).getPrice() + "");
            StorageUtils.loadStorageImageIntoImageView("product-img", products.get(i).getImg(), dataItem.iv_product_item_home);
        }

        return convertView;
    }

    private static class MyView {
        ImageView iv_product_item_home;
        TextView tv_product_name_home, tv_product_price_home;
    }
}
