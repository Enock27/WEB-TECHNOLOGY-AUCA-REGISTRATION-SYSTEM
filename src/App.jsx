import { useState, useEffect } from "react";
import "./App.css";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css"; // Ensure this is imported
import SignUpComponent from "./components/SignUpComponent";
import StudentListComponent from "./components/StudentListComponent";
import Topbar from "./components/Topbar";
import Footer from "./components/Footer";
import axios from "axios";

function App() {
  const [students, setStudents] = useState([]);
  const [editStudent, setEditStudent] = useState(null);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState("");

  const fetchStudents = async () => {
    try {
      setLoading(true);
      const response = await axios.get("http://localhost:8082/student/getAllStudents");
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
      toast.error("Failed to load students");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const filteredStudents = students.filter(student =>
    `${student.firstName} ${student.lastName} ${student.email} ${student.location}`
      .toLowerCase()
      .includes(searchTerm.toLowerCase())
  );

  return (
    <div className="app-container">
      <Topbar />
      <div className="app-content">
        <div className="app-layout">
          {/* Left Side - Student List */}
          <div className="list-section">
            <div className="search-container">
              <input
                type="text"
                placeholder="Search students..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="search-input"
              />
            </div>
            {loading ? (
              <div className="loading-spinner">Loading students...</div>
            ) : (
              <StudentListComponent 
                students={filteredStudents}
                fetchStudents={fetchStudents}
                setEditStudent={setEditStudent}
              />
            )}
          </div>

          {/* Right Side - Form */}
          <div className="form-section">
            <SignUpComponent 
              editStudent={editStudent}
              setEditStudent={setEditStudent}
              fetchStudents={fetchStudents}
            />
          </div>
        </div>
      </div>
      <Footer />
      <ToastContainer />
    </div>
  );
}

export default App;