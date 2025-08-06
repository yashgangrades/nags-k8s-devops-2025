package com.nagp.advancedevops.assignment.service;

import com.nagp.advancedevops.assignment.dto.UserRecordDto;
import com.nagp.advancedevops.assignment.entity.UserRecord;
import com.nagp.advancedevops.assignment.exception.ResourceNotFoundException;
import com.nagp.advancedevops.assignment.exception.UserRecordAlreadyExistsException;
import com.nagp.advancedevops.assignment.mapper.UserRecordMapper;
import com.nagp.advancedevops.assignment.repository.UserRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

@Service
public class UserRecordServiceImpl implements UserRecordService {

    private final UserRecordRepository repository;

    public UserRecordServiceImpl(UserRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * @param userRecordDto - UserRecordDto Object
     */
    @Override
    public void createRecord(UserRecordDto userRecordDto) {
        UserRecord userRecord = UserRecordMapper.mapToUserRecord(userRecordDto, new UserRecord());
        Optional<UserRecord> optionalUserRecord = repository.findByMobileNumber(userRecordDto.getMobileNumber());
        if(optionalUserRecord.isPresent()) {
            throw new UserRecordAlreadyExistsException("User already registered with given mobileNumber "
                    +userRecordDto.getMobileNumber());
        }
        UserRecord savedUserRecord = repository.save(userRecord);
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return UserRecord Details based on a given mobileNumber
     */
    @Override
    public UserRecordDto fetchUserDetails(String mobileNumber) {
        UserRecord userRecord = repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("UserRecord", "mobileNumber", mobileNumber)
        );
        return UserRecordMapper.mapToUserRecordDto(userRecord, new UserRecordDto());
    }

    /**
     * @param userRecordDto - UserRecordDto Object
     * @return boolean indicating if the update of UserRecord details is successful or not
     */
    @Override
    public boolean updateUserDetails(UserRecordDto userRecordDto) {
        boolean isUpdated = false;

        if (userRecordDto != null && userRecordDto.getMobileNumber() != null) {
            UserRecord userRecord = repository.findByMobileNumber(userRecordDto.getMobileNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "User", "MobileNumber", userRecordDto.getMobileNumber()));

            UserRecordMapper.mapToUserRecord(userRecordDto,userRecord);
            repository.save(userRecord);
            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if to delete of UserRecord details is successful or not
     */
    @Override
    public boolean deleteUserDetails(String mobileNumber) {
        UserRecord userRecord = repository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("UserRecord", "mobileNumber", mobileNumber)
        );

        repository.deleteById(userRecord.getUserId());
        return true;
    }
}
