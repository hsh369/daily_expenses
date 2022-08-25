package com.example.dailyexpenses.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
@Data
@NoArgsConstructor
@ToString
public class UserEntity {

    public UserEntity(long userChatId, String username, Date userDate) {
        this.userChatId = userChatId;
        this.username = username;
        this.userDate = userDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(
            name = "user_chat_id",
            nullable = false,
            unique = true
    )
    private long userChatId;

    @Column(
            name = "username",
            columnDefinition = "TEXT"
    )
    private String username;

    @Column(
            name = "user_date",
            columnDefinition = "TIMESTAMP"
    )
    private Date userDate;

    @OneToMany(mappedBy = "user",targetEntity = ProductEntity.class,cascade = CascadeType.ALL)
    private List<ProductEntity> products=new ArrayList<>();
}
