import React from 'react';
import CourseCard from './CourseFormelements/CourseCard';

function CourseDashboard(props) {

    



    return ( 
        <main>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Available Courses</span>
        <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#editstudent"></i>
        </p>
        {/* <CourseCard data={coursedata} unenroll={unenrollFromCourse}/> */}
        </main>
     );
}

export default CourseDashboard;