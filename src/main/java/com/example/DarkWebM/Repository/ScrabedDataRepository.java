package com.example.DarkWebM.Repository;

import com.example.DarkWebM.Model.ScrabedData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository interface for ScrabedData
public interface ScrabedDataRepository extends JpaRepository<ScrabedData, Long> {

    // Custom query to find ScrabedData by queryId
    List<ScrabedData> findByQuery_QueryId(Long queryId); // Fetch ScrabedData by Query's queryId
}
