package com.example.demo.Repository;

import com.example.demo.Entitys.Sim;
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
public interface SimRepository extends JpaRepository<Sim, Integer> {

    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;

    public Page<Sim> findAll(Pageable pageable);

    public Page<Sim> findAllByTrangThai(Pageable pageable, int trangThai);

    public List<Sim> findByTrangThai(int trangThai);

    public Sim findByTenIsLike(String ten);

    @Modifying
    @Query(value = "UPDATE Sim SET trangThai = :trangThai WHERE id = :id")
    void changeStatus(@Param("trangThai") int trangThai, @Param("id") int id);

    @Query(value = "SELECT sim FROM Sim sim WHERE sim.ten LIKE %:ten%")
    Page<Sim> search(@Param("ten") String ten, Pageable pageable);

}
