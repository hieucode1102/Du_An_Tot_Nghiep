package com.example.demo.Controller;

import com.example.demo.Entitys.RAM;
import com.example.demo.Entitys.ROM;
import com.example.demo.Repository.RAMRepository;
import com.example.demo.Repository.ROMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("rom")
public class RomController {

    @Autowired
    private ROMRepository romRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<ROM> data = romRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "ROM/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0 && romRepository.findByTenRomIsLike(ten.trim()) == null) {
            ROM rom = new ROM();
            rom.setTenRom(ten.trim());
            rom.setTrangThai(1);
            romRepository.save(rom);
        }

        return "redirect:/rom/index";
    }

//    @GetMapping("/delete")
//    public String delete(@RequestParam("id") int id)
//    {
//        int currentStatus = ramRepository.findById(id).get().getTrangThai();
//        if (currentStatus == 1) {
//            ramRepository.changeStatus(0, id);
//        } else {
//            ramRepository.changeStatus(1, id);
//        }
//        return "redirect:/ram/index";
//    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<ROM> findSearch = romRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "ROM/index";
    }

//    @GetMapping("filter")
//    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
//    {
//        int page = pageParam.orElse(0);
//        Pageable pageable = PageRequest.of(page, 5);
//        if (status == 1) {
//            model.addAttribute("data", ramRepository.findAllByTrangThai(pageable,ramRepository.ACTIVE));
//        } else {
//            model.addAttribute("data", ramRepository.findAllByTrangThai(pageable,ramRepository.INACTIVE));
//        }
//
//        return "RAM/index";
//    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<ROM> rom = romRepository.findById(id);
        model.addAttribute("rom", rom.get());
        return "ROM/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten)
    {
        if (ten.trim().length() > 0) {
            Optional<ROM> req = romRepository.findById(id);
            ROM rom = req.get();
            rom.setTenRom(ten.trim());
            romRepository.save(rom);
        }
        return "redirect:/rom/index";
    }
}
