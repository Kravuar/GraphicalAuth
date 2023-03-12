package net.kravuar.graphicalkey.domain.dto;

import jakarta.validation.constraints.Size;

public record UserForm (
        @Size(min = 5, max = 20, message = "validation.username")
        String username,
        @Size(min = 5, message = "validation.key")
        short[] key
) {}