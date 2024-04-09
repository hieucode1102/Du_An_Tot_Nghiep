package com.example.demo.Controller;

import com.example.demo.Entitys.*;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("sanPham")
public class SanPhamController {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 10;

    @Autowired
    private HangRepository hangRepository;
    @Autowired
    private CameraSauRepository cameraSauRepository;
    @Autowired
    private CameraTruocRepository cameraTruocRepository;
    @Autowired
    private ChipRepository chipRepository;
    @Autowired
    private HeDieuHanhRepository heDieuHanhRepository;
    @Autowired
    private ManHinhRepository manHinhRepository;
    @Autowired
    private PinRepository pinRepository;
    @Autowired
    private SimRepository simRepository;
    @Autowired
    private ROMRepository romRepository;
    @Autowired
    private RAMRepository ramRepository;
    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private ChiTietSanPhamRepository ctspRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    SanPham sanPham = new SanPham();
    List<ROM> dsROM = new ArrayList<>();
    List<RAM> dsRAM = new ArrayList<>();
    List<MauSac> dsMauSac = new ArrayList<>();
    List<SanPham> lstSP = new ArrayList<>();
    List<ChiTietSanPham> lstCTSP = new ArrayList<>();

    @GetMapping("/index")
    public String index(@RequestParam("page") Optional<Integer> pageParam, Model model) {

        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<SanPham> pageData = sanPhamRepository.findBy(pageable);
        model.addAttribute("data", pageData);

        for (SanPham sp : pageData.get().toList()) {
            System.out.println(sp.getMaSanPham());
        }

        return "SanPham/index";
    }

    @GetMapping("create-product")
    public String create(Model model) {
        model.addAttribute("lstHang", hangRepository.findByTrangThai(HangRepository.ACTIVE));
        model.addAttribute("lstCameraTruoc", cameraTruocRepository.findByTrangThai(CameraTruocRepository.ACTIVE));
        model.addAttribute("lstCameraSau", cameraSauRepository.findByTrangThai(CameraSauRepository.ACTIVE));
        model.addAttribute("lstChip", chipRepository.findByTrangThai(ChipRepository.ACTIVE));
        model.addAttribute("lstHeDieuHanh", heDieuHanhRepository.findByTrangThai(HeDieuHanhRepository.ACTIVE));
        model.addAttribute("lstManHinh", manHinhRepository.findByTrangThai(ManHinhRepository.ACTIVE));
        model.addAttribute("lstPin", pinRepository.findByTrangThai(PinRepository.ACTIVE));
        model.addAttribute("lstSim", simRepository.findByTrangThai(SimRepository.ACTIVE));
        model.addAttribute("lstRom", romRepository.findByTrangThai(ROMRepository.ACTIVE));
        model.addAttribute("lstRam", ramRepository.findByTrangThai(RAMRepository.ACTIVE));
        model.addAttribute("lstMauSac", mauSacRepository.findByTrangThai(MauSacRepository.ACTIVE));
        model.addAttribute("lstCTSP", lstCTSP);
        model.addAttribute("dsMauSac", dsMauSac);
        model.addAttribute("dsROM", dsROM);
        model.addAttribute("dsRAM", dsRAM);
        model.addAttribute("sanPham", sanPham);

        for (ChiTietSanPham ctsp : lstCTSP) {
            System.out.println(ctsp.getMaChiTietSanPham());
        }

        return "SanPham/create";
    }

