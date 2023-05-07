package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Category;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Category>> categoriesLiveData;

    public CategoryViewModel() {
        firebaseRepository = new FirebaseRepository();
        categoriesLiveData = firebaseRepository.getFirebaseData("Categories", Category.class);
    }

    public LiveData<ArrayList<Category>> getCategoriesLiveData() {
        return categoriesLiveData;
    }
}
