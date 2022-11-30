package com.example.recyclerviewdatabasedemo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NonNls;

// (DB1) first add room database implementation into gradle file
// (DB2) if have existing java class, turn it into an entity --> @Entity:
@Entity

// (DB3) create interface (CourseDao) for DAO (to lists all methods for using database)

public class Course {
    // (DB17) add primary key (@PrimaryKey) and makes sure it is not null (@NonNull):
    @PrimaryKey
    @NonNull

    // 7. define the 2 attribute objects of course dataset (course code, and course name)
    private String code;
    private String name;

    //9(cont.). create constructor method with EXACT name of class --> it will pass 2 parameter:
    // one for the code and other for the name
    public Course(String courseCode, String courseName) {
        //10. then inside constructor method -->
        this.code = courseCode;
        this.name = courseName;
    }

    // (DB18) add empty constructor (because creating random course data. if was using API,
    // would not need this):
    public Course() {

    }


    // 8. to access these attributes, must use getter and setter methods; getter/setter methods must
    // 8(cont.).be public to access them form outside this class.
    // 8(cont.) Type "pub" and click enter on 'getCode' and type "pub" and click enter on 'setCode'
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    // 9(cont.) Type "pub" and click enter on 'getName' and type "pub" and click enter on 'setName'
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // 9.whenever you want to instantiate an object from this class and pass some values to be part
    // of the object or instance
    // 9.you need to pass the values using constructor method (Object Orienting Programming)
    // 9.constructor method must be public to access outside this class --> (go to 9(cont.))

    // 11. go to Main Activity and create new private method to getData().

    // 13. create method in Course java class to create a list of data (random generated course
    // code and name)

}

