package com.example.roomlivedatademo.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomlivedatademo.database.dao.UserModelDao;
import com.example.roomlivedatademo.model.UserModel;

import java.util.List;

public class UserRepository {
    private UserModelDao userModelDao;
    private LiveData<List<UserModel>> userListLiveData;

    public UserRepository(Application application) {
        RoomLiveDatabase roomLiveDatabase = RoomLiveDatabase.getDatabase(application);
        userModelDao = roomLiveDatabase.userModelDao();
        userListLiveData = userModelDao.getAll();
    }

    LiveData<List<UserModel>> getAll() {
        return userListLiveData;
    }

    public void insert(UserModel userModel) {
        new InsertAsyncTask(userModelDao).execute(userModel);
    }

    public void delete(Integer integer) {
        new DeleteAsyncTask(userModelDao).execute(integer);
    }

    private static class InsertAsyncTask extends AsyncTask<UserModel, Void, Void> {

        private UserModelDao mAsyncTaskDao;

        InsertAsyncTask(UserModelDao userModelDao) {
            mAsyncTaskDao = userModelDao;
        }

        @Override
        protected Void doInBackground(final UserModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private UserModelDao mAsyncTaskDao;

        DeleteAsyncTask(UserModelDao userModelDao) {
            mAsyncTaskDao = userModelDao;
        }

        @Override
        protected Void doInBackground(final Integer ... integers) {
            mAsyncTaskDao.deleteById(integers[0]);
            return null;
        }
    }
}
