package com.example.roomlivedatademo.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomlivedatademo.model.UserModel;

import java.util.List;

@Dao
public interface UserModelDao {

    @Query("SELECT * FROM UserModel order by `id`")
    LiveData<List<UserModel>> getAll();

    @Query("DELETE  FROM UserModel WHERE id == :id")
    void deleteById(int id);

    @Insert
    void insert(UserModel userModel);


}
