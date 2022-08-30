package prog3fp.llom.forum.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prog3fp.llom.forum.Domain.Post;
import prog3fp.llom.forum.Services.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/post/newpost")
    public String add(@PathVariable("topicId") Long topicId, Model model) {
        Post post = new Post();
        post.setTopicId(topicId);
        model.addAttribute("post", post);
        return "editpost";
    }

    @RequestMapping(value = "/post/savepost", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult, Model model) {
        //if post is present

        if(bindingResult.hasErrors()){
            return "editpost";
        }
        String topicId = String.valueOf(post.getTopicId());
        postService.save(post);
        //return to the topic via topicId
        return "redirect:topic/"+topicId;
    }

    @RequestMapping("/post/editpost/{postId}")
    public ModelAndView showEditPostPage(@PathVariable("postId") Long postId) {
        ModelAndView mav = new ModelAndView("editpost");
        Optional<Post> post = postService.findPostByPostId(postId);
        mav.addObject("post", post);
        return mav;

    }
    @RequestMapping("/post/deletepost/{postId}")
    public String deletePostPage(@PathVariable(name = "postId") Long postId) {
        postService.delete(postId);
        return "redirect:topic";
    }

}
