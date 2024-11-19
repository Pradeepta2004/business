package com.example.business.salesRepository;

import com.example.business.salesModel.SalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<SalesModel, Long> {
    List<SalesModel> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<SalesModel> findByInventoryItem_Id(Long id);

}