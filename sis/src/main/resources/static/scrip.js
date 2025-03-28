document.addEventListener('DOMContentLoaded', () => {
    loadStudents();

    document.getElementById('studentForm').addEventListener('submit', async (e) => {
        e.preventDefault();

        const student = {
            studentCode: document.getElementById('studentCode').value,
            fullName: document.getElementById('fullName').value,
            address: document.getElementById('address').value
        };

        try {
            const response = await fetch('/api/students', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(student)
            });

            if (response.ok) {
                document.getElementById('studentForm').reset();
                loadStudents();
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });
});

async function loadStudents() {
    try {
        const response = await fetch('/api/students');
        const students = await response.json();
        const tbody = document.querySelector('#studentTable tbody');
        tbody.innerHTML = '';

        students.forEach(student => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${student.studentCode}</td>
                <td>${student.fullName}</td>
                <td>${student.address || ''}</td>
            `;
            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error loading students:', error);
    }
}