package org.example.servlet;

import org.example.dao.DiagnosisDAO;
import org.example.model.Diagnosis;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DiagnosisServlet", urlPatterns = "/diagnoses")
public class DiagnosisServlet extends HttpServlet {
    private final DiagnosisDAO diagnosisDAO = new DiagnosisDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Diagnosis> diagnoses = diagnosisDAO.getAllDiagnoses();
        String diagnosesJson = gson.toJson(diagnoses);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(diagnosesJson);
    }
}
