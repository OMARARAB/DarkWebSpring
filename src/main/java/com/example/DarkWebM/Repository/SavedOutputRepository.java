package com.example.DarkWebM.Repository;

import com.example.DarkWebM.Model.SavedOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavedOutputRepository extends JpaRepository<SavedOutput, Long> {
    List<SavedOutput> findByUserId(Long userId);
}
