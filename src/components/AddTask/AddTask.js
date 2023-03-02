import React, { useState } from 'react'
import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';





export default function AddTask() {
  let navigate = useNavigate()

  const [task, setTask] = useState({
    titleOfTask: "",
    description: "",
    priorityOfTask: "",
    executionDay: ""
  })

  const [selectedOption, setSelectedOption] = useState('');

  const { titleOfTask, description, priorityOfTask, executionDay } = task

  const onInputChange = (e) => {

    setSelectedOption(e.target.value);

    setTask({ ...task, [e.target.name]: e.target.value })
    // setTask({
    //   ...task, 
    //   [`${nameOfValue}`]:val,
    // })
  }
  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/api/task", task)
    navigate("/")
  };

  // const [selectedOption, setSelectedOption] = useState('');




  
  return (
    <div>
      <form onSubmit={(e) => onSubmit(e)}>
        <div className="mb-3">
          <label htmlFor="date" className="form-label"></label>
          <input
            type={"text"}
            className="form-control"
            placeholder="Enter a title"
            name="titleOfTask"
            value={titleOfTask}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        <div className="mb-3">
          <label htmlFor="date" className="form-label"></label>
          <input
            type={"text"}
            className="form-control"
            placeholder="Enter a description"
            name="description"
            value={description}
            onChange={(e) => onInputChange(e)}
          />
        </div>
        {/* <div className="mb-3">
                    <label htmlFor="date" className="form-label"></label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter priority"
                    name="priorityOfTask"
                    value={priorityOfTask}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div> */}
                 <div className="mb-3">
          <label htmlFor="date" className="form-label"></label>
          <input
            type={"date"}
            className="form-control"
            placeholder="Enter a date"
            name="executionDay"
            value={executionDay}
            onChange={(e) => onInputChange(e)}
          />
        </div>
                <div className="dropdown">
                <select
                value={selectedOption}
                 onChange={onInputChange} name="priorityOfTask"
                className="btn btn-secondary dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <option className="btn btn-secondary dropdown-toggle"value="">Change priority</option>
                <option value="LOW">Low</option>
                <option value="MEDIUM">Medium</option>
                <option value="HIGH">High</option>
                </select>
                </div>
   
 
  
       

        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
      
    </div>
  )
}
