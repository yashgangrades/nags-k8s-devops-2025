package com.nagp.advancedevops.assignment.service;

import com.nagp.advancedevops.assignment.dto.UserRecordDto;
import com.nagp.advancedevops.assignment.entity.UserRecord;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Optional;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

public interface UserRecordService {
    /**
     *
     * @param userRecordDto - UserRecordDto Object
     */
    void createRecord(UserRecordDto userRecordDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return User Details based on a given mobileNumber
     */
    UserRecordDto fetchUserDetails(String mobileNumber);

    /**
     *
     * @param userRecordDto - UserRecordDto Object
     * @return boolean indicating if the update of UserRecord details is successful or not
     */
    boolean updateUserDetails(UserRecordDto userRecordDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if to delete of UsersRecord details is successful or not
     */
    boolean deleteUserDetails(@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber);
}
