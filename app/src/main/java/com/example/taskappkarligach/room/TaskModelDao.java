package com.example.taskappkarligach.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.taskappkarligach.TaskModel;

import java.util.List;

@Dao
public interface TaskModelDao {

    @Query("SELECT*FROM taskModel")
    LiveData<List<TaskModel>> getAll();

    @Query("SELECT * from TaskModel order by title asc")
    List<TaskModel> getSortedList();

    @Insert
    void insert(TaskModel model);

    @Query("delete from TaskModel where id=:id")
    void delete(long id);

    @Update
    void update(TaskModel task);

}
