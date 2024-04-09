package com.example.demo.Controller;

import com.example.demo.Entitys.Sim;
import com.example.demo.Repository.SimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("sim")
public class SimController {

    @Autowired
    private SimRepository simRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Sim> data = simRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "Sim/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && simRepository.findByTenIsLike(ten.trim()) == null) {
            Sim sim = new Sim();
            sim.setTen(ten.trim());
            sim.setTrangThai(1);
            simRepository.save(sim);
        }

        return "redirect:/sim/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = simRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            simRepository.changeStatus(0, id);
        } else {
            simRepository.changeStatus(1, id);
        }
        return "redirect:/sim/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Sim> findSearch = simRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "Sim/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", simRepository.findAllByTrangThai(pageable,simRepository.ACTIVE));
        } else {
            model.addAttribute("data", simRepository.findAllByTrangThai(pageable,simRepository.INACTIVE));
        }

        return "Sim/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<Sim> sim = simRepository.findById(id);
        model.addAttribute("sim", sim.get());
        return "Sim/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<Sim> req = simRepository.findById(id);
            Sim sim = req.get();
            sim.setTen(ten.trim());
            simRepository.save(sim);
        }
        return "redirect:/sim/index";
    }
}
