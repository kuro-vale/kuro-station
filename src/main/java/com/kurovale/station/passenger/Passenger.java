package com.kurovale.station.passenger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "passengers")
public class Passenger
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "address", nullable = false, length = 50)
    private String address;
    @Column(name = "phone", nullable = false)
    @Pattern(regexp = "(^\\(\\d{3}\\)\\s\\d{7})")
    private String phone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Passenger(String name, String address, String phone)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
