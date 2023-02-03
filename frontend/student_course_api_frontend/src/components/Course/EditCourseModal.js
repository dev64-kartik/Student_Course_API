import React from "react" 
import CourseName from "./CourseFormelements/CourseName";
import CourseEducator from "./CourseFormelements/CourseEducator";
import CourseFees from "./CourseFormelements/CourseFees";

const bootstrap  = window.bootstrap;

export default function EditCourseModal(props)
{
        
    function handleSubmit(event)
    {
        event.preventDefault();
        const myModal = bootstrap.Modal.getInstance((document.getElementById('editcourse')))
         myModal.hide();
         props.editCourse(event);
    }
    
    
        return (
            <div class="modal fade" id="editcourse">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Student Profile Update</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                <form className="form" id="updatecourse" onSubmit={handleSubmit}>
                  <CourseName name={props.name}/>
                  <CourseEducator educator={props.educator} />
                  <CourseFees fees={props.fees}/>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button form="updatecourse" type="submit" id="modalsubmitupdate" class="btn btn-primary">Save changes</button>
                </div>
              </div>
            </div>
          </div>
        )
}