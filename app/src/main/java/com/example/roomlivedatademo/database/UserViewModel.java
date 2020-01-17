package com.example.roomlivedatademo.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomlivedatademo.model.UserModel;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<UserModel>> userListLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.userListLiveData = userRepository.getAll();
    }

    public LiveData<List<UserModel>> getUserListLiveData()
    {
        return this.userListLiveData;
    }

    public void insert(UserModel userModel)
    {
        this.userRepository.insert(userModel);
    }

    public void delete(int id)
    {
        this.userRepository.delete(id);
    }
}
