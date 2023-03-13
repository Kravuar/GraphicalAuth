package net.kravuar.graphicalkey.domain.dto;

import java.util.List;

public record LoginMessage(List<String> message, int attemptsLeft) {}