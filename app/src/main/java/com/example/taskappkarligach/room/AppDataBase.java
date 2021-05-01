package com.example.taskappkarligach.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.taskappkarligach.TaskModel;

@Database(entities = {TaskModel.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract TaskModelDao taskModelDao();

}
