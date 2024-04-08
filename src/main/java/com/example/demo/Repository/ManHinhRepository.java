package com.example.demo.Repository;

import com.example.demo.Entitys.ManHinh;
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
public interface ManHinhRepository extends JpaRepository<ManHinh, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<ManHinh> findAll(Pageable pageable);

    public Page<ManHinh> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<ManHinh> findByTrangThai(int trangThai);

    @Modifying
    @Query(value = "UPDATE ManHinh SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT manHinh FROM ManHinh manHinh WHERE manHinh.ten LIKE %:ten%")
    Page<ManHinh> search(@Param("ten") String ten, Pageable pageable);

}
