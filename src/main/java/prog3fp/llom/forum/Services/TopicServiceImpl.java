package prog3fp.llom.forum.Services;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import prog3fp.llom.forum.Domain.Topic;
import prog3fp.llom.forum.Repositories.TopicRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {


    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public Topic findTopicByTopicId(Long id) {
        Optional<Topic> topic = topicRepository.findByTopicId(id);
        topic.orElseThrow(() -> new UsernameNotFoundException("Not found: " + id));
        return topic.get();
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }


    @Override
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public Topic findTopByOrderByTopicIdDesc() {
        return topicRepository.findTopByOrderByTopicIdDesc();
    }
}

