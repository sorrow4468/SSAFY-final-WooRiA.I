package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECORD")
public class Record {

    @JsonIgnore
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VIOLENT_CNT", length = 128)
    @NotNull
    @Size(max = 128)
    private Long VIOLENT_CNT;

    @Column(name = "STUMBLE_CNT", length = 128)
    @NotNull
    @Size(max = 128)
    private Long STUMBLE_CNT;

    @Column(name = "TOTAL", length = 128)
    @NotNull
    @Size(max = 128)
    private Long TOTAL;
}
