import axios from "axios";
import React from "react";
import CourseCard from "../Course/CourseFormelements/CourseCard";
import EnrollCourseModal from "../Course/EnrollCourseModal";
import Notification from "../Notification";
import EditStudentModal from "./EditStudentModal";
import {useLocation,useParams} from "react-router-dom";

const bootstrap = window.bootstrap;

export default function StudentDashboard(props)
{   
    const location = useLocation();
    const params = useParams();
    const [coursedata,setCoursedata] = React.useState(props.data ? props.data.enrolledCourses : (location.state ? location.state.enrolledCourses || [] : []));
    const [studentdata, setStudentdata] = React.useState({
        ...props.data || location.state || {}
    })
    

    React.useEffect(()=>{
        if((props.data === undefined && location.state === null) || location.state.callAPI)
        {
            axios.get(`http://localhost:8080/students/${params.id}`)
            .then(res=>{
                setStudentdata({name:res.data.name, id:res.data.id, college:res.data.college})
                setCoursedata(res.data.enrolledCourses);
            })
            .catch(err=>{
                
            })
        }
    },[])
    
    
    
    function unenrollFromCourse(courseId)
    {   
    
        axios.delete(`http://localhost:8080/students/${studentdata.id}/courses/${courseId}`)
        .then((res)=>{
            setCoursedata(coursedata.filter((course)=>{
                return course.courseId != courseId;
            }))
        }).catch(err=>{
            console.log(err);
        })
    }
    
    async function enrollIntoCourses()
    {   
        let toast;
        let AlreadyenrolledCourses = [];
        let promises=[];

        for(let course of window.selectedCourses.values())
            promises.push(axios.post(`http://localhost:8080/students/${studentdata.id}/courses/${course.courseId}`,{}));

        let res = await Promise.allSettled(promises);
        let index=0;

        for(let course of window.selectedCourses.values())
        {   
            if(res[index++].status === "rejected")
                    AlreadyenrolledCourses.push(course);
        }

            if(AlreadyenrolledCourses.length === 0)
                 toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-info-courseenrolled"));
            else
            {   
                let El = document.getElementById("notification-warning-coursealreadyenrolled");
                let message = "";
                AlreadyenrolledCourses.forEach((course)=>{
                    message+=`${course.name}, `;
                    window.selectedCourses.delete(course);
                })
                El.childNodes[0].childNodes[0].innerHTML = "Failed to enroll in " + message;
                toast = bootstrap.Toast.getOrCreateInstance(El);
            }
            toast.show();
           // console.log(window.selectedCourses); 
            setCoursedata((prevcoursedata)=>{
                const newcoursedata = [...prevcoursedata];
                window.selectedCourses.forEach((course)=>{
                    newcoursedata.push(course);
                })
                return newcoursedata;
            });

    }


    function editStudent(event)
    {
        let toast;
        axios.put(`http://localhost:8080/students/${studentdata.id}`,{
          name:event.target[0].value,
          college:event.target[1].value,
      }).then((res)=>{
        toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-info-studentupdated"));
        toast.show();
        setStudentdata(res.data);

        }).catch((err)=>{

            if(err.response.status === 409)
                toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-exists"));
          
             toast.show();
        })
    }


    return (
        <>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Student Profile</span>
        <i className="bi bi-pencil-fill icon" data-bs-toggle="modal" data-bs-target="#editstudent"></i>
        </p>
        <section className="profile">
            <p>
            Name : {studentdata.name}
            </p>
            <p>
            College : {studentdata.college}
            </p>
        </section>
        <p className="layout-dashboard">
        <span style={{fontSize:25 + "px"}} >Enrolled Courses</span>
        <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#enrollcourse"></i>
        </p>
        <CourseCard data={coursedata} unenroll={unenrollFromCourse}/>
        <EnrollCourseModal enroll={enrollIntoCourses}/>
        <EditStudentModal editStudent={editStudent} name={studentdata.name} college={studentdata.college}/>
        <Notification info={true} role="notification-info-courseenrolled" message="Succesfully Enrolled in Courses !"/>
        <Notification info={true} role="notification-info-studentupdated" message="Student Profile Updated Successfully !"/>
        <Notification warning={true} role="notification-warning-coursealreadyenrolled"/>
        </>
    )
}