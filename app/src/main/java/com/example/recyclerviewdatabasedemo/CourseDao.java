package com.example.recyclerviewdatabasedemo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// (DB3) now need to convert this empty standard interface into a room
// DAO by using annotation --> @Dao:
@Dao
public interface CourseDao {

    // (DB4) return list of all course records from the table:
    @Query("SELECT * FROM course")
    List<Course> getCourses();

    // (DB5) return a course record with provided course code:
    @Query("SELECT * FROM course WHERE code == :courseCode")
    Course getCourse(String courseCode);

    // (DB6) Delete all records form course table:
    @Delete
    void deleteCourses(Course... courses);

    // (DB7) Delete all records by using @Query:
    @Query("DELETE FROM course")
    void deleteAll();

    // (DB8) Insert list of courses into course table:
    @Insert
    void insertCourses(Course... courses);

    // (DB9) now add new java class for database itself (CourseDatabase)

}
