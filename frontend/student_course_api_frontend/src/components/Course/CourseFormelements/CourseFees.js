import React from "react";

export default function CourseFees(props)
{   
    const [fees,setFees] = React.useState(props.fees);
    return (
        <div className='row g-1 mb-3 form-element'>
       <label htmlFor='fees' className='form-label'>Fees</label>
       <input type="number" id = "fees" name="fees" placeholder="---Course Fees---" value={fees}
         onChange={(event)=>(setFees(event.target.value))} min={0} max={20000} required className='form-control'/>
        </div>
    )
}