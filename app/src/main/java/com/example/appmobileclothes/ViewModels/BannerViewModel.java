package com.example.appmobileclothes.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.appmobileclothes.Models.Banner;
import com.example.appmobileclothes.Repositories.FirebaseRepository;

import java.util.ArrayList;

public class BannerViewModel extends ViewModel {
    private FirebaseRepository firebaseRepository;
    private LiveData<ArrayList<Banner>> bannersLiveData;

    public BannerViewModel() {
        firebaseRepository = new FirebaseRepository();
        bannersLiveData = firebaseRepository.getFirebaseData("Banners", Banner.class);
    }

    public LiveData<ArrayList<Banner>> getBannersLiveData() {
        return bannersLiveData;
    }
}
