package com.example.recyclerviewdatabasedemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// 18. create subclass in java through "extend":
// 19. need to pass on viewHolder --> each row/item on the list is a viewholder


// 54. add "implements Filterable" + implement its methods
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> implements Filterable {
    //33. --> private List<Course> mCourses;
    private List<Course> mCourses, mCoursesFiltered;
    // 58. add new attribute mCoursesFiltered --> private List<Course> mCourses, mCoursesFiltered;

    //45. add another attribute to class:
    private CourseRecyclerviewInterface mInterface;



    //46. BELOW: need to pass on instance from same interface from MainActivity to here
    // in public CourseAdapter -->
    // public CourseAdapter(List<Course> courses, CourseRecyclerviewInterface.courseInterface) {

    //31. create constructor method in courseAdapter class and pass List of Course class object:
    //  --> public CourseAdapter(List<Course> courses) { }
    public CourseAdapter(List<Course> courses, CourseRecyclerviewInterface courseInterface) {
        //34.
        mCourses = courses;

        //59. keep copy of the same list (courses) for mCourseFiltered so filtering done on the
        // copy not the original list:
        mCoursesFiltered = courses;

        //47.
        mInterface = courseInterface;
        //48. go to MainActivity --> adapter = new CourseAdapter...

    }



    // 19(cont.) "implement methods" to implement the abstract methods:
    // 20. add new XML file to decide viewholder layout

    @NonNull

    @Override
    // creates the ViewHolder
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //24. need to specify the layout we created and implement it here:
        // instantiate object from the view class:
        // LayoutInflater is the class we are going to use:
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,
                false);

        //25. instead of returning null, we return the viewholder we created but need to pass on
        // this view object to the constructor method
        // --> return new MyViewHolder(view);
        return new MyViewHolder(view, mInterface);
        //50. ABOVE:  pass on instance to MyViewHolder
        // --> return new MyViewHolder(view, mInterface);
        // and hover red line to add 2nd parameter to automatically add
    }

    @Override
    // sets/assign data for every ViewHolder
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {
        //36. bind course object:
        // --> Course course = mCourses.get(position);
        Course course = mCoursesFiltered.get(position);
        //68. need to change above to
        // --> Course course = mCoursesFiltered.get(position);

        //37. get code and name
        holder.mCode.setText(course.getCode());
        holder.mName.setText(course.getName());


    }

    @Override
    // gives the size of the list/ number of items in the recyclerView
    public int getItemCount() {
        //35. change return value to mCourses.size(); to set the list size dynamically:
        // --> return mCourses.size();
        return mCoursesFiltered.size();
        //67. need to change above getItemCount() to return mCoursesFiltered (filtered list)
        // --> return mCoursesFiltered.size();
    }

    //55. method automatically appears below
    @Override
    public Filter getFilter() {
        //56.instead of returning null, we return "new Filter"
        // + double click option to automaticly add method

        //57. add new attribute at top of CourseAdapter class

        return new Filter() {
            @Override
            // does the actual filtering
            protected FilterResults performFiltering(CharSequence charSequence) {
                //60. saved charsequence to the query object
                String query = charSequence.toString();
                //61. now check if query is empty or not:
                //61(cont). if query object is empty, the filtered list (mCoursesFiltered) equals original list (mCourses)
                if (query.isEmpty()) {
                    mCoursesFiltered = mCourses;
                } else {
                    //61(cont).but if there is a query, for every course object (courses) in the list of courses (mCourses) if
                    //61(cont). the course name (course.getName()) contains the query string (.contains(query)),
                    //61(cont). this is candidate for a search result and we add the 'course' object to the
                    //61(cont). 'filteredList' array list
                    ArrayList<Course> filteredList = new ArrayList<>();
                    for (Course course : mCourses) {
                        if(course.getName().contains(query)) {
                            filteredList.add(course);
                        }

                    }
                    //62. at the end of the loop, set the filteredList to equal the mCoursesFiltered
                    mCoursesFiltered = filteredList;


                }
                //63. need to convert the list to FilterResult:
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCoursesFiltered;
                return filterResults;

                //64. to display search --> go to MainActivity
            }

            @Override
            //publishes the filtering results
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                //64. now doing the opposite of step 63. --> convert filterResult to arraylist of Course objects
                // getting the values of filterResult and converting them to the Courses
                mCoursesFiltered = (ArrayList<Course>) filterResults.values;
                //65. once have list published (above) need to notify the adapter about the changes in the list:
                // --> notifyDataSetChanged();
                notifyDataSetChanged();

            }
        };
    }

    //21. implement myViewHolder sub-class & implement constructor
    class MyViewHolder extends RecyclerView.ViewHolder {
        //22. need to have 2 textview components (for code and for name):
        private TextView mCode, mName;


        public MyViewHolder(@NonNull View itemView, CourseRecyclerviewInterface mInterface) {
            super(itemView);
            //23.for the itemView need to set the view elements for 1 viewholder (code and name):
            // finding the view element from the view and then set:
            mCode = itemView.findViewById(R.id.codeTv);
            mName = itemView.findViewById(R.id.nameTv);

            //38. adding recyclerView to the itemView so that when we click
            // instantiate new object from the onCLickListener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //51.if anyone clicks on each itemview, we want to get the interface object and
                    //its method and pass on position of that item on the list to the onCourseClick:
                    // --> mInterface.onCourseClick(getAdapterPosition());
                    mInterface.onCourseClick(getAdapterPosition());

                    //52. go to MainActivity --> public void onCourseClick...

                    //53. add search function --> add new resource (XML) file
                    // --> file name = course_menu, resource type = menu
                    // --> change icon image to search bar + change showAsAction = always
                    // --> implement Filterable in CourseAdapter class

                }
            });

            //39. add new java class INTERFACE called CourseRecyclerviewInterface
            // in OOP --> interface is an abstract --> meaning don't have any implementation for it
            // instead have a list of methods for that interface and whenever in application want
            // to use the interface, you need to implement the method for that interface

        }
    }

    //DB21 (cont.)
    public void setData(ArrayList<Course> courses) {
        mCourses.clear();
        mCourses.addAll(courses);
        notifyDataSetChanged();
    }
}

