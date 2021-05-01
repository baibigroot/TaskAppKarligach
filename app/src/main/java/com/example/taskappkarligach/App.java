package com.example.taskappkarligach;

import android.app.Application;
import androidx.room.Room;

import com.example.taskappkarligach.room.AppDataBase;

public class App extends Application {

    public static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(this,AppDataBase.class,"database")
                .allowMainThreadQueries().build();
    }
}
