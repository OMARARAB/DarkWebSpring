package com.example.DarkWebM.Repository; // Defines the package for the repository
import com.example.DarkWebM.Model.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface QueryRepository extends JpaRepository<Query, Long> {

    @org.springframework.data.jpa.repository.Query("SELECT q FROM Query q WHERE q.user.id = :id")
    List<Query> findByUserId(@Param("id") Long userId); // Fetch queries by userId
}
