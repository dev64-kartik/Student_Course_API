import React from "react";
import CourseSelector from "./CourseFormelements/CourseSelector";
import axios from "axios";

const bootstrap = window.bootstrap;

export default function EnrollCourseModal(props)
{   

    function handleSubmit(event)
    {
        event.preventDefault();
        const myModal = bootstrap.Modal.getInstance((document.getElementById('enrollcourse')))
        myModal.hide();
        props.enroll();

       
    }  

    return (
        <div class="modal fade" id="enrollcourse">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Course Registration</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      <form className="form" id="enrollintocourse" onSubmit={handleSubmit}>
        <CourseSelector/>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button form="enrollintocourse" type="submit" id="modalsubmit" class="btn btn-primary">Enroll</button>
      </div>
    </div>
  </div>
</div>
    )
}