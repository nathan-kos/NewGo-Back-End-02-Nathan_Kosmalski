package org.example.presentation.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateLativoDTO {

    private Boolean lativo;

    public UpdateLativoDTO() {
    }

    public UpdateLativoDTO(Boolean lativo) {
        this.lativo = lativo;
    }
}
