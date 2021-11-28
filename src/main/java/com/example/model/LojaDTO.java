package com.example.model;

import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LojaDTO {
    private String nomeLoja;
    private Double total;
    private List<Cnab> operaciones;
}
