package com.example.demo.Repository;

import com.example.demo.Entitys.RAM;
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
public interface RAMRepository extends JpaRepository<RAM, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<RAM> findAll(Pageable pageable);

    public Page<RAM> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<RAM> findByTrangThai(int trangThai);

    @Modifying
    @Query(value = "UPDATE RAM SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT ram FROM RAM ram WHERE ram.ten LIKE %:ten%")
    Page<RAM> search(@Param("ten") String ten, Pageable pageable);

}
