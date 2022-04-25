package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CCTV")
public class CCTV extends BaseEntity {

    @JsonIgnore
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="userId")
    User user;

    @Column(name = "TYPE", length = 128)
    @NotNull
    @Size(max = 32)
    private Long type;

    @Column(name = "STYPE", length = 128)
    @NotNull
    @Size(max = 32)
    private Long stype;

    @Column(name = "START_TIME", length = 128)
    @NotNull
    private LocalDateTime START_TIME;

    @Column(name = "END_TIME", length = 128)
    @NotNull
    private LocalDateTime END_TIME;

    @Column(name = "LOCATION", length = 256)
    @NotNull
    @Size(max = 256)
    private String LOCATION;

    @Column(name = "VIDEO_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String VIDEO_URL;
}
