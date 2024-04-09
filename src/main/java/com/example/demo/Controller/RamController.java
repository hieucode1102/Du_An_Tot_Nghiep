package com.example.demo.Controller;

import com.example.demo.Entitys.RAM;
import com.example.demo.Repository.RAMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("ram")
public class RamController {

    @Autowired
    private RAMRepository ramRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<RAM> data = ramRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "RAM/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && ramRepository.findByTenIsLike(ten.trim()) == null) {
            RAM ram = new RAM();
            ram.setTen(ten.trim());
            ram.setTrangThai(1);
            ramRepository.save(ram);
        }

        return "redirect:/ram/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = ramRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            ramRepository.changeStatus(0, id);
        } else {
            ramRepository.changeStatus(1, id);
        }
        return "redirect:/ram/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<RAM> findSearch = ramRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "RAM/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", ramRepository.findAllByTrangThai(pageable,ramRepository.ACTIVE));
        } else {
            model.addAttribute("data", ramRepository.findAllByTrangThai(pageable,ramRepository.INACTIVE));
        }

        return "RAM/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<RAM> ram = ramRepository.findById(id);
        model.addAttribute("ram", ram.get());
        return "RAM/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<RAM> req = ramRepository.findById(id);
            RAM ram = req.get();
            ram.setTen(ten.trim());
            ramRepository.save(ram);
        }
        return "redirect:/ram/index";
    }
}
