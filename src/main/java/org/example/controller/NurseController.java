package org.example.controller;

import org.example.model.Nurse;
import org.example.service.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping
    public List<Nurse> getAllNurses() {
        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    public Nurse getNurseById(@PathVariable("id") long id) {
        return nurseService.getNurseById(id);
    }

    @PostMapping
    public Nurse addNurse(@RequestBody Nurse nurse) {
        return nurseService.addNurse(nurse);
    }

    @PutMapping("/{id}")
    public Nurse updateNurse(@PathVariable("id") long id, @RequestBody Nurse nurse) {
        return nurseService.updateNurse(id, nurse);
    }

    @DeleteMapping("/{id}")
    public void deleteNurse(@PathVariable("id") long id) {
        nurseService.deleteNurse(id);
    }
}
