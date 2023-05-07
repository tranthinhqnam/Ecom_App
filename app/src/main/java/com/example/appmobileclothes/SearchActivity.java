package com.example.appmobileclothes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;

import com.example.appmobileclothes.UI.Adapters.ProductAdapter;
import com.example.appmobileclothes.ViewModels.ProductViewModel;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private SearchView searchView;
    private Button btn_male, btn_female;
    int pressedBtnId = 0;
    private String gender;
    private int categoryId;
    private ProductViewModel productViewModel;
    private ProductAdapter productAdapter;
    TextView tv_return;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_male = findViewById(R.id.btn_male);
        btn_male.setOnClickListener(this);
        btn_female = findViewById(R.id.btn_female);
        btn_female.setOnClickListener(this);
        tv_return = findViewById(R.id.tv_return);
        gridView = findViewById(R.id.gridview_product_search);

        pressButton(btn_male);

        searchView = findViewById(R.id.searchView_search);
        searchView.clearFocus();

        Intent intent = getIntent();

        // Retrieve the data from the Intent
        String productName = intent.getStringExtra("productName");
        categoryId = intent.getIntExtra("categoryId", 99);

        //GridView for products
        productAdapter = new ProductAdapter(getBaseContext());
        gridView.setAdapter(productAdapter);

        //Retrieve products data
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String productName) {
                productViewModel.searchProducts(productName, categoryId, gender).observe(SearchActivity.this, products -> {
                    if (products != null) {
                        productAdapter.setProducts(products);
                    }
                });

                return true;
            }

            @Override
            public boolean onQueryTextChange(String productName) {
                productViewModel.searchProducts(productName, categoryId, gender).observe(SearchActivity.this, products -> {
                    if (products != null) {
                        productAdapter.setProducts(products);
                    }
                });

                return true;
            }
        });

        searchView.setQuery(productName, false);

        tv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String productId = gridView.getItemIdAtPosition(position) + "";
                productViewModel.getProductByIdFromDb(productId).observe(SearchActivity.this, product -> {
                    Intent intent = new Intent(SearchActivity.this, ProductActivity.class);
                    intent.putExtra("data", product);
                    intent.putExtra("userId", getIntent().getStringExtra("userId"));
                    startActivity(intent);
                });
            }
        });
    }

    private void changeBtnColor(@NonNull Button btn) {
        btn.setBackgroundColor(getColor(R.color.secondary_color));
        btn.setTextColor(getColor(R.color.white));
        pressedBtnId = btn.getId();
    }

    private void disposeBtnColor(@NonNull Button btn) {
        btn.setBackgroundColor(getColor(R.color.white));
        btn.setTextColor(getColor(R.color.black));
        pressedBtnId = 0;
    }

    private void pressButton(Button btn) {
        String btnText = btn.getText().toString();
        gender = btnText;

        //If pressed button's id is already existed -> dispose it
        if (pressedBtnId != 0) {
            disposeBtnColor(findViewById(pressedBtnId));
        }

        changeBtnColor(btn);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        pressButton(btn);

        // Manually trigger search even if search query is empty
        String query = searchView.getQuery().toString();
        if (query.isEmpty()) {
            onQueryTextSubmit("");
        } else {
            searchView.setQuery(query, true);
        }
    }

    private void onQueryTextSubmit(String query) {
        productViewModel.searchProducts(query, categoryId, gender).observe(SearchActivity.this, products -> {
            if (products != null) {
                productAdapter.setProducts(products);
            }
        });
    }
}