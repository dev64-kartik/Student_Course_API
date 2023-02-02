import logo from './logo.svg';
import './App.css';
import NavBar from './components/NavBar';
import {Link, Routes,Route, Navigate} from "react-router-dom"
import StudentMain from './components/Student/StudentMain';
import CourseDashboard from './components/Course/CourseDashboard';
import StudentDashboard from './components/Student/StudentDashboard';


function App() {
  return (
    <>
    <NavBar />
    <Routes>
      ""
      <Route path="/courses" element={<CourseDashboard/>}/>
     {/* <Route path="/" element={<Navigate to="/students"/>}/>
      <Route path="/courses" element={
       <GetCourse viewAll={true}/>
        }/>
        <Route
          path="/courses/view-course"
          element={
             <GetCourse/>
          }
        />
        <Route
          path="/courses/add-course"
          element={
            <PostCourse/>
          }
        />
        <Route
          path="/courses/update-course"
          element={
            <PutCourse/>
          }
        />
        <Route
          path="/courses/delete-course"
          element={
            <DeleteCourse/>
          }
        />*/}
      
      <Route path="/" element={<Navigate to="/students"/>}/>
      <Route path="/students" element={<StudentMain/>}/>
      <Route path="/students/:id" element={<StudentDashboard/>}/>
      {/* <Route path="/students/:id" element={}/> */}
      { /* <Route
          path="/students/view-student"
          element={
            <GetStudent/>
          }
        />
        <Route
          path="/students/add-student"
          element={
           <PostStudent/>
          }
        />
        <Route
          path="/students/update-student"
          element={
           <PutStudent/>
          }
        />
        <Route
          path="/students/delete-student"
          element={
           <DeleteStudent/>
          }
        />
        <Route
          path="/students/enroll"
          element={
            <EnrollStudent/>
          }
        />
        <Route
          path="/students/unenroll"
          element={
            <UnenrollStudent/>
          }
        />*/}
        
        <Route path="/test"/>

    </Routes>
  </>
);
}

export default App;
