package com.example.demo.Controller;

import com.example.demo.Entitys.Hang;
import com.example.demo.Repository.HangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("hang")
public class HangController {

    @Autowired
    private HangRepository hangRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Hang> data = hangRepository.findAll(pageable);
        model.addAttribute("data", data);
        return "Hang/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() != 0 && (hangRepository.findByTenIsLike(ten.trim()) == null)) {
            Hang hang = new Hang();
            hang.setTen(ten);
            hang.setTrangThai(1);
            hangRepository.save(hang);
        }

        return "redirect:/hang/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = hangRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            hangRepository.changeStatus(0, id);
        } else {
            hangRepository.changeStatus(1, id);
        }
        return "redirect:/hang/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Hang> findSearch = hangRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "Hang/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", hangRepository.findAllByTrangThai(pageable,hangRepository.ACTIVE));
        } else {
            model.addAttribute("data", hangRepository.findAllByTrangThai(pageable,hangRepository.INACTIVE));
        }

        return "Hang/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int idRq)
    {
        Optional<Hang> hang = hangRepository.findById(idRq);
        model.addAttribute("hang", hang.get());
        return "Hang/update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() != 0) {
            Optional<Hang> req = hangRepository.findById(id);
            Hang hang = req.get();
            hang.setTen(ten);
            hangRepository.save(hang);
        }

        return "redirect:/hang/index";
    }
}
