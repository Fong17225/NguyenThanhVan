import React, { useState, useEffect } from 'react';
import { addScore, getStudents, getSubjects } from '../../services/api';

const ScoreForm = () => {
  const [score, setScore] = useState({
    studentId: '',
    subjectId: '',
    score1: '',
    score2: '',
  });
  const [students, setStudents] = useState([]);
  const [subjects, setSubjects] = useState([]);

  useEffect(() => {
    getStudents()
      .then((response) => setStudents(response.data))
      .catch((error) => console.error(error));
    getSubjects()
      .then((response) => setSubjects(response.data))
      .catch((error) => console.error(error));
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setScore({
      ...score,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addScore(score)
      .then(() => {
        alert('Score added successfully!');
        setScore({ studentId: '', subjectId: '', score1: '', score2: '' });
      })
      .catch((error) => {
        console.error(error);
        alert('Failed to add score');
      });
  };

  return (
    <div className="container mt-4">
      <h3>Add Score</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <select
            name="studentId"
            value={score.studentId}
            onChange={handleChange}
            className="form-select"
            required
          >
            <option value="">Select Student</option>
            {students.map((student) => (
              <option key={student.studentId} value={student.studentId}>
                {student.fullName}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <select
            name="subjectId"
            value={score.subjectId}
            onChange={handleChange}
            className="form-select"
            required
          >
            <option value="">Select Subject</option>
            {subjects.map((subject) => (
              <option key={subject.subjectId} value={subject.subjectId}>
                {subject.subjectName}
              </option>
            ))}
          </select>
        </div>
        <div className="mb-3">
          <input
            type="number"
            name="score1"
            value={score.score1}
            onChange={handleChange}
            className="form-control"
            placeholder="Score 1"
            required
          />
        </div>
        <div className="mb-3">
          <input
            type="number"
            name="score2"
            value={score.score2}
            onChange={handleChange}
            className="form-control"
            placeholder="Score 2"
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Add Score</button>
      </form>
    </div>
  );
};

export default ScoreForm;
