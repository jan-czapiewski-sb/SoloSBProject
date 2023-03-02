import React, { useState, useEffect } from "react";
import FullCalendar from '@fullcalendar/react'
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import axios from "axios";

function TaskCalendar() {
  const [events, setEvents] = useState([]);
  const [showPopup, setShowPopup] = useState(false);
  const [selectedTask, setSelectedTask] = useState(null);
  // useEffect(() => {
  //   fetch('http://localhost:8080/api/task-undone')
  //     .then(response => response.json())
  //     .then(data => {
  //       setTasks(data.map(()=>{ return{...task, executionDay : new Date(task.executionDay)}}));
  //       console.log(tasks);

  //     })
  // }, []);
  
  useEffect(() => {
    axios.get("http://localhost:8080/api/task").then((response) => {
      const events = response.data.map((task) => {
        return {
          title: task.titleOfTask,
          start: new Date(task.executionDay),
          description: task.description,
          id: task.id,
          done: task.done,
        };
      });
      setEvents(events);
    });
  }, []);

  const handleEventClick = (eventInfo, calendarApi) => {
    setSelectedTask(eventInfo.event);
    setShowPopup(true);
  };

  const handlePopupClose = () => {
    setShowPopup(false);
  };

  return (
    <div className="container mx-auto my-8">
      <FullCalendar
        plugins={[dayGridPlugin, interactionPlugin]}
        initialView="dayGridMonth"
        events={events}
        displayEventTime={false}
        eventClick={handleEventClick}
      />
      {showPopup && selectedTask && (
        <Popup task={selectedTask} onClose={handlePopupClose} />
      )}
    </div>
  );
}

function Popup({ task, onClose }) {
  return (
    <div className="popup">
      <div className="popup-content">
        <h3>{task.title}</h3>
        <p>Execution day: {task.start.toLocaleDateString()}</p>
        <p>Execution day: {task.description}</p>

        <div className="text-center">khgigiiggi{task.description}</div>

        <button onClick={onClose}>Close</button>
      </div>
    </div>
  );
}

export default TaskCalendar;
