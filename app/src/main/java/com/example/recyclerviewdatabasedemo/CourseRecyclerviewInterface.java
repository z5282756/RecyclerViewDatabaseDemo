package com.example.recyclerviewdatabasedemo;

public interface CourseRecyclerviewInterface {
    //40. add a method so that whenever the application uses this interface, need to implement
    // the classes for that interface: (we have method below but no implementation for the method)
    void onCourseClick(int position);

    //41. now in in MainAcitivty recyclerView, going to use/implement the interface


}
