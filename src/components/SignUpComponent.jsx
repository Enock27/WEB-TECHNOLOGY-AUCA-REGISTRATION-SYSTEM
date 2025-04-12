import React, { useState, useEffect } from "react";
import axios from "axios";
import { toast } from "react-toastify";
import Button from "./Button";

function SignUpComponent({ editStudent, setEditStudent, fetchStudents }) {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    location: "",
    email: ""
  });

  useEffect(() => {
    if (editStudent) {
      setFormData({
        firstName: editStudent.firstName,
        lastName: editStudent.lastName,
        location: editStudent.location,
        email: editStudent.email
      });
    } else {
      setFormData({
        firstName: "",
        lastName: "",
        location: "",
        email: ""
      });
    }
  }, [editStudent]);

  const handleSubmit = async () => {
    try {
      if (editStudent) {
        // Update existing student
        await axios.put(
          `http://localhost:8082/student/updateStudent/${editStudent.id}`,
          formData
        );
        toast.success("Student updated successfully");
      } else {
        // Create new student
        await axios.post(
          "http://localhost:8082/student/saveStudent",
          formData
        );
        toast.success("Student created successfully");
      }
      setEditStudent(null);
      fetchStudents(); // Refresh the list
    } catch (error) {
      toast.error(error.response?.data || "Operation failed");
      console.error("Operation error:", error);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  return (
    <div className="form-card">
      <h1>{editStudent ? "Edit Student" : "Add New Student"}</h1>
      
      <div className="form-group">
        <label>First Name</label>
        <input
          type="text"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label>Last Name</label>
        <input
          type="text"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label>Email</label>
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-group">
        <label>Location</label>
        <input
          type="text"
          name="location"
          value={formData.location}
          onChange={handleChange}
          required
        />
      </div>

      <div className="form-actions">
        <button 
          className="holographic-btn"
          onClick={handleSubmit}
        >
          {editStudent ? "Update" : "Submit"}
        </button>
        {editStudent && (
          <button 
            className="holographic-btn cancel-btn"
            onClick={() => setEditStudent(null)}
          >
            Cancel
          </button>
        )}
      </div>
    </div>
  );
}

export default SignUpComponent;