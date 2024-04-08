package com.example.demo.Controller;

import com.example.demo.Entitys.CameraSau;
import com.example.demo.Repository.CameraSauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("cameraSau")
public class CameraSauController {

    @Autowired
    private CameraSauRepository cameraSauRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<CameraSau> data = cameraSauRepository.findAll(pageable);
        model.addAttribute("data", data);
        return "CameraSau/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() != 0 && (cameraSauRepository.findByTenIsLike(ten.trim()) == null)) {
            CameraSau cameraSau = new CameraSau();
            cameraSau.setTen(ten);
            cameraSau.setTrangThai(1);
            cameraSauRepository.save(cameraSau);
        }
        return "redirect:/cameraSau/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = cameraSauRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            cameraSauRepository.changeStatus(0, id);
        } else {
            cameraSauRepository.changeStatus(1, id);
        }
        return "redirect:/cameraSau/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<CameraSau> findSearch = cameraSauRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "CameraSau/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", cameraSauRepository.findAllByTrangThai(pageable,cameraSauRepository.ACTIVE));
        } else {
            model.addAttribute("data", cameraSauRepository.findAllByTrangThai(pageable,cameraSauRepository.INACTIVE));
        }

        return "CameraSau/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<CameraSau> cameraSau = cameraSauRepository.findById(id);
        model.addAttribute("cameraSau", cameraSau.get());
        return "CameraSau/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() != 0) {
            Optional<CameraSau> req = cameraSauRepository.findById(id);
            CameraSau cameraSau = req.get();
            cameraSau.setTen(ten);
            cameraSauRepository.save(cameraSau);
        }

        return "redirect:/cameraSau/index";
    }
}
