package com.example.appmobileclothes;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.Models.OrderDetail;
import com.example.appmobileclothes.UI.Adapters.OrderDetailAdapter;
import com.example.appmobileclothes.ViewModels.OrderDetailViewModel;
import com.example.appmobileclothes.ViewModels.OrderViewModel;
import com.example.appmobileclothes.ViewModels.ProductViewModel;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailViewModel orderDetailViewModel;
    ProductViewModel productViewModel;
    OrderViewModel orderViewModel;
    TextView tv_date, tv_total, tv_return;
    String orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        recyclerView = findViewById(R.id.recycler_orderDetail);
        tv_date = findViewById(R.id.tv_date);
        tv_total = findViewById(R.id.tv_total);
        tv_return = findViewById(R.id.tv_return);


        OrderDetailAdapter orderDetailAdapter = new OrderDetailAdapter(this);
        recyclerView.setAdapter(orderDetailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderId = extras.getString("orderId");
        }

        orderDetailViewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        orderDetailViewModel.getOrdersLiveData().observe(this, orderDetail -> {
            if (orderDetail != null) {
                ArrayList<OrderDetail> list = OrderDetailViewModel.getOrderDetailByOrderId(orderDetail, orderId);
                orderDetailAdapter.setOrderDetails(list);
            }
        });

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductsLiveData().observe(this, products -> {
            if (products != null) {
                orderDetailAdapter.setProducts(products);
            }
        });

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getOrdersLiveData().observe(this, orders -> {
            if (orders != null) {
                Order order = OrderViewModel.getOrderById(orders, orderId);
                tv_date.setText(order.getDate());
                tv_total.setText(order.getTotal());
            }
        });

        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}