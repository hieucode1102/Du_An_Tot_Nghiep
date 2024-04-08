package com.example.demo.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ChiTietSanPham")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "MaChiTietSanPham", unique = true)
    private String maChiTietSanPham;

    @ManyToOne
    @JoinColumn(name = "IDMauSac")
    private MauSac idMauSac;

    @ManyToOne
    @JoinColumn(name = "IDROM")
    private ROM idROM;

    @ManyToOne
    @JoinColumn(name = "IDRAM")
    private RAM idRAM;

    @ManyToOne
    @JoinColumn(name = "IDSanPham")
    private SanPham idSanPham;

    @Column(name = "GiaBan")
    private BigDecimal giaBan;

    @Column(name = "Thue")
    private Integer thue;

    @Column(name = "YeuThich")
    private Integer yeuThich;

    @Column(name = "NgayTao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column(name = "NgaySua")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySua;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
