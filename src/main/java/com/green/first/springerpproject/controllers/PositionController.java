package com.green.first.springerpproject.controllers;

import com.green.first.springerpproject.models.Position;
import com.green.first.springerpproject.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PositionController {

    PositionRepository positionRepository;

    @Autowired
    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping("/positions")
    public String getPositionPage(Model model) {
        List<Position> positionList = positionRepository.findAllPosition();
        model.addAttribute("positionList", positionList);
        return "/positions/position_page";
    }

    @GetMapping("/position/edit/{position_id}")
    public String getPositionEditPage(@PathVariable String position_id, Model model) {
        Position position = positionRepository.findPositionById(Long.parseLong(position_id));
        model.addAttribute("selectedPosition", position);
        return "/positions/position_edit";
    }

    @PostMapping("/position/edit/{position_id}")
    public String editPosition(@PathVariable String position_id, @RequestParam(name = "new_position_id") String new_position_id, @RequestParam(name = "new_position_name") String new_position_name) {
        positionRepository.updatePositionById(new_position_name, Long.parseLong(new_position_id));
        return "redirect:/positions";
    }

    @GetMapping("/position/add")
    public String addPosition() {
        return "/positions/position_add";
    }

    @PostMapping("/position/add")
    public String addPosition(@RequestParam String new_position_name) {
        positionRepository.insertPosition(new_position_name);
        return "redirect:/positions";
    }

    @PostMapping("/position/delete/{position_id}")
    public String deletePosition(@PathVariable String position_id) {
        positionRepository.deletePositionById(Long.parseLong(position_id));
        return "redirect:/positions";
    }
}
