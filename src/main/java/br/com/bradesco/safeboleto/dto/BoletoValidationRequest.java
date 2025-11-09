package br.com.bradesco.safeboleto.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BoletoValidationRequest {
    @NotEmpty(message = "O código de barras não pode ser vazio.")
    private String barcode;
}