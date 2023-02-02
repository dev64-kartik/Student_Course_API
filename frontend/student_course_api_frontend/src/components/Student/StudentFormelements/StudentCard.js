import React from "react";
import {Link} from "react-router-dom";

export default function StudentCard(props)
{    
    const data = props.data;  


    return (
        <div className="card-container">
        {data.map((student=>{
            const {name,id,college} = student;
            return (<div className="card card-component" id={id}>
            {/* <p className="card-header text-center card_header">
                <span>{name}</span>
                <span>
                <i className="bi bi-trash3-fill trash-icon" onClick={handleClick}></i>
                </span>
                </p> */}
            <div className="card-body card_body">
                <p className="card-text">Student Id: {id}</p>
                <p className="card-text">Name: {name}</p>
                <p className="card-text">College: {college}</p>
                <Link to={`/students/${id}`} state={{...student}} className="btn view-dashboard-btn" >View Dashboard</Link>
            </div>
        </div>)
        }))}
    </div>
    )
}