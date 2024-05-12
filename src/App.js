import React, { useEffect, useState } from 'react';
import keycloak from './auth/keycloak-config';
import './App.css';

function App() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const token = await keycloak.updateToken();
        const response = await fetch('http://localhost:8080/api/patients', {
          headers: {
            Authorization: 'Bearer ' + token,
          },
        });
        const data = await response.json();
        setPatients(data);
      } catch (error) {
        console.error('Error fetching patients:', error);
      }
    };

    fetchPatients();
  }, []);

  return (
      <div className="App">
        <h1>Patients</h1>

        <ul>
          {patients.map((patient) => (
              <li key={patient.id}>{patient.name}</li>
          ))}
        </ul>
      </div>
  );
}

export default App;
