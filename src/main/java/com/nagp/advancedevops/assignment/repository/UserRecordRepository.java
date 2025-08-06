package com.nagp.advancedevops.assignment.repository;

import com.nagp.advancedevops.assignment.entity.UserRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {
    Optional<UserRecord> findByMobileNumber(String mobileNumber);
}

