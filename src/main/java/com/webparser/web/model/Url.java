package com.webparser.web.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "web")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name_url;
    private String name, twitter, facebook, logo, icon, employees, address;



}
