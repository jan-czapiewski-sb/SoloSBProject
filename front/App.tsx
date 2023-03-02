
import './App.css';

import { ListOfTasks } from './components/ListOfTasks/ListOfTasks';

import "../node_modules/bootstrap/dist/css/bootstrap.min.css";

import {BrowserRouter, Route, Routes } from "react-router-dom"
import AddTask from './components/AddTask/AddTask';
import Navbar from './components/Navbar/Navbar';
import EditTask from './components/EditTask/EditTask';
import TasksDone from './components/TasksDone/TasksDone';
import TasksForToday from './components/TasksForToday/TasksForToday';
import { useState } from 'react';
// import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

import { Calendar, momentLocalizer } from 'react-big-calendar'
import 'react-big-calendar/lib/css/react-big-calendar.css'
import moment from 'moment'
import TaskCalendar from './components/Calendar/TaskCalendar';


function App() {


    return (


    
  
    <div className="App">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<ListOfTasks/>}/>
          <Route path="/addTask" element={<AddTask/>}/>
          <Route path="/editTask/:id" element={<EditTask/>}/>
          <Route path="/tasksDone" element={<TasksDone/>}/>
          <Route path="/tasksForToday" element={<TasksForToday/>}/>
          <Route path="/calendar" element={<TaskCalendar/>}/>
        </Routes>
      </BrowserRouter>
      


    </div>
  );
}

export default App;
