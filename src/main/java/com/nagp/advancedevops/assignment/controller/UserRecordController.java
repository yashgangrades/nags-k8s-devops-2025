package com.nagp.advancedevops.assignment.controller;

import com.nagp.advancedevops.assignment.constants.UserRecordConstants;
import com.nagp.advancedevops.assignment.dto.ErrorResponseDto;
import com.nagp.advancedevops.assignment.dto.ResponseDto;
import com.nagp.advancedevops.assignment.dto.UserRecordDto;
import com.nagp.advancedevops.assignment.service.UserRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

@Tag(
        name = "UserRecord CRUD APIs",
        description = "REST APIs to CREATE, READ, UPDATE, and DELETE user records for the NAGP 2025 assignment"
)

@RestController
@RequestMapping(path="/records", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserRecordController {

    private final UserRecordService service;

    public UserRecordController(UserRecordService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create UserRecord REST API",
            description = "REST API to create a new user record in the NAGP 2025 assignment database"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User record created successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> create(@Valid @RequestBody UserRecordDto userRecordDto) {
        service.createRecord(userRecordDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(UserRecordConstants.STATUS_201, UserRecordConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch User Details REST API",
            description = "REST API to fetch User details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<UserRecordDto> fetchUserDetails(@RequestParam
                                                           @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                           String mobileNumber) {
        UserRecordDto userRecordDto = service.fetchUserDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userRecordDto);
    }

    @Operation(
            summary = "Update User Details REST API",
            description = "REST API to update User details based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserDetails(@Valid @RequestBody UserRecordDto userRecordDto) {
        boolean isUpdated = service.updateUserDetails(userRecordDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserRecordConstants.STATUS_200, UserRecordConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UserRecordConstants.STATUS_417, UserRecordConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete User Details REST API",
            description = "REST API to delete User details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUserDetails(@RequestParam
                                                         @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                         String mobileNumber) {
        boolean isDeleted = service.deleteUserDetails(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(UserRecordConstants.STATUS_200, UserRecordConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UserRecordConstants.STATUS_417, UserRecordConstants.MESSAGE_417_DELETE));
        }
    }
}

