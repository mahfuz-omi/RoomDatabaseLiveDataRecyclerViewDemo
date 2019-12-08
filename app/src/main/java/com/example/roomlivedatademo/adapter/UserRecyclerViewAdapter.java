package com.example.roomlivedatademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomlivedatademo.R;
import com.example.roomlivedatademo.activity.LiveDBActivity;
import com.example.roomlivedatademo.database.UserViewModel;
import com.example.roomlivedatademo.model.UserModel;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    Context context;
    List<UserModel> userModels;
    LayoutInflater layoutInflater;

    public UserRecyclerViewAdapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(List<UserModel> userModels)
    {
        this.userModels = userModels;
        this.notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.layoutInflater.inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        final UserModel userModel = this.userModels.get(position);
        holder.idTextView.setText(userModel.id+"");
        holder.nameTextView.setText(userModel.getUserName());
        holder.ageTextView.setText(userModel.getAge()+"");
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserViewModel userViewModel = ViewModelProviders.of((LiveDBActivity)context).get(UserViewModel.class);
                userViewModel.delete(userModel.id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.userModels != null)
            return this.userModels.size();
        else
            return 0;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView idTextView;
        TextView nameTextView;
        TextView ageTextView;
        Button deleteButton;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            idTextView = (TextView) itemView.findViewById(R.id.idTextView);
            nameTextView  = (TextView) itemView.findViewById(R.id.nameTextView);
            ageTextView = itemView.findViewById(R.id.ageTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);

        }


    }

}

