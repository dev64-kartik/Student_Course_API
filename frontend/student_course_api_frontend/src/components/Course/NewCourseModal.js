import React from "react";
import CourseName from "./CourseFormelements/CourseName";
import CourseEducator from "./CourseFormelements/CourseEducator"
import CourseFees from "./CourseFormelements/CourseFees"

const bootstrap = window.bootstrap;

export default function NewCourseModal(props)
{   
    
    function handleSubmit(event)
    {  
        event.preventDefault();
       const myModal = bootstrap.Modal.getInstance((document.getElementById('newcourse')))
        myModal.hide();
        props.newCourse(event);
    }    

    return (
        <div class="modal fade" id="newcourse">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Course Registration</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <form className="form" id="addcourse" onSubmit={handleSubmit}>
       <CourseName/>
       <CourseEducator/>
       <CourseFees/>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button form="addcourse" type="submit" id="modalsubmit" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
    )
}