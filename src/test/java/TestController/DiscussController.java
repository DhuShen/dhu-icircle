package TestController;

import com.dhu.domain.Discuss;
import com.dhu.domain.User;
import com.dhu.service.DiscussService;
import com.dhu.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/discuss")
public class DiscussController {
    @Autowired
    DiscussService discussService;

    //根据帖子id获取所有评论
    @RequestMapping("/getDiscuss")
    public Result<List<Discuss>> getDiscuss(@RequestParam Integer postId) {
        List<Discuss> discusses = discussService.selectPostDiscuss(postId);
        return new Result<>(Result.GET_OK, discusses, null);
    }

    //点赞
    @RequestMapping("/good")
    public Result<Boolean> good(@RequestParam Integer discussId) {
        boolean flag = discussService.likeDiscuss(discussId);
        return new Result<>(flag ? Result.UPDATE_OK : Result.UPDATE_ERR, flag, null);
    }

    //添加评论
    @RequestMapping("/add")
    public Result<Boolean> add(@RequestParam Integer postId, @RequestParam String content, @RequestParam String replayed, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (replayed.equals("null"))
            replayed = null;
        boolean flag = discussService.discussInPost(user.getUserId(), postId, content, replayed);
        return new Result<>(flag ? Result.SAVE_OK : Result.SAVE_ERR, flag, null);
    }
}
