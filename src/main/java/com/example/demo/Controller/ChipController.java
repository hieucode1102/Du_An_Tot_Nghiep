package com.example.demo.Controller;

import com.example.demo.Entitys.Chip;
import com.example.demo.Repository.ChipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "Chip/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        Chip chip = new Chip();
        chip.setTen(ten);
        chip.setTrangThai(1);
        chipRepository.save(chip);

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

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id)
    {
        Optional<Chip> chip = chipRepository.findById(id);
        model.addAttribute("chip", chip.get());
        return "Chip/update";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") int id, @RequestParam("ten") String ten, @RequestParam("trangThai") int trangThai)
    {
        Optional<Chip> req = chipRepository.findById(id);
        Chip chip = req.get();
        chip.setTrangThai(trangThai);
        chip.setTen(ten);
        chipRepository.save(chip);
        return "redirect:/chip/index";
    }
}
