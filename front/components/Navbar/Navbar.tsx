import React from 'react'
import { Link } from 'react-router-dom';
import "../../../node_modules/bootstrap/dist/css/bootstrap.min.css";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <a className="navbar-brand" href="#">Todolist</a>
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
            <ul className="navbar-nav">
            <li className="nav-item nav-link"><Link to="/">Home</Link></li>
            <li className="nav-item nav-link"><Link to="/addTask">Add Task</Link></li>
            <li className="nav-item nav-link"><Link to="/tasksDone">Tasks done</Link></li>
            <li className="nav-item nav-link"><Link to="/tasksForToday">Tasks for today</Link></li>
            <li className="nav-item nav-link"><Link to="/calendar">Calendar</Link></li>



            </ul>
        </div>
    </nav>
  )
}
