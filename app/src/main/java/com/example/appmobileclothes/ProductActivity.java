package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.Utilities.StorageUtils;
import com.example.appmobileclothes.ViewModels.CartViewModel;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_add2cart, bt_sub, bt_add;
    TextView tv_quantity, tv_name, tv_price, tv_total;
    ImageView iv_image;
    int quantity = 1, max, cost, total_price;
    String total;
    LinearLayout rollback;
    CartViewModel cartViewModel;
    Product product;
    ArrayList<Cart> cartArrayList;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        iv_image = findViewById(R.id.iv_image);
        tv_quantity = findViewById(R.id.tv_quantity);
        tv_name = findViewById(R.id.tv_name);
        tv_price = findViewById(R.id.tv_price);
        tv_total = findViewById(R.id.tv_total);
        rollback = findViewById(R.id.rollback);

        assignId(bt_add2cart, R.id.bt_add2cart);
        assignId(bt_sub, R.id.bt_sub);
        assignId(bt_add, R.id.bt_add);

        cartArrayList = new ArrayList<Cart>();

        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        cartViewModel.getCartsLiveData().observe(this, carts -> {
            if (carts != null) {
                cartArrayList = carts;
            }
        });
        userId = getIntent().getStringExtra("userId");

        product = (Product) getIntent().getSerializableExtra("data");

        cost = product.getPrice();
        max = product.getQuantity();

        tv_name.setText(product.getName());
        tv_price.setText(Integer.toString(cost));
        tv_total.setText(Integer.toString(cost));

        StorageUtils.loadStorageImageIntoImageView("product-img", product.getImg(), iv_image);

        rollback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sub: {
                if (quantity == 1) {
                    break;
                }

                quantity -= 1;
                tv_total.setText(totalPrice(quantity));

                break;
            }
            case R.id.bt_add: {
                if (quantity == max) {
                    break;
                }

                quantity += 1;
                tv_total.setText(totalPrice(quantity));

                break;
            }
            case R.id.bt_add2cart: {
                if (cartArrayList.size() > 0) {
                    ArrayList<Cart> cartArrayListById = CartViewModel.getCartsByUserId(cartArrayList, userId);
                    for (Cart cart : cartArrayListById) {
                        if (cart.getProd_id() == product.getId()) {
                            int newQuan = quantity + cart.getQuantity();
                            CartViewModel.updateCart(cart.getId(), newQuan, this, "You have been added this product successfully", "Fail");
                            Intent intent = new Intent(this, MainActivity.class);
                            intent.putExtra("id", userId);
                            startActivity(intent);
                            return;
                        }
                    }
                }
                addCart();
                break;
            }
            case R.id.rollback: {
                onBackPressed();
                break;
            }
        }
    }

    public void assignId(Button btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    public String totalPrice(int quantity) {
        total = Integer.toString(quantity);
        tv_quantity.setText(total);
        total_price = cost * quantity;
        return Integer.toString(total_price);
    }

    public void addCart() {
        String key = cartViewModel.getCartKey();
        Cart newCart = new Cart(key, userId, product.getId(), quantity);
        cartViewModel.addCart(newCart, key, getBaseContext(), "You have been added this product successfully", "Fail");
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", userId);
        startActivity(intent);
    }
}