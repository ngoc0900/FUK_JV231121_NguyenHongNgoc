package com.ra.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor@AllArgsConstructor
@Getter
@Setter
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="student_name")
    private String studentName;
    private String address;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "image_url")
    private String imageUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "date")
    private Date birthday;
    private boolean sex;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
