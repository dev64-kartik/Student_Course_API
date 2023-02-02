import React from "react";

export default function CourseEducator()
{   
    const [educator,setEducator] = React.useState("");
    return (
        <div className='row g-1 mb-3 form-element'>
       <label htmlFor='educator' className='form-label'>Educator</label>
       <input type="text" id = "educator" name="educator" placeholder="---Educator Name---" value={educator}
         onChange={(event)=>(setEducator(event.target.value))} required className='form-control'/>
        </div>
    )
}