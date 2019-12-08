package com.example.roomlivedatademo.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.roomlivedatademo.database.dao.UserModelDao;
import com.example.roomlivedatademo.model.UserModel;
/**
 * Created by USER on 11/27/2017.
 */

@Database(entities = {UserModel.class}, version = 1,exportSchema = false)
public abstract class RoomLiveDatabase extends RoomDatabase {
    public abstract UserModelDao userModelDao();

    private static RoomLiveDatabase INSTANCE;

    static RoomLiveDatabase getDatabase(final Context context) {
        if (INSTANCE == null)
        {
            synchronized (RoomLiveDatabase.class) {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomLiveDatabase.class, "RoomLiveDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

