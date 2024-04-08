package com.example.demo.Repository;

import com.example.demo.Entitys.HeDieuHanh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface HeDieuHanhRepository extends JpaRepository<HeDieuHanh, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<HeDieuHanh> findAll(Pageable pageable);

    public Page<HeDieuHanh> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<HeDieuHanh> findByTrangThai(int trangThai);

    @Modifying
    @Query(value = "UPDATE HeDieuHanh SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT hdh FROM HeDieuHanh hdh WHERE hdh.ten LIKE %:ten%")
    Page<HeDieuHanh> search(@Param("ten") String ten, Pageable pageable);

}
