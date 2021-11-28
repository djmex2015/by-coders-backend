package com.example.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "cnab")
public class Cnab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ETipo tipo;
    private LocalDate data;
    private Double valor;
    private Integer cpf;
    private String cartao;
    private LocalTime hora;
    private String donoLoja;
    private String nomeLoja;
}
