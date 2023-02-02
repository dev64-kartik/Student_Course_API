import React from "react";

export default function CourseFees()
{   
    const [fees,setFees] = React.useState(0);
    return (
        <div className='row g-1 mb-3 form-element'>
       <label htmlFor='fees' className='form-label'>Fees</label>
       <input type="number" id = "fees" name="fees" placeholder="---Course Fees---" value={fees}
         onChange={(event)=>(setFees(event.target.value))} min={0} max={20000} required className='form-control'/>
        </div>
    )
}