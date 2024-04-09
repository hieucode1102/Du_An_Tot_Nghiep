package com.example.demo.Repository;

import com.example.demo.Entitys.ROM;
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
public interface ROMRepository extends JpaRepository<ROM, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<ROM> findAll(Pageable pageable);

    public Page<ROM> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<ROM> findByTrangThai(int trangThai);

    public ROM findByTenRomIsLike(String ten);
    @Modifying
    @Query(value = "UPDATE ROM SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT rom FROM ROM rom WHERE rom.tenRom LIKE %:ten%")
    Page<ROM> search(@Param("ten") String ten, Pageable pageable);

}
