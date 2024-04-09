package com.example.demo.Controller;

import com.example.demo.Entitys.ManHinh;
import com.example.demo.Repository.ManHinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("manHinh")
public class ManHinhController {

    @Autowired
    private ManHinhRepository manHinhRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<ManHinh> data = manHinhRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "ManHinh/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && manHinhRepository.findByTenIsLike(ten.trim()) == null) {
            ManHinh manHinh = new ManHinh();
            manHinh.setTen(ten.trim());
            manHinh.setTrangThai(1);
            manHinhRepository.save(manHinh);
        }


        return "redirect:/manHinh/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = manHinhRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            manHinhRepository.changeStatus(0, id);
        } else {
            manHinhRepository.changeStatus(1, id);
        }
        return "redirect:/manHinh/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<ManHinh> findSearch = manHinhRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "ManHinh/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", manHinhRepository.findAllByTrangThai(pageable,manHinhRepository.ACTIVE));
        } else {
            model.addAttribute("data", manHinhRepository.findAllByTrangThai(pageable,manHinhRepository.INACTIVE));
        }

        return "ManHinh/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<ManHinh> manHinh = manHinhRepository.findById(id);
        model.addAttribute("manHinh", manHinh.get());
        return "ManHinh/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<ManHinh> req = manHinhRepository.findById(id);
            ManHinh manHinh = req.get();
            manHinh.setTen(ten.trim());
            manHinhRepository.save(manHinh);
        }
        return "redirect:/manHinh/index";
    }
}
