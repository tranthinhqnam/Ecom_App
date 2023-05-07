package com.example.appmobileclothes.Repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseRepository {
    private DatabaseReference dbRef;

    public FirebaseRepository() {
        dbRef = FirebaseDatabase.getInstance().getReference();
    }

    public <T> LiveData<ArrayList<T>> getFirebaseData(String nodePath, Class<T> dataType) {
        MutableLiveData<ArrayList<T>> firebaseData = new MutableLiveData<>();
        dbRef.child(nodePath).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<T> dataList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    T data = dataSnapshot.getValue(dataType);
                    dataList.add(data);
                }
                firebaseData.setValue(dataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseData.setValue(null);
            }
        });
        return firebaseData;
    }

    public <T> LiveData<T> getFirebaseSingleData(String nodePath, Class<T> dataType) {
        MutableLiveData<T> firebaseData = new MutableLiveData<>();
        dbRef.child(nodePath).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                T data = snapshot.getValue(dataType);
                firebaseData.setValue(data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                firebaseData.setValue(null);
            }
        });
        return firebaseData;
    }

    public void deleteFirebaseData(String nodePath, String key) {
        dbRef.child(nodePath).child(key).removeValue();
    }

    public void updateFirebaseData(String nodePath, String key, String detailPath, Object newDetail) {
        dbRef.child(nodePath).child(key).child(detailPath).setValue(newDetail);
    }

    public void updateFirebaseData(String nodePath, String key, String detailPath, Object newDetail,Context context, String success,String fail) {
        dbRef.child(nodePath).child(key).child(detailPath).setValue(newDetail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, fail, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getKey(String nodePath) {
        String key = dbRef.child(nodePath).push().getKey();
        return key;
    }

    public <T> void addFirebaseData(String nodePath, T data, String key) {
        dbRef.child(nodePath).child(key).setValue(data);
    }

    public <T> void addFirebaseData(String nodePath, T data, String key, Context context, String success, String fail) {
        dbRef.child(nodePath).child(key).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, fail, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

