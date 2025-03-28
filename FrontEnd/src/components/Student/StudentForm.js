import React, { useState } from 'react';
import { addStudent } from '../../services/api';

const StudentForm = () => {
  const [student, setStudent] = useState({
    studentCode: '',
    fullName: '',
    address: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setStudent({
      ...student,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addStudent(student)
      .then((response) => {
        alert('Student added successfully!');
        setStudent({
          studentCode: '',
          fullName: '',
          address: '',
        });
      })
      .catch((error) => {
        console.error(error);
        alert('Failed to add student');
      });
  };

  return (
    <div className="container mt-4">
      <h3>Add Student</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <input
            type="text"
            name="studentCode"
            value={student.studentCode}
            onChange={handleChange}
            className="form-control"
            placeholder="Student Code"
            required
          />
        </div>
        <div className="mb-3">
          <input
            type="text"
            name="fullName"
            value={student.fullName}
            onChange={handleChange}
            className="form-control"
            placeholder="Full Name"
            required
          />
        </div>
        <div className="mb-3">
          <input
            type="text"
            name="address"
            value={student.address}
            onChange={handleChange}
            className="form-control"
            placeholder="Address"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Add Student</button>
      </form>
    </div>
  );
};

export default StudentForm;
