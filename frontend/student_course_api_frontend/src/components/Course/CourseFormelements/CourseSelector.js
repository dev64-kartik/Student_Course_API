import React from 'react';
import axios from 'axios';
import Multiselect from 'multiselect-react-dropdown';

window.selectedCourses = new Set();

export default function CourseSelector()
{   
    const [coursedata,setCoursedata] = React.useState([]);
    
    React.useEffect(()=>{
         axios.get("http://localhost:8080/courses").then((res)=>{
            setCoursedata(res.data);
            // window.courses=res.data;

         }).catch((error)=>{
            console.log(error);
         })
    },[]);

    return (
        <div className='ro g-1 mb-3 form-element'>
            <label htmlFor='courseselector' className='form-label'>
                Select Course to Enroll
            </label>
            {/* <select id="courseselector" className='form-select' value={course} onChange={(event)=>(setCourse(event.target.value))}>
                <option value=""> ---Choose Course---</option>
                { coursedata.map((course)=>{
                return <option value={course.name} id={course.courseId}>{course.name}</option>
            })}
            </select> */}
            <Multiselect 
                options={coursedata}
                showCheckbox={true}
                displayValue="name"
                onSelect={
                    ((courses,selectedcourse)=>{
                        window.selectedCourses.add(selectedcourse);
                })} 
                onRemove={
                    (courses,unselectedcourse)=>{
                        window.selectedCourses.delete(unselectedcourse);
                    }
                }
                showArrow={true}
                placeholder="---Choose Course---"

                />
       </div>
    )
}

