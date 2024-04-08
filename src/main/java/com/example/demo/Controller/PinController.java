package com.example.demo.Controller;

import com.example.demo.Entitys.Pin;
import com.example.demo.Repository.PinRepository;
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
@RequestMapping("pin")
public class PinController {

    @Autowired
    private PinRepository pinRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pin> data = pinRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "Pin/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        Pin pin = new Pin();
        pin.setTen(ten);
        pin.setTrangThai(1);
        pinRepository.save(pin);

        return "redirect:/pin/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = pinRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            pinRepository.changeStatus(0, id);
        } else {
            pinRepository.changeStatus(1, id);
        }
        return "redirect:/pin/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pin> findSearch = pinRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "Pin/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", pinRepository.findAllByTrangThai(pageable,pinRepository.ACTIVE));
        } else {
            model.addAttribute("data", pinRepository.findAllByTrangThai(pageable,pinRepository.INACTIVE));
        }

        return "Pin/index";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id)
    {
        Optional<Pin> pin = pinRepository.findById(id);
        model.addAttribute("pin", pin.get());
        return "Pin/update";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") int id, @RequestParam("ten") String ten, @RequestParam("trangThai") int trangThai)
    {
        Optional<Pin> req = pinRepository.findById(id);
        Pin pin = req.get();
        pin.setTrangThai(trangThai);
        pin.setTen(ten);
        pinRepository.save(pin);
        return "redirect:/pin/index";
    }
}
