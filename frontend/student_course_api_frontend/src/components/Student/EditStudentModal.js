import React from "react";
import StudentName from "./StudentFormelements/StudentName";
import StudentCollege from "./StudentFormelements/StudentCollege";

const bootstrap = window.bootstrap;


export default function EditStudentModal(props)
{   

    function handleSubmit(event)
    {
        event.preventDefault();
        const myModal = bootstrap.Modal.getInstance((document.getElementById('editstudent')))
         myModal.hide();
         props.editStudent(event);
    }


    return (
        <div class="modal fade" id="editstudent">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Student Profile Update</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
            <form className="form" id="updatestudent" onSubmit={handleSubmit}>
              <StudentName />
              <StudentCollege />
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
              <button form="updatestudent" type="submit" id="modalsubmitupdate" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div>
    )
}