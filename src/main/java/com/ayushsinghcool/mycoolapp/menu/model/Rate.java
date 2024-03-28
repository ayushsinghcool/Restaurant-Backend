package com.ayushsinghcool.mycoolapp.menu.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Embeddable
public class Rate {
        public Rate() {
        }

        private Double half;
        private Double full;

}
