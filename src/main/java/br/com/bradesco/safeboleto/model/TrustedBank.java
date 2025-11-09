package br.com.bradesco.safeboleto.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trusted_banks")
@Data
@NoArgsConstructor
public class TrustedBank {

    @Id // <-- Garante que este campo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <-- Gera o valor automaticamente.
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    public TrustedBank(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
