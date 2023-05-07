package com.example.appmobileclothes.ViewModels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Cart;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {
    private static FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Cart>> cartsLiveData;

    public CartViewModel() {
        firebaseRepository = new FirebaseRepository();
        cartsLiveData = firebaseRepository.getFirebaseData("Carts", Cart.class);
    }

    public LiveData<ArrayList<Cart>> getCartsLiveData() {
        return cartsLiveData;
    }

    public static ArrayList<Cart> getCartsByUserId(ArrayList<Cart> cartList, String userId) {
        ArrayList<Cart> cartArrayList = new ArrayList<>();
        for (Cart cart : cartList) {
            if (cart.getUser_id().equals(userId)) {
                cartArrayList.add(cart);
            }
        }
        return cartArrayList;
    }

    public void addCart(Cart data, String key, Context context, String success, String fail) {
        firebaseRepository.addFirebaseData("Carts", data, key, context, success, fail);
    }

    public String getCartKey() {
        return firebaseRepository.getKey("Carts");
    }


    public static void deleteCart(String id) {
        firebaseRepository.deleteFirebaseData("Carts", id);
    }

    public static void updateCart(String id, int detail) {
        firebaseRepository.updateFirebaseData("Carts", id, "quantity", detail);
    }

    public static void updateCart(String id, int detail, Context context, String success, String fail) {
        firebaseRepository.updateFirebaseData("Carts", id, "quantity", detail, context, success, fail);
    }
}
