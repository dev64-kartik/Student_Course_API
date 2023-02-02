import React from 'react';


export default function CourseID()
{   
    const [id,setID] = React.useState("");
    return (
        <div className='row g-1 mb-3 form-element'>
        <label htmlFor='id' className="form-label">Id</label>
        <input type="number" className='form-control'
         id = 'id' name='id' placeholder="---Course Id---"  value={id} onChange={(event)=>(setID(event.target.value))}
         required min={0} max={100}/>
       </div>

    )
}