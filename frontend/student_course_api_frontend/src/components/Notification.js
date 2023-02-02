import React from "react";

export default function Notification(props)
{    
    let styleobj={}

     if(props.info)
        styleobj.backgroundColor="cornflowerblue";
     else if(props.warning)
        styleobj.backgroundColor="red";
    
    styleobj.color="ghostwhite";
    styleobj.fontSize="medium";

    return (
   <div class="toast-container position-fixed bottom-0 start-50 translate-middle-x p-3">
  <div class="toast align-items-center" id={props.role} style={styleobj}>
    <div class="d-flex">
    <div class="toast-body">
        {props.message}
    </div>
    <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
  </div>
    </div>
</div>
    )
}