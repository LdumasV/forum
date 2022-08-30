package prog3fp.llom.forum.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prog3fp.llom.forum.Domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}