package com.example.demo.Repository;

import com.example.demo.Entitys.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Integer> {


    public static final int ACTIVE = 1;

    public static final int INACTIVE = 0;

    public Page<ChiTietSanPham> findByIdSanPham(Pageable pageable, SanPham sp);

    public void deleteByIdSanPhamAndIdRAMAndIdROMAndIdMauSac(SanPham idSP, RAM idRAM, ROM idROM, MauSac idMauSac);

    @Query(value = "SELECT * FROM ChiTietSanPham ctsp WHERE (ctsp.IDMauSac IN (SELECT ID FROM MauSac ms WHERE ms.TenMauSac LIKE %:param%))\n" +
            "OR (ctsp.IDRAM IN (SELECT ID FROM RAM ram WHERE ram.Ten LIKE  %:param%))\n" +
            "OR (ctsp.IDROM IN (SELECT ID FROM ROM rom WHERE rom.TenRom LIKE  %:param%))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham sp WHERE sp.TenSanPham LIKE  %:param%))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDChip IN (SELECT ID FROM Chip WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDPin IN (SELECT ID FROM Pin WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDSim IN (SELECT ID FROM Sim WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDCameraSau IN (SELECT ID FROM CameraSau WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDCameraTruoc IN (SELECT ID FROM CameraTruoc WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDHang IN (SELECT ID FROM Hang WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDManHinh IN (SELECT ID FROM ManHinh WHERE Ten LIKE %:param%)))" +
            "OR (ctsp.IDSanPham IN (SELECT ID FROM SanPham WHERE IDHeDieuHanh IN (SELECT ID FROM HeDieuHanh WHERE Ten LIKE %:param%)))", nativeQuery = true)
    public Page<ChiTietSanPham> search(Pageable pageable, @Param("param") String param);

}
