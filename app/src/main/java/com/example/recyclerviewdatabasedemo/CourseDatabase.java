package com.example.recyclerviewdatabasedemo;

// (DB10) import room database:
import androidx.room.Database;
import androidx.room.RoomDatabase;

// (DB11) convert this class into a room database --> @Database + specifiy which class (Course):

@Database(entities = {Course.class}, version = 1)

//(DB12) --> abstract + extends RoomDatabase
public abstract class CourseDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();


}
