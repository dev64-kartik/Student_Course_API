import React from "react";

export default function StudentCollege(props)
{
    const [college,setCollege] = React.useState(props.college);
    
    return(
        <div className='row g-1 mb-3 form-element'>
       <label htmlFor='college' className='form-label'>College</label>
       <input type="text" id = "college" name="college" placeholder="---Your College---" value={college}
         onChange={(event) => (setCollege(event.target.value))} required className='form-control'/>
        </div>
    )
}