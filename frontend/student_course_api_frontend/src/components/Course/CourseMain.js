import React from 'react';
import Notification from '../Notification';
import CourseCard from './CourseFormelements/CourseCard';
import NewCourseModal from './NewCourseModal';
import axios from "axios";
import {useNavigate} from "react-router-dom";

const bootstrap = window.bootstrap;

function CourseMain(props) {

    const [courses, setCourses] = React.useState([]);
    const navigate = useNavigate();
    
    React.useEffect(()=>{
        axios.get("http://localhost:8080/courses").then((res)=>{
           setCourses(res.data);
           // window.courses=res.data;

        }).catch((error)=>{
           console.log(error);
        })
   },[]);



    function newCourse(event)
    {
        let toast;
        axios.post("http://localhost:8080/courses",{
          name:event.target[0].value,
          educator:event.target[1].value,
          fees:event.target[2].value
      }).then((res)=>{
        window.selectedCourses.clear();
        toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-info-course"));
        console.log(res.data);
        // navigate(`/courses/${res.data.courseId}`,{state:{
        //     ...res.data}
        // })
        toast.show();
        setCourses((prevcourses)=>{
            return [...prevcourses,res.data]
        })
       
        }).catch((err)=>{
          toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-courseexists"));
          toast.show();
        })
    }
     

    function deleteCourse(courseId)
    {
        axios.delete(`http://localhost:8080/courses/${courseId}`)
        .then((res)=>{
            setCourses(courses.filter((course)=>{
                return course.courseId != courseId;
            }))
        }).catch(err=>{
            console.log(err);
        })
    }


    return ( 
        <main>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Available Courses</span>
        <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#newcourse"></i>
        </p>
        <CourseCard data={courses} showDashboard={true} unenroll={deleteCourse}/>
        <NewCourseModal newCourse={newCourse}/>
        <Notification info={true} role="notification-info-course" message="Course Added Successfully !"/>
        <Notification warning={true} role="notification-warning-courseexists" message="Course Already Exists"/>
        </main>
     );
}

export default CourseMain;