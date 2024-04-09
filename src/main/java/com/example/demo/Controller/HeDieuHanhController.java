package com.example.demo.Controller;

import com.example.demo.Entitys.HeDieuHanh;
import com.example.demo.Repository.HeDieuHanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("heDieuHanh")
public class HeDieuHanhController {

    @Autowired
    private HeDieuHanhRepository heDieuHanhRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<HeDieuHanh> data = heDieuHanhRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "HeDieuHanh/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && heDieuHanhRepository.findByTenIsLike(ten.trim()) == null) {
            HeDieuHanh heDieuHanh = new HeDieuHanh();
            heDieuHanh.setTen(ten.trim());
            heDieuHanh.setTrangThai(1);
            heDieuHanhRepository.save(heDieuHanh);
        }

        return "redirect:/heDieuHanh/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = heDieuHanhRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            heDieuHanhRepository.changeStatus(0, id);
        } else {
            heDieuHanhRepository.changeStatus(1, id);
        }
        return "redirect:/heDieuHanh/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<HeDieuHanh> findSearch = heDieuHanhRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "HeDieuHanh/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", heDieuHanhRepository.findAllByTrangThai(pageable,heDieuHanhRepository.ACTIVE));
        } else {
            model.addAttribute("data", heDieuHanhRepository.findAllByTrangThai(pageable,heDieuHanhRepository.INACTIVE));
        }

        return "HeDieuHanh/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<HeDieuHanh> heDieuHanh = heDieuHanhRepository.findById(id);
        model.addAttribute("heDieuHanh", heDieuHanh.get());
        return "HeDieuHanh/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<HeDieuHanh> req = heDieuHanhRepository.findById(id);
            HeDieuHanh heDieuHanh = req.get();
            heDieuHanh.setTen(ten.trim());
            heDieuHanhRepository.save(heDieuHanh);
        }

        return "redirect:/heDieuHanh/index";
    }
}
