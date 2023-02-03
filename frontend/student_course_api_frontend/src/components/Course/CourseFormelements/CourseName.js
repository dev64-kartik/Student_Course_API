import React from "react";


export default function CourseName(props)
{   
    const [name,setName] = React.useState(props.name);
    return (
        <div className='row g-1 mb-3 form-element'>
            <label htmlFor='title' className='form-label' >Name</label>
            <input type="text" id = "title" name="title" placeholder="---Course Name---" value={name} onChange={(event)=>(setName(event.target.value))} 
            required className='form-control'/>
            </div>
    )
}