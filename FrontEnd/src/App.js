import React from "react";
import StudentForm from "./components/Student/StudentForm";
import StudentList from "./components/Student/StudentList";
import ScoreForm from "./components/Score/ScoreForm";
import GradeList from "./components/Grade/GradeList";
import "bootstrap/dist/css/bootstrap.min.css";

const App = () => {
  return (
    <div>
      <h1>Student Management System</h1>
      <StudentForm />
      <ScoreForm />
      <StudentList />
      <GradeList />
    </div>
  );
};

export default App;
