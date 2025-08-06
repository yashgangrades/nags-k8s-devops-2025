package com.nagp.advancedevops.assignment.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Yash Gangrade <yash.gangrade@nagarro.com>
 */

@Entity
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
public class UserRecord extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String name;

    private String description;

    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}

