package net.kravuar.graphicalkey.domain.dto;

import jakarta.validation.constraints.Size;

public record UserForm (
        @Size(min = 5, max = 20)
        String username,
        @Size(min = 5)
        short[] key
) {}