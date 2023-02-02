import React from 'react'

export default function LayoutMain(props)
{   
    return(
     <div className='layout'>
       {props.children}
    </div>
    )
}