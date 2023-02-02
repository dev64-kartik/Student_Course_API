import React from "react";
import { Link } from "react-router-dom";

export default function NavBar() {
  const [isSelected, setisSelected] = React.useState({
    isCourse: false,
    isStudent: true,
  });

  function handleClick(event) {
    if (event.target.id === "student" && !isSelected.isStudent) {
      setisSelected({
        isStudent: true,
        isCourse: false,
      });
      
    }
    if (event.target.id === "course" && !isSelected.isCourse) {
      setisSelected({
        isCourse: true,
        isStudent: false,
      });
    }
  }

  return (
    <nav class="navbar navbar-expand-md bg-dark" data-bs-theme="dark">
      <ul class="navbar-nav nav-pills mynav">
        <li className={`nav-item ${isSelected.isStudent ? "active" : ""}`}>
          <Link
            class="nav-link"
            to="/students"
            onClick={handleClick}
            id="student"
          >
            Students
          </Link>
        </li>
        <li className={`nav-item ${isSelected.isCourse ? "active" : ""}`}>
          <Link
            class="nav-link"
            to="/courses"
            onClick={handleClick}
            id="course"
          >
            Courses
          </Link>
        </li>
      </ul>
    </nav>
  );
}
