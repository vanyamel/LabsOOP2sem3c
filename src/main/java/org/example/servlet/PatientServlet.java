package org.example.servlet;

import org.example.dao.PatientDAO;
import org.example.model.Patient;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PatientServlet", urlPatterns = "/patients")
public class PatientServlet extends HttpServlet {
    private final PatientDAO patientDAO = new PatientDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Patient> patients = patientDAO.getAllPatients();
        String patientsJson = gson.toJson(patients);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(patientsJson);
    }
}
