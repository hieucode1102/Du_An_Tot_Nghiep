package com.example.demo.Controller;

import com.example.demo.Entitys.MauSac;
import com.example.demo.Repository.MauSacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("mauSac")
public class MauSacController {

    @Autowired
    private MauSacRepository mauSacRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<MauSac> data = mauSacRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "MauSac/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten, @RequestParam("ma") String ma)
    {
        if (ten.trim().length() > 0 && ma.trim().length() > 0 && mauSacRepository.findByTenMauSacIsLike(ten.trim()) == null && mauSacRepository.findByMaMauSacIsLike(ma.trim()) == null) {
            MauSac mauSac = new MauSac();
            mauSac.setTenMauSac(ten);
            mauSac.setMaMauSac(ma);
            mauSac.setTrangThai(1);
            mauSacRepository.save(mauSac);
        }

        return "redirect:/mauSac/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = mauSacRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            mauSacRepository.changeStatus(0, id);
        } else {
            mauSacRepository.changeStatus(1, id);
        }
        return "redirect:/mauSac/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<MauSac> findSearch = mauSacRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "MauSac/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", mauSacRepository.findAllByTrangThai(pageable,mauSacRepository.ACTIVE));
        } else {
            model.addAttribute("data", mauSacRepository.findAllByTrangThai(pageable,mauSacRepository.INACTIVE));
        }

        return "MauSac/index";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") int id)
    {
        Optional<MauSac> mauSac = mauSacRepository.findById(id);
        model.addAttribute("mauSac", mauSac.get());
        return "MauSac/update";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") int id, @RequestParam("ten") String ten
            , @RequestParam("ma") String ma)
    {
        if (ten.trim().length() > 0 && ma.trim().length() > 0) {
            Optional<MauSac> req = mauSacRepository.findById(id);
            MauSac mauSac = req.get();
            mauSac.setTenMauSac(ten.trim());
            mauSac.setMaMauSac(ma.trim());
            mauSacRepository.save(mauSac);

        }
        return "redirect:/mauSac/index";
    }
}
