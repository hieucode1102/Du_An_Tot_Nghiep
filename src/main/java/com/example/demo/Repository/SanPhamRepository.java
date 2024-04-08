package com.example.demo.Repository;

import com.example.demo.Entitys.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    Page<SanPham> findBy(Pageable pageable);
    public SanPham findByTenSanPham(String tenSP);

    public SanPham findByMaSanPham(String maSP);

}