    @PostMapping("store")
    public String store(@RequestParam("tenSanPham") String tenSanPham,
                        @RequestParam("idHeDieuHanh") int idHeDieuHanh,
                        @RequestParam("idManHinh") int idManHinh,
                        @RequestParam("idHang") int idHang,
                        @RequestParam("idCameraTruoc") int idCameraTruoc,
                        @RequestParam("idCameraSau") int idCameraSau,
                        @RequestParam("idSim") int idSim,
                        @RequestParam("idPin") int idPin,
                        @RequestParam("idChip") int idChip,
                        @RequestParam("idRAM") List<Integer> lstIdRam,
                        @RequestParam("idROM") List<Integer> lstIdRom,
                        @RequestParam("idMauSac") List<Integer> lstIdMauSac) {

        SanPham sanPhamRq = new SanPham();
        sanPhamRq.setTenSanPham(tenSanPham);
        HeDieuHanh hdh = new HeDieuHanh();
        hdh.setId(idHeDieuHanh);
        sanPhamRq.setIdHeDieuHanh(hdh);
        ManHinh mh = new ManHinh();
        mh.setId(idManHinh);
        sanPhamRq.setIdManHinh(mh);
        Hang hang = new Hang();
        hang.setId(idHang);
        sanPhamRq.setIdHang(hang);
        CameraTruoc cameraTruoc = new CameraTruoc();
        cameraTruoc.setId(idCameraTruoc);
        sanPhamRq.setIdCameraTruoc(cameraTruoc);
        CameraSau cameraSau = new CameraSau();
        cameraSau.setId(idCameraSau);
        sanPhamRq.setIdCameraSau(cameraSau);
        Sim sim = new Sim();
        sim.setId(idSim);
        sanPhamRq.setIdSim(sim);
        Pin pin = new Pin();
        pin.setId(idPin);
        sanPhamRq.setIdPin(pin);
        Chip chip = new Chip();
        chip.setId(idChip);
        sanPhamRq.setIdChip(chip);
        for (Integer id : lstIdRam) {
            RAM ram = ramRepository.findById(id).get();
            dsRAM.add(ram);
        }

        for (Integer id : lstIdRom) {
            ROM rom = romRepository.findById(id).get();
            dsROM.add(rom);
        }

        for (Integer id : lstIdMauSac) {
            MauSac mauSac = mauSacRepository.findById(id).get();
            dsMauSac.add(mauSac);
        }

        int count = 0;
        int lstSize = lstIdRam.size() * lstIdRom.size() * lstIdMauSac.size();

        for (Integer idRam : lstIdRam) {
            for (Integer idRom : lstIdRom) {
                for (Integer idMauSac : lstIdMauSac) {
                    ChiTietSanPham ctsp = new ChiTietSanPham();
                    SanPham sp = new SanPham();
                    sp.setTenSanPham(sanPhamRq.getTenSanPham());
                    RAM ram = new RAM();
                    ram.setId(idRam);
                    ROM rom = new ROM();
                    rom.setId(idRom);
                    MauSac mauSac = new MauSac();
                    mauSac.setId(idMauSac);
                    if (count < lstSize) {
                        ctsp.setMaChiTietSanPham(createMaCTSP(count));
                        count++;
                    }
                    ctsp.setIdSanPham(sp);
                    ctsp.setIdROM(rom);
                    ctsp.setIdRAM(ram);
                    ctsp.setIdMauSac(mauSac);
                    ctsp.setTrangThai(ChiTietSanPhamRepository.ACTIVE);
                    lstCTSP.add(ctsp);
                }
            }
        }

        int check = 0;
        String tenSpRq = sanPhamRq.getTenSanPham();
        List<SanPham> lstSPhams = sanPhamRepository.findAll();
        for (int i = 0; i < lstSPhams.size(); i++) {
            if (lstSPhams.get(i).getTenSanPham().equalsIgnoreCase(tenSpRq)) {
                sanPham = lstSPhams.get(i);
                check++;
            } else {
                break;
            }
        }

        if (check == 0) {
            sanPhamRq.setMaSanPham(generateMaSP());
            sanPhamRepository.save(sanPhamRq);
            sanPham = sanPhamRepository.findByTenSanPham(tenSpRq);
        }

        return "redirect:/sanPham/create-product";
    }

    @GetMapping("/delete/{idRAM}/{idROM}/{idMauSac}")
    public String delete(
            @PathVariable("idRAM") RAM idRAM,
            @PathVariable("idROM") ROM idROM,
            @PathVariable("idMauSac") MauSac idMauSac) {


        for (int i = 0; i < lstCTSP.size(); i++) {
            if (lstCTSP.get(i).getIdRAM().getId() == idRAM.getId().intValue() &&
                    lstCTSP.get(i).getIdROM().getId() == idROM.getId().intValue() &&
                    lstCTSP.get(i).getIdMauSac().getId() == idMauSac.getId().intValue()) {
                lstCTSP.remove(i);
            }
        }

        if (countExist(idRAM.getId().intValue(), idROM.getId().intValue()) == 0) {
            for (int i = 0; i < dsROM.size(); i++) {
                if (dsROM.get(i).getId().intValue() == idROM.getId().intValue()) {
                    dsROM.remove(i);
                }
            }
        }
        if (lstCTSP.size() == 0) {
            dsRAM.clear();
        }

        return "redirect:/sanPham/create-product";
    }

    @PostMapping("/updatePrice")
    public String updatePrice(@RequestParam("price") List<BigDecimal> lstPrice) {
        for (int i = 0; i < lstCTSP.size(); i++) {
            lstCTSP.get(i).setGiaBan(lstPrice.get(i));
            lstCTSP.get(i).setIdSanPham(sanPham);
        }
        dsMauSac.clear();
        dsRAM.clear();
        dsROM.clear();
        ctspRepository.saveAll(lstCTSP);

        return "redirect:/sanPham/index";
    }

