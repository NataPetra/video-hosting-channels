package by.nata.videohostingchannels.database.repository;

import by.nata.videohostingchannels.database.model.Chanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChanelRepository extends JpaRepository<Chanel, Long>, JpaSpecificationExecutor<Chanel> {
}
