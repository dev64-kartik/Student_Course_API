import React from "react";

export default function StudentID()
{   
    const [ID,setID] = React.useState("");

    function handleChange(event)
    {
        setID(event.target.value);
    }

    return (
        <div className='row g-1 mb-3 form-element'>
             
             <label htmlFor='id' className="form-label">Id</label>
             <input type="number" className='form-control'
              id = 'id' name='id' placeholder="---Your Registered Id---"  value={ID} onChange={handleChange}
              required min={0} max={100}/>
            
            </div>

    )
}