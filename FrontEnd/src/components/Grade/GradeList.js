import React, { useEffect, useState } from 'react';
import { getStudentGrades } from '../../services/api';

const GradeList = () => {
  const [grades, setGrades] = useState([]);

  useEffect(() => {
    getStudentGrades()
      .then((response) => setGrades(response.data))
      .catch((error) => console.error('Error fetching grades', error));
  }, []);

  return (
    <div className="container mt-4">
      <h3 className="mb-4">Student Grades</h3>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Student Code</th>
            <th>Full Name</th>
            <th>Subject</th>
            <th>Grade</th>
          </tr>
        </thead>
        <tbody>
          {grades.map((grade, index) => (
            <tr key={index}>
              <td>{grade.studentCode}</td>
              <td>{grade.fullName}</td>
              <td>{grade.subjectName}</td>
              <td>{grade.grade}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default GradeList;
