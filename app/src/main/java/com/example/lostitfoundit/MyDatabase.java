package com.example.lostitfoundit;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Post.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {

    public abstract AllDao getAllDao();

    public static MyDatabase myDatabase;

    public static MyDatabase getMyDatabase(Context context) {
        if (myDatabase == null) {
            myDatabase = Room.databaseBuilder(context,
                            MyDatabase.class, "AndroidDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return myDatabase;
    }

}
