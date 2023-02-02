import React from "react";
import axios from "axios";


export default function CourseCard(props)
{   
     const data = props.data;   
     
     function handleClick(event)
     {  
        props.unenroll(event.target.parentElement.parentElement.parentElement.id);
     }
     

    return (
        <div className="card-container">
            {data.map((course=>{
                const {name,courseId,educator,fees} = course;
                return (<div className="card card-component" id={courseId}>
                <p className="card-header text-center card_header">
                    <span>{name}</span>
                    <span>
                    <i className="bi bi-trash3-fill trash-icon" onClick={handleClick}></i>
                    </span>
                    </p>
                <div className="card-body card_body">
                    <p className="card-text">CourseId: {courseId}</p>
                    <p className="card-text">Educator: {educator}</p>
                    <p className="card-text">
                        Fees: Rs {fees} 
                    </p>
                </div>
            </div>)
            }))}
        </div>
    )
}