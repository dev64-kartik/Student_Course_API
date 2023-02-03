import React from "react";
import Notification from "../Notification";
import StudentCard from "../Student/StudentFormelements/StudentCard";
import EditCourseModal from "./EditCourseModal";
import { useLocation,useParams } from "react-router-dom";
import axios from "axios"

const bootstrap = window.bootstrap;

export default function CourseDashboard(props)
{   
    const location = useLocation();
    const params = useParams();
    const [course,setCourse] = React.useState({
        ...props.data || location.state || {}
    });
    

    React.useEffect(()=>{
        if(props.data === undefined && location.state === null)
        {
            axios.get(`http://localhost:8080/courses/${params.id}`)
            .then(res=>{
                setCourse(res.data);
            })
            .catch(err=>{
                console.log(err);
            })
        }
    },[])

    function editCourse(event)
    {   
        let toast;
        axios.put(`http://localhost:8080/courses/${course.courseId}`,{
          name:event.target[0].value,
          educator:event.target[1].value,
          fees:event.target[2].value
      }).then((res)=>{
        toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-info-courseupdated"));
        toast.show();
        setCourse(prevdata=>{
            return {
                courseId:res.data.courseId,
                name:res.data.name,
                college:res.data.college,
                fees:res.data.fees,
                enrolledStudents:prevdata.enrolledStudents
            }
        })

        }).catch((err)=>{

            if(err.response.status === 409)
                toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-courseexists"));
          
             toast.show();
        })

    }

    return (
        <>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Course Info</span>
        <i className="bi bi-pencil-fill icon" data-bs-toggle="modal" data-bs-target="#editcourse"></i>
        </p>
        <section className="profile">
            <p>
            Name : {course.name}
            </p>
            <p>
            Educator : {course.educator}
            </p>
            <p>
            Fees : {course.fees}
            </p>
        </section>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Enrolled Students</span>
        {/* <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#newstudentenrollcourse"></i> */}
        </p>
        <StudentCard data={course.enrolledStudents || []} />
        <EditCourseModal editCourse={editCourse} name={course.name} educator={course.educator} fees = {course.fees}/>
        {/* <Notification info={true} role="notification-info-courseenrolled" message="Succesfully Enrolled in Courses !"/> */}
        <Notification info={true} role="notification-info-courseupdated" message="Course Info Updated Successfully !"/>
        <Notification warning={true} role="notification-warning-courseexists" message="Course Already Exists"/>
        </>
    )
}