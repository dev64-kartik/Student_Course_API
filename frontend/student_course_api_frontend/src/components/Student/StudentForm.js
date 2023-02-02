import React from 'react';

 export default function StudentForm(props)
{   
    const [formData,setformData]  = React.useState({
        id:0,
        name:"",
        college:""
    });

    //console.log("component rendered");
    function handleChange(event){

        setformData((prevformData) => {
            return {
                ...prevformData,
                [event.target.name]:event.target.value
            }
        })
    }

    return (
        <form>

            <div className='row g-1 mb-2'>
            <label htmlFor='name' className='form-label' >Name</label>
            <input type="text" id = "name" name="name" placeholder="---Your Name---" value={formData.name} onChange={handleChange} 
            required className='form-control'/>
            </div>
            
            <div className='row g-1 mb-2'>
           <label htmlFor='college' className='form-label'>College</label>
           <input type="text" id = "college" name="college" placeholder="---Your College---" value={formData.college}
             onChange={handleChange} required className='form-control'/>
            </div>
           
           <div className='ro g-3 mb-2'>
                <label htmlFor='courseselector' className='form-label'>
                    Select Course to Enroll
                </label>
                <select id="courseselector" className='form-select' required>
                    <option value=""> ---Choose Course---</option>
                    <option value="a">A</option>
                    <option value="b">B</option>
                    <option value="c">C</option>
                </select>
           </div>
        </form>
        
    )
}