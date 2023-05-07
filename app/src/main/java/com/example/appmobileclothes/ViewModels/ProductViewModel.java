package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Product;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Product>> productsLiveData;

    public ProductViewModel() {
        firebaseRepository = new FirebaseRepository();
        productsLiveData = firebaseRepository.getFirebaseData("Products", Product.class);
    }

    public LiveData<ArrayList<Product>> getProductsLiveData() {
        return productsLiveData;
    }

    public static Product getProductByIdFromList(ArrayList<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public LiveData<Product> getProductByIdFromDb(String productId) {
        return firebaseRepository.getFirebaseSingleData("Products/" + productId, Product.class);
    }

    // Get products that match the given name and category
    public LiveData<ArrayList<Product>> searchProducts(String name, int category, String gender) {
        MutableLiveData<ArrayList<Product>> filteredData = new MutableLiveData<>();
        productsLiveData.observeForever(products -> {
            ArrayList<Product> filteredProducts = new ArrayList<>();
            for (Product product : products) {
                if (category == 99) {
                    if (product.getName().toLowerCase().contains(name.toLowerCase()) && product.getGender().toLowerCase().equals(gender.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                } else {
                    if (product.getName().toLowerCase().contains(name.toLowerCase()) && product.getCategory() == category && product.getGender().toLowerCase().equals(gender.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                }
            }
            filteredData.setValue(filteredProducts);
        });
        return filteredData;
    }
}
