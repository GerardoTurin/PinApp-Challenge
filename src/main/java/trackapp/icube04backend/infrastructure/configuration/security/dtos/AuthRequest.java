package trackapp.icube04backend.infrastructure.configuration.security.dtos;

public record AuthRequest(
        String username,
        String password
) {
}
