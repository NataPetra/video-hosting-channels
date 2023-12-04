package by.nata.videohostingchannels.database.repository;

import by.nata.videohostingchannels.database.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findAppUserByLogin(String login);
}
