package com.example.demo.Repository;

import com.example.demo.Entitys.Chip;
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
public interface ChipRepository extends JpaRepository<Chip, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<Chip> findAll(Pageable pageable);

    public Page<Chip> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<Chip> findByTrangThai(int trangThai);

    public Chip findByTenIsLike(String ten);

    @Modifying
    @Query(value = "UPDATE Chip SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT chip FROM Chip chip WHERE chip.ten LIKE %:ten%")
    Page<Chip> search(@Param("ten") String ten, Pageable pageable);

}
