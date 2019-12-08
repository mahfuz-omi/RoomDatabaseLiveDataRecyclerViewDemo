package com.example.roomlivedatademo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomlivedatademo.R;
import com.example.roomlivedatademo.adapter.UserRecyclerViewAdapter;
import com.example.roomlivedatademo.database.UserViewModel;
import com.example.roomlivedatademo.model.UserModel;

import java.util.List;

public class LiveDBActivity extends AppCompatActivity {
    Button saveButton;
    EditText nameEditText, ageEditText;
    RecyclerView usersRecyclerView;
    UserRecyclerViewAdapter userRecyclerViewAdapter;

    private UserViewModel userViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_db);

        this.nameEditText = this.findViewById(R.id.nameEditText);
        this.ageEditText = this.findViewById(R.id.ageEditText);
        this.usersRecyclerView = this.findViewById(R.id.recyclerView);

        this.saveButton = this.findViewById(R.id.saveButton);
        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                UserModel userModel = new UserModel(userName,age);
                userViewModel.insert(userModel);

            }
        });

        this.usersRecyclerView = findViewById(R.id.recyclerView);
        this.userRecyclerViewAdapter = new UserRecyclerViewAdapter(this);
        usersRecyclerView.setAdapter(userRecyclerViewAdapter);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        this.userViewModel.getUserListLiveData().observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(final List<UserModel> userModels) {
                // Update the cached copy of the words in the adapter.
                userRecyclerViewAdapter.setData(userModels);
            }
        });


    }
}
