package com.example.demo.Controller;

import com.example.demo.Entitys.CameraTruoc;
import com.example.demo.Repository.CameraTruocRepository;
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
@RequestMapping("cameraTruoc")
public class CameraTruocController {

    @Autowired
    private CameraTruocRepository cameraTruocRepository;

    @GetMapping("/index")
    public String index(Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<CameraTruoc> data = cameraTruocRepository.findAll(pageable);
        model.addAttribute("main", "index");
        model.addAttribute("data", data);
        return "CameraTruoc/index";
    }

    @PostMapping("/store")
    public String store(@RequestParam("ten") String ten)
    {
        CameraTruoc cameraTruoc = new CameraTruoc();
        cameraTruoc.setTen(ten);
        cameraTruoc.setTrangThai(1);
        cameraTruocRepository.save(cameraTruoc);

        return "redirect:/cameraTruoc/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id)
    {
        int currentStatus = cameraTruocRepository.findById(id).get().getTrangThai();
        if (currentStatus == 1) {
            cameraTruocRepository.changeStatus(0, id);
        } else {
            cameraTruocRepository.changeStatus(1, id);
        }
        return "redirect:/cameraTruoc/index";
    }

    @GetMapping("search")
    public String search(@RequestParam("ten") String ten, Model model, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<CameraTruoc> findSearch = cameraTruocRepository.search(ten, pageable);
        model.addAttribute("data", findSearch);
        model.addAttribute("main", "search");
        return "CameraTruoc/index";
    }

    @GetMapping("filter")
    public String filter(Model model, @RequestParam("status") int status, @RequestParam("page") Optional<Integer> pageParam)
    {
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        if (status == 1) {
            model.addAttribute("data", cameraTruocRepository.findAllByTrangThai(pageable,CameraTruocRepository.ACTIVE));
        } else {
            model.addAttribute("data", cameraTruocRepository.findAllByTrangThai(pageable,CameraTruocRepository.INACTIVE));
        }

        return "CameraTruoc/index";
    }

    @GetMapping("edit")
    public String edit(Model model, @RequestParam("id") int id)
    {
        Optional<CameraTruoc> cameraTruoc = cameraTruocRepository.findById(id);
        model.addAttribute("camTruoc", cameraTruoc.get());
        return "CameraTruoc/update";
    }

    @PostMapping("update")
    public String update(@RequestParam("id") int id, @RequestParam("ten") String ten, @RequestParam("trangThai") int trangThai)
    {
        Optional<CameraTruoc> req = cameraTruocRepository.findById(id);
        CameraTruoc cameraTruoc = req.get();
        cameraTruoc.setTrangThai(trangThai);
        cameraTruoc.setTen(ten);
        cameraTruocRepository.save(cameraTruoc);
        return "redirect:/cameraTruoc/index";
    }
}
