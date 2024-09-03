package com.example.Academy.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;
    @ManyToOne
    @JoinColumn (name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn (name = "member_id")
    private Member member;

}
