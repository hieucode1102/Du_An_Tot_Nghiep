package com.example.demo.Repository;

import com.example.demo.Entitys.CameraTruoc;
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
public interface CameraTruocRepository extends JpaRepository<CameraTruoc, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<CameraTruoc> findAll(Pageable pageable);

    public Page<CameraTruoc> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<CameraTruoc> findByTrangThai(int trangThai);

    public CameraTruoc findByTenIsLike(String ten);

    @Modifying
    @Query(value = "UPDATE CameraTruoc SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT camTruoc FROM CameraTruoc camTruoc WHERE camTruoc.ten LIKE %:ten%")
    Page<CameraTruoc> search(@Param("ten") String ten, Pageable pageable);

}
