import React from "react";

export default function StudentName(props) {
  const [name, setname] = React.useState(props.name);

  return (
    <div className="row g-1 mb-3 form-element">
        <label htmlFor="name" className="form-label">
          Name
        </label>
        <input
          type="text"
          id="name"
          name="name"
          placeholder="---Your Name---"
          value={name}
          onChange={(event) => (setname(event.target.value))}
          required
          className="form-control"
        />
    </div>
  );
}
