package org.example.servlet;

import org.example.dao.DoctorDAO;
import org.example.model.Doctor;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DoctorServlet", urlPatterns = "/doctors")
public class DoctorServlet extends HttpServlet {
    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        String doctorsJson = gson.toJson(doctors);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(doctorsJson);
    }
}
