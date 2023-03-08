package bssm.deploy.domain.user.facade;

import bssm.deploy.domain.auth.domain.repository.RefreshTokenRepository;
import bssm.deploy.domain.auth.exception.NoSuchRefreshTokenException;
import bssm.deploy.domain.user.domain.User;
import bssm.deploy.domain.user.domain.UserCache;
import bssm.deploy.domain.user.domain.repository.RedisUserRepository;
import bssm.deploy.domain.user.domain.repository.UserRepository;
import bssm.deploy.domain.user.exception.NoSuchUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final RedisUserRepository userRedisRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public User findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByTokenAndIsAvailable(refreshToken, true)
                .orElseThrow(NoSuchRefreshTokenException::new)
                .getUser();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(NoSuchUserException::new);
    }

    public User findByIdOrNull(long id) {
        return userRepository.findById(id)
                .orElseGet(() -> null);
    }

    public User findCachedUser(long id) {
        return userRedisRepository.findById(id)
                .orElseGet(() -> findAndSaveUserCache(id))
                .toUser();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void saveUserCache(User user) {
        userRedisRepository.save(user.toUserCache());
    }

    private UserCache findAndSaveUserCache(long id) {
        User user = findById(id);
        saveUserCache(user);
        return user.toUserCache();
    }

}
