package com.example.DarkWebM.Repository;

import com.example.DarkWebM.Model.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indicates that this interface is a Spring Data repository

public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
}
