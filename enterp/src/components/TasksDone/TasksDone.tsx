import React from "react";
import { Link, Route, Routes, Router, useParams, useNavigate } from 'react-router-dom';

import { useContext, useEffect, useState } from "react";
import { ITask } from "../ListOfTasks/ListOfTasks";
import axios from "axios";

 
  



export function TasksDone() {
    let navigate = useNavigate()

        const {id} = useParams()    

        const [tasks, setTasks]= useState<ITask[]>([])

    useEffect(() => {
        fetch('hhttp://localhost:8080/api/task-done')
          .then(response => response.json())
          .then(data => {
            setTasks(data.map((task:ITask)=>{ return{...task, executionDay : new Date(task.executionDay)}}));
            console.log(tasks);
          })
      }, []);

      useEffect(() => {
        loadTasks();
    }, []);
    
    const loadTasks = async () => {
        const result = await axios.get("http://localhost:8080/api/task-done");
        setTasks(result.data);
    }

      const deleteTask=async (id: any)=>{
        await axios.delete(`http://localhost:8080/api/task/${id}`)
        fetch('http://localhost:8080/api/task')
        .then(response => response.json())
        .then(data => {
          setTasks(data.map((task:ITask)=>{ return{...task, executionDay : new Date(task.executionDay)}}));
          console.log(tasks);
        })
  }

  const markAsUnDone=async (id: any)=>{
    await axios.get(`http://localhost:8080/api/task-done/${id}`)
    fetch('http://localhost:8080/api/task-done')
    .then(response => response.json())
    .then(data => {
      setTasks(data.map((task:ITask)=>{ return{...task, executionDay : new Date(task.executionDay)}}));
      console.log(tasks);
    })
}
      
return(
    <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title of task</th>
                        <th scope="col">Description</th>
                        <th scope="col">Priority</th>
                        <th scope="col">Execution day</th>
                        <th scope="col">Done</th>
                    </tr>
                </thead>
      <tbody>
      {
          tasks.map((task, index) => (
              <tr>
                  <th scope="row" key={index}>{index + 1}</th>
                  <td className="text-center">{task.titleOfTask}</td>
                  <td className="text-center">{task.description}</td>
                  <td className="text-center">{task.priorityOfTask}</td>
                  <td className="text-center">{new Date(task.executionDay).toDateString()}</td>
                  <td className="text-center">{task.done}</td>
                  <td className="text-center">
                    <Link 
                   className="btn btn-primary mx-2" 
                      to={`/editTask/${task.id}`}>Edit
                      </Link>
                     <button className= "btn btn-danger mx-2"
                     onClick={()=>deleteTask(task.id)}>Delete</button>
                    <button className="btn btn-warning"
                    onClick={()=>markAsUnDone(task.id)}
                    >Undone</button>
                  </td>
              </tr>
          ))
      }
  </tbody>
  </table>
)             
}
export default TasksDone;

function loadUsers() {
    throw new Error("Function not implemented.");
}
