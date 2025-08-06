package com.nagp.advancedevops.assignment.mapper;

import com.nagp.advancedevops.assignment.dto.UserRecordDetailsDto;
import com.nagp.advancedevops.assignment.dto.UserRecordDto;
import com.nagp.advancedevops.assignment.entity.UserRecord;

public class UserRecordMapper {

    public static UserRecordDto mapToUserRecordDto(UserRecord userRecord, UserRecordDto userRecordDto) {
        userRecordDto.setName(userRecord.getName());
        userRecordDto.setDescription(userRecord.getDescription());
        userRecordDto.setEmail(userRecord.getEmail());
        userRecordDto.setMobileNumber(userRecord.getMobileNumber());
        return userRecordDto;
    }

    public static UserRecordDetailsDto mapToUserRecordDetailsDto(UserRecord userRecord, UserRecordDetailsDto userRecordDetailsDto) {
        userRecordDetailsDto.setName(userRecord.getName());
        userRecordDetailsDto.setDescription(userRecord.getDescription());
        userRecordDetailsDto.setEmail(userRecord.getEmail());
        userRecordDetailsDto.setMobileNumber(userRecord.getMobileNumber());
        return userRecordDetailsDto;
    }

    public static UserRecord mapToUserRecord(UserRecordDto userRecordDto, UserRecord userRecord) {
        userRecord.setName(userRecordDto.getName());
        userRecord.setDescription(userRecordDto.getDescription());
        userRecord.setEmail(userRecordDto.getEmail());
        userRecord.setMobileNumber(userRecordDto.getMobileNumber());
        return userRecord;
    }

}
