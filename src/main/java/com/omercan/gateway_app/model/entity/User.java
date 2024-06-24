package com.omercan.gateway_app.model.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="USERS")
@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "OMERCAN_USERS_SEQ", initialValue = 1, allocationSize = 1)
public class User
{
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQUENCE")
        @Column(name = "USER_ID", nullable = false)
        @Id
        private Integer userId;

        @Column(nullable = false)
        private String username;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        @Column(nullable = false)
        private String password;

        private Date created;
}
