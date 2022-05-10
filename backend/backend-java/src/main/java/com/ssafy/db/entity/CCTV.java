package com.ssafy.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "CCTV")
public class CCTV extends BaseEntity {

    @JsonIgnore
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE", length = 128)
    @NotNull
    @Size(max = 32)
    private String type;

    @Column(name = "DANGER")
    @Enumerated(EnumType.STRING)
    private Danger danger;

    @Column(name = "LOCATION", length = 256)
    @NotNull
    @Size(max = 256)
    private String LOCATION;

    @Column(name = "VIDEO_URL", length = 512)
    @NotNull
    @Size(max = 512)
    private String VIDEO_URL;
}