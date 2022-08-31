package prog3fp.llom.forum.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prog3fp.llom.forum.Domain.Post;
import prog3fp.llom.forum.Domain.Topic;
import prog3fp.llom.forum.Services.PostService;
import prog3fp.llom.forum.Services.TopicService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class TopicController {

    @Autowired
    private PostService postService;
    @Autowired
    private TopicService topicService;

    @GetMapping("/")
    public String index(Model model){
        List<Topic> listtopics = topicService.findAllTopics();
        model.addAttribute("listtopics", listtopics);
        return "index";
    }

    @GetMapping("/topic/{topicId}")
    public ModelAndView viewHomePage(@PathVariable("topicId") Long topicId, Model model) {
        List<Post> postList = postService.findTopicPosts(topicId);
        Topic topic = topicService.findTopicByTopicId(topicId);
        topic.setPostList(postList);
        ModelAndView mav = new ModelAndView("topic");
        mav.addObject("topic", topic);

        return mav;
    }

    @GetMapping("/topic/newtopic")
    public String add(Model model) {
        model.addAttribute("topic", new Topic());
        return "newtopic";
    }

    @RequestMapping(value = "/topic/savetopic", method = RequestMethod.POST)
    public String saveTopic(@ModelAttribute("topic") @Valid Topic topic, BindingResult bindingResult, Model model) {
        //if topic is present

        if(bindingResult.hasErrors()){
            return "newtopic";
        }
        topicService.save(topic);
        Topic saved = topicService.findTopByOrderByTopicIdDesc();
        return "redirect:/";
    }

    @RequestMapping("/topic/edittopic/{topicId}")
    public ModelAndView showEditTopicPage(@PathVariable("topicId") Long topicId) {
        ModelAndView mav = new ModelAndView("newtopic");
        Topic topic = topicService.findTopicByTopicId(topicId);
        mav.addObject("topic", topic);
        return mav;

    }
    @RequestMapping("/topic/deletetopic/{topicId}")
    public String deleteTopicPage(@PathVariable(name = "topicId") Long topicId) {
        topicService.delete(topicId);
        return "redirect:/";
    }
}
