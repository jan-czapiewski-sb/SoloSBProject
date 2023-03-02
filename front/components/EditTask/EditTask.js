import React, { useState, useEffect } from 'react'
import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import { useNavigate, Link, useParams } from 'react-router-dom';





export default function EditTask() {
let navigate = useNavigate()

const {id} = useParams()
const [selectedOption, setSelectedOption] = useState('');

const [task,setTask] = useState({
  titleOfTask: "",
   description: "", 
   priorityOfTask: "",
    executionDay: ""
})

const{titleOfTask, description, priorityOfTask, executionDay} = task

const onInputChange = (e)=>{
  setSelectedOption(e.target.value);
  setTask({...task,[e.target.name]: e.target.value})
  // setTask({
  //   ...task, 
  //   [`${nameOfValue}`]:val,
  // })
}


useEffect(()=>{
    loadUser();
}, []);
const onSubmit =async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/task/${id}`, task)
    navigate("/");
};
const loadUser = async () =>{
    const result=await axios.get(`http://localhost:8080/api/task/${id}`)
    setTask(result.data)
}


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
                    onChange={(e)=>onInputChange(e)}
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
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
               
                <div className="mb-3">
                    <label htmlFor="date" className="form-label"></label>
                    <input
                    type={"date"}
                    className="form-control"
                    placeholder="Enter a date"
                    name="executionDay"
                    value={executionDay}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>

                <div className="dropdown">
      
      <select

      value={selectedOption}
       onChange={onInputChange} name="priorityOfTask"
      className="btn btn-secondary dropdown-toggle" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
      <option className="btn btn-secondary dropdown-toggle"value="">Change priority</option>
      <option value="Low">Low</option>
      <option value="MEDIUM">MEDIUM</option>
      <option value="HIGH">HIGH</option>
      </select>
      </div>
      
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  )
}
