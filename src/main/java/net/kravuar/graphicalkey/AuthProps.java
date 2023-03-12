package net.kravuar.graphicalkey;

import jakarta.validation.constraints.Min;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app.auth")
@Validated
public record AuthProps(
        @Min(1) int maxAttempts,
        @Min(1) int attemptTimeout
) {}