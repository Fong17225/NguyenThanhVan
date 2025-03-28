import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Your backend API URL
});

export const getStudents = () => {
  return api.get('/api/students');
};
export const getSubjects = () => {
  return api.get('/api/subjects');
};
export const addStudent = (student) => {
  return api.post('/api/students', student);
};

export const addScore = (score) => {
  return api.post('/api/scores', score);
};

export const getStudentGrades = () => {
  return api.get('/api/students/grades');
};
