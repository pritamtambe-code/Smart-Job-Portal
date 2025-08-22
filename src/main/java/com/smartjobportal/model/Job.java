package com.smartjobportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Primary key

    private String title;
    private String description;
    private String location;

    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate = new Date();

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private User employer;
}
