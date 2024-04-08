package com.example.demo.Repository;

import com.example.demo.Entitys.Hang;
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
public interface HangRepository extends JpaRepository<Hang, Integer> {
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<Hang> findAll(Pageable pageable);

    public Page<Hang> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<Hang> findByTrangThai(int trangThai);

    public Hang findByTenIsLike(String ten);

    @Modifying
    @Query(value = "UPDATE Hang SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT hang FROM Hang hang WHERE hang.ten LIKE %:ten%")
    Page<Hang> search(@Param("ten") String ten, Pageable pageable);

}
