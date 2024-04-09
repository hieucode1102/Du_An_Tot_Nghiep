package com.example.demo.Controller;

import com.example.demo.Entitys.Chip;
import com.example.demo.Repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("chip")
public class ChipController {

    @Autowired
    private ChipRepository chipRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Chip> data = chipRepository.findAll(pageable);
        model.addAttribute("data", data);
        return "Chip/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && chipRepository.findByTenIsLike(ten.trim()) != null) {
            Chip chip = new Chip();
            chip.setTen(ten.trim());
            chip.setTrangThai(1);
            chipRepository.save(chip);
        }
        return "redirect:/chip/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = chipRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            chipRepository.changeStatus(0, id);
        } else {
            chipRepository.changeStatus(1, id);
        }
        return "redirect:/chip/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Chip> findSearch = chipRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "Chip/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", chipRepository.findAllByTrangThai(pageable,chipRepository.ACTIVE));
        } else {
            model.addAttribute("data", chipRepository.findAllByTrangThai(pageable,chipRepository.INACTIVE));
        }

        return "Chip/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<Chip> chip = chipRepository.findById(id);
        model.addAttribute("chip", chip.get());
        return "Chip/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<Chip> req = chipRepository.findById(id);
            Chip chip = req.get();
            chip.setTen(ten.trim());
            chipRepository.save(chip);
        }

        return "redirect:/chip/index";
    }
}