    @GetMapping("/detail/{id}")
    public String getCTSP(@PathVariable("id") int idSP,
                          @RequestParam("page") Optional<Integer> pageParam,
                          Model model) {
        SanPham sp = new SanPham();
        sp.setId(idSP);
        int page = pageParam.orElse(0);
        Pageable pageable = PageRequest.of(page, 5);
        Page<ChiTietSanPham> lstCTSP = ctspRepository.findByIdSanPham(pageable, sp);
        model.addAttribute("lstCTSP", lstCTSP);
        model.addAttribute("idSP", sp.getId());
        return "SanPham/detail";
    }

    @GetMapping("/deleteVersion/{idRAM}/{idROM}")
    public String deleteVersion(@PathVariable("idROM") int idROM,
                                @PathVariable("idRAM") int idRAM) {
        for (int i = 0; i < lstCTSP.size(); i++) {
            if  (lstCTSP.get(i).getIdRAM().getId().intValue() == idRAM && lstCTSP.get(i).getIdROM().getId().intValue() == idROM) {
                lstCTSP.remove(i);
            }
        }

        if (countExist(idRAM, idROM) == 0) {
            for (int i = 0; i < dsROM.size(); i++) {
                if (dsROM.get(i).getId().intValue() == idROM) {
                    dsROM.remove(i);
                }
            }
        }

        if (lstCTSP.size() == 0) {
            dsROM.clear();
            dsMauSac.clear();
            dsRAM.clear();
        }

        return "redirect:/sanPham/create-product";
    }

    @GetMapping("all-product")
    public String getAllCTSP(Model model, @RequestParam("page") Optional<Integer> pageRequest) {
        model.addAttribute("lstHang", hangRepository.findByTrangThai(HangRepository.ACTIVE));
        model.addAttribute("lstCameraTruoc", cameraTruocRepository.findByTrangThai(CameraTruocRepository.ACTIVE));
        model.addAttribute("lstCameraSau", cameraSauRepository.findByTrangThai(CameraSauRepository.ACTIVE));
        model.addAttribute("lstChip", chipRepository.findByTrangThai(ChipRepository.ACTIVE));
        model.addAttribute("lstHeDieuHanh", heDieuHanhRepository.findByTrangThai(HeDieuHanhRepository.ACTIVE));
        model.addAttribute("lstManHinh", manHinhRepository.findByTrangThai(ManHinhRepository.ACTIVE));
        model.addAttribute("lstPin", pinRepository.findByTrangThai(PinRepository.ACTIVE));
        model.addAttribute("lstSim", simRepository.findByTrangThai(SimRepository.ACTIVE));
        model.addAttribute("lstRom", romRepository.findByTrangThai(ROMRepository.ACTIVE));
        model.addAttribute("lstRam", ramRepository.findByTrangThai(RAMRepository.ACTIVE));
        model.addAttribute("lstMauSac", mauSacRepository.findByTrangThai(MauSacRepository.ACTIVE));
        int page = pageRequest.orElse(0);
        Pageable pageable = PageRequest.of(page, 30);
        Page<ChiTietSanPham> data = ctspRepository.findAll(pageable);

        model.addAttribute("data", data);

        return "SanPham/allCTSP";
    }

    @GetMapping("/search/product")
    public String searchProduct(@RequestParam("param") String rqParam, Model model, @RequestParam("page") Optional<Integer> pageRequest) {
        int page = pageRequest.orElse(0);
        Pageable pageable = PageRequest.of(page, 30);
        String param = rqParam.trim();
        Page<ChiTietSanPham> data = ctspRepository.search(pageable, param);

        model.addAttribute("data", data);

        return "SanPham/allCTSP";
    }

    public int countExist(int idRAM, int idROM) {
        int count = 0;
        for (int i = 0; i < lstCTSP.size(); i++) {
            if (lstCTSP.get(i).getIdRAM().getId().intValue() == idRAM &&
                    lstCTSP.get(i).getIdROM().getId().intValue() == idROM) {
                count++;
            }
        }
        return count;
    }

    public String createMaCTSP(int count) {
        int index = ctspRepository.findAll().size() + count + 1;
        String sizeToString = String.valueOf(index);
        char character = '0';

        String repeatString = String.valueOf(character).repeat(9 - sizeToString.length());
        String maCTSP = "SP" + repeatString + index;
        return maCTSP;
    }

    public String generateMaSP() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }
}
