package org.example.servlet;

import org.example.dao.NurseDAO;
import org.example.model.Nurse;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NurseServlet", urlPatterns = "/nurses")
public class NurseServlet extends HttpServlet {
    private final NurseDAO nurseDAO = new NurseDAO();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Nurse> nurses = nurseDAO.getAllNurses();
        String nursesJson = gson.toJson(nurses);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(nursesJson);
    }
}
