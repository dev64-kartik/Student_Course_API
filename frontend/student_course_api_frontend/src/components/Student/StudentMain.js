import React from "react";
import LayoutMain from "../Layouts/LayoutMain";
import StudentID from "../Student/StudentFormelements/StudentID"
import NewStudentModal from "./NewStudentModal";
import Notification from "../Notification";
import StudentDashboard from "./StudentDashboard";
import axios from "axios";

const bootstrap = window.bootstrap;

export default function StudentMain()
{   
    const [studentdata, setstudentdata] = React.useState({});
    let toast;

    function handleSubmit(event)
    {   
        event.preventDefault();
        axios.get(`http://localhost:8080/students/${event.target[0].value}`)
        .then((res)=>{
            setstudentdata(res.data);
        }).catch((err)=>{
            if(err.response.status === 404)
            {
                toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-notexists"));
                toast.show();
            }
            else
                console.log(err);
        })
        
    }

    function newStudent(event)
    {
        let toast;
        axios.post("http://localhost:8080/students",{
          name:event.target[0].value,
          college:event.target[1].value,
          enrolledCourses:[...window.selectedCourses]
      }).then((res)=>{
        window.selectedCourses.clear();
        toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-info-student"));
        toast.show();
        setstudentdata(res.data);
        }).catch((err)=>{
          toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-exists"));
          toast.show();
        })
    }

    if(Object.keys(studentdata).length !== 0)
        return <StudentDashboard data={studentdata} />
    else
    {
        return (    
            <>
            <LayoutMain>
                <form className="form" onSubmit={handleSubmit}>
                    <StudentID />
                    <button className="btn btn-outline-primary form-submit" >Submit</button>
                  </form>
                  <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#newstudent"></i>
            </LayoutMain>
            <NewStudentModal newStudent={newStudent}/>
            <Notification info={true} role="notification-info-student" message="Student added successfully !"/>
            <Notification warning={true} role="notification-warning-notexists" message="Student Does Not Exist !"/>
            <Notification warning={true} role="notification-warning-exists" message="Student Already Exists !"/>
            </>
            )
    }
    
}