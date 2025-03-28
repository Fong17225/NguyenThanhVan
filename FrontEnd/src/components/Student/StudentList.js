import React, { useEffect, useState } from 'react';
import { getStudents } from '../../services/api';

const StudentList = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    getStudents()
      .then((response) => setStudents(response.data))
      .catch((error) => console.error('Error fetching students', error));
  }, []);

  return (
    <div className="container mt-4">
      <h3>Student List</h3>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>Student Code</th>
            <th>Full Name</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {students.map((student) => (
            <tr key={student.studentId}>
              <td>{student.studentCode}</td>
              <td>{student.fullName}</td>
              <td>{student.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default StudentList;
