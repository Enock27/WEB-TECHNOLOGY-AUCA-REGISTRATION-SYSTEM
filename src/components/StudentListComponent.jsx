import React from "react";
import { toast } from "react-toastify";
import axios from "axios";
import Button from "./Button";

function StudentListComponent({ students = [], fetchStudents, setEditStudent }) {
  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this student?")) {
      try {
        await axios.delete(`http://localhost:8082/student/deleteStudent/${id}`);
        toast.success("Student deleted successfully");
        fetchStudents();
      } catch (error) {
        toast.error("Failed to delete student: " + error.message);
      }
    }
  };

  return (
    <div className="student-list-container">
      <h2>Registered Students ({students.length})</h2>
      <div className="student-list-wrapper">
        {students.length === 0 ? (
          <p className="no-students">No matching students found.</p>
        ) : (
          <ul className="student-list">
            {students.map(student => (
              <li key={student.id} className="student-item">
                <div className="student-info">
                  <h3>{student.firstName} {student.lastName}</h3>
                  <p><strong>Email:</strong> {student.email}</p>
                  <p><strong>Location:</strong> {student.location}</p>
                </div>
                <div className="student-actions">
                  <Button 
                    label="Edit" 
                    onClick={() => setEditStudent(student)}
                    className="edit-btn"
                  />
                  <Button 
                    label="Delete" 
                    onClick={() => handleDelete(student.id)}
                    className="delete-btn"
                  />
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default StudentListComponent;