import React, { useEffect, useState } from 'react';
import { getStudents } from './api';

const StudentList = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        const fetchStudents = async () => {
            const studentsData = await getStudents();
            setStudents(studentsData);
        };

        fetchStudents();
    }, []);

    return (
        <div>
            <h1>Student List</h1>
            <ul>
                {students.map((student) => (
                    <li key={student.studentId}>
                        {student.fullName} - {student.studentCode}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default StudentList;
