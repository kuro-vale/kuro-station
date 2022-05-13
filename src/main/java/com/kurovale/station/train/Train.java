package com.kurovale.station.train;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "trains")
public class Train
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model", nullable = false, updatable = false)
    private String model;
    @Column(name = "model_year", nullable = false, updatable = false)
    @Min(1980)
    @Max(2022)
    private Integer modelYear;
    @Column(name = "capacity", nullable = false, updatable = false)
    @Min(100)
    @Max(500)
    private Integer capacity;
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Train(String model, Integer modelYear, Integer capacity)
    {
        this.model = model;
        this.modelYear = modelYear;
        this.capacity = capacity;
    }
}
