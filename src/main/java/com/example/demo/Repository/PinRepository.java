package com.example.demo.Repository;

import com.example.demo.Entitys.Pin;
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
public interface PinRepository extends JpaRepository<Pin, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<Pin> findAll(Pageable pageable);

    public Page<Pin> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<Pin> findByTrangThai(int trangThai);

    @Modifying
    @Query(value = "UPDATE Pin SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT pin FROM Pin pin WHERE pin.ten LIKE %:ten%")
    Page<Pin> search(@Param("ten") String ten, Pageable pageable);

}
