import React from "react";
import LayoutMain from "../Layouts/LayoutMain";
import StudentID from "../Student/StudentFormelements/StudentID"
import NewStudentModal from "./NewStudentModal";
import Notification from "../Notification";
import StudentDashboard from "./StudentDashboard";
import axios from "axios";
import StudentCard from "./StudentFormelements/StudentCard";
import {useNavigate} from "react-router-dom";

const bootstrap = window.bootstrap;

export default function StudentMain()
{   
   // const [studentdata, setstudentdata] = React.useState({});
    const [allstudentdata, setallstudentdata] = React.useState([]);
    const navigate = useNavigate();
    let toast;


    React.useEffect(()=>{
        axios.get("http://localhost:8080/students")
        .then((res)=>{
            setallstudentdata(res.data);
        })
        .catch(err=>{
            console.log(err);
        })
    },[]);

    function handleSubmit(event)
    {   
        event.preventDefault();
        // axios.get(`http://localhost:8080/students/${event.target[0].value}`)
        // .then((res)=>{
        //     setstudentdata(res.data);
        // }).catch((err)=>{
        //     if(err.response.status === 404)
        //     {
        //         toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-notexists"));
        //         toast.show();
        //     }
        //     else
        //         console.log(err);
        // })
        const studentdata = allstudentdata.find((student)=>{
            return student.studentId == event.target[0].value;
        })
        if(!studentdata)
        {
            toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-notexists"));
            toast.show();
        }
        else
        {
            navigate(`/students/${studentdata.studentId}`, {state:
                studentdata
            })
        }

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
        navigate(`/students/${res.data.id}`,{state:{
            ...res.data}
        })
        }).catch((err)=>{
          toast = bootstrap.Toast.getOrCreateInstance(document.getElementById("notification-warning-exists"));
          toast.show();
        })
    }

        return (    
            <>
            <LayoutMain>
                <form className="form" onSubmit={handleSubmit}>
                    <StudentID />
                    <button className="btn btn-outline-primary form-submit" >Submit</button>
                  </form>
                  <i className="bi bi-plus-circle-fill icon" data-bs-toggle="modal" data-bs-target="#newstudent"></i>
            </LayoutMain>
            <StudentCard data={allstudentdata}/>
            <NewStudentModal newStudent={newStudent}/>
            <Notification info={true} role="notification-info-student" message="Student added successfully !"/>
            <Notification warning={true} role="notification-warning-notexists" message="Student Does Not Exist !"/>
            <Notification warning={true} role="notification-warning-exists" message="Student Already Exists !"/>
            </>
            )
    
}