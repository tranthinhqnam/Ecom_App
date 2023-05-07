package com.example.appmobileclothes.UI.Framents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appmobileclothes.Models.Order;
import com.example.appmobileclothes.R;
import com.example.appmobileclothes.UI.Adapters.OrderAdapter;
import com.example.appmobileclothes.ViewModels.OrderViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    OrderViewModel orderViewModel;
    String user_id;
    LinearLayout linear_order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_order, container, false);
        linear_order = contentView.findViewById(R.id.linear_order);

        Bundle args = getArguments();
        if (args != null) {
            user_id = args.getString("id");
        }

        //RecyclerView for Carts
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_order);
        OrderAdapter orderAdapter = new OrderAdapter(contentView.getContext());
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(contentView.getContext()));

        //Retrieve carts data
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getOrdersLiveData().observe(getViewLifecycleOwner(), orders -> {
            if (orders != null) {
                ArrayList<Order> list = OrderViewModel.getOrdersByUserId(orders, user_id);
                orderAdapter.setOrders(list);
                if (list.size() < 1) {
                    linear_order.setVisibility(View.VISIBLE);
                }
            }
        });

        return contentView;
    }
}