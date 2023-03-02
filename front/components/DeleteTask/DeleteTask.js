import React, { useState, useEffect } from 'react'
import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";
import axios from 'axios';
import { useNavigate, Link, useParams } from 'react-router-dom';





export default function EditTask() {
let navigate = useNavigate()

const {id} = useParams()

const [task,setTask] = useState({
  titleOfTask: "",
   description: "", 
   priorityOfTask: "",
    executionDay: ""
})

const{titleOfTask, description, priorityOfTask, executionDay} = task

const onInputChange = (e)=>{

  setTask({...task,[e.target.name]: e.target.value})
  // setTask({
  //   ...task, 
  //   [`${nameOfValue}`]:val,
  // })
}


useEffect(()=>{
   
}, []);
const onSubmit =async (e) => {
    e.preventDefault();
    await axios.delete(`http://localhost:8080/api/task/${id}`, task)
    navigate("/");
};



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
                    type={"text"}
                    className="form-control"
                    placeholder="Enter priority"
                    name="priorityOfTask"
                    value={priorityOfTask}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="date" className="form-label"></label>
                    <input
                    type={"text"}
                    className="form-control"
                    placeholder="Enter a date"
                    name="executionDay"
                    value={executionDay}
                    onChange={(e)=>onInputChange(e)}
                    />
                </div>
      
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  )
}
