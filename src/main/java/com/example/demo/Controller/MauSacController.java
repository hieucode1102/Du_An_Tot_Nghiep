package com.example.demo.Controller;

import com.example.demo.Entitys.MauSac;
import com.example.demo.Repository.MauSacRepository;
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
        MauSac mauSac = new MauSac();
        mauSac.setTenMauSac(ten);
        mauSac.setMaMauSac(ma);
        mauSac.setTrangThai(1);
        mauSacRepository.save(mauSac);

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

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id)
    {
        Optional<MauSac> mauSac = mauSacRepository.findById(id);
        model.addAttribute("mauSac", mauSac.get());
        return "MauSac/update";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") int id, @RequestParam("ten") String ten
            , @RequestParam("trangThai") int trangThai, @RequestParam("ma") String ma)
    {
        Optional<MauSac> req = mauSacRepository.findById(id);
        MauSac mauSac = req.get();
        mauSac.setTrangThai(trangThai);
        mauSac.setTenMauSac(ten);
        mauSac.setMaMauSac(ma);
        mauSacRepository.save(mauSac);
        return "redirect:/mauSac/index";
    }
}
