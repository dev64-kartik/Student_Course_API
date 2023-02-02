import React from "react";
import axios from "axios";
import StudentName from "./StudentFormelements/StudentName";
import StudentCollege from "./StudentFormelements/StudentCollege";
import CourseSelector from "../Course/CourseFormelements/CourseSelector"
import Notification from "../Notification";

const bootstrap = window.bootstrap;

export default function NewStudentModal(props)
{      

    function handleSubmit(event)
    {  
        event.preventDefault();
       const myModal = bootstrap.Modal.getInstance((document.getElementById('newstudent')))
        myModal.hide();
        props.newStudent(event);
    }    



    return (
        <div class="modal fade" id="newstudent">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Student Registration</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <form className="form" id="addstudent" onSubmit={handleSubmit}>
        <StudentName />
        <StudentCollege />
        <CourseSelector/>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button form="addstudent" type="submit" id="modalsubmit" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
    )
}