package org.example.servlet;

import org.example.dao.PrescriptionDAO;
import org.example.model.Prescription;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PrescriptionServlet", urlPatterns = "/prescriptions")
public class PrescriptionServlet extends HttpServlet {
    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
        String prescriptionsJson = gson.toJson(prescriptions);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(prescriptionsJson);
    }
}
