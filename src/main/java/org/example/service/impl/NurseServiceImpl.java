package org.example.service.impl;

import org.example.dao.NurseDAO;
import org.example.model.Nurse;
import org.example.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseDAO nurseDAO;

    @Override
    public List<Nurse> getAllNurses() {
        return nurseDAO.getAllNurses();
    }

    @Override
    public Nurse getNurseById(long id) {
        return nurseDAO.getNurseById(id);
    }

    @Override
    public Nurse addNurse(Nurse nurse) {
        return nurseDAO.addNurse(nurse);
    }

    @Override
    public Nurse updateNurse(long id, Nurse nurse) {
        return nurseDAO.updateNurse(id, nurse);
    }

    @Override
    public void deleteNurse(long id) {
        nurseDAO.deleteNurse(id);
    }
}
