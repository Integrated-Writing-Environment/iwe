package top.xianyume.iwe.backend.controller;

import cn.dev33.satoken.util.SaResult;
import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.validation.Valid;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.xianyume.iwe.backend.model.dto.ArticleContentDTO;
import top.xianyume.iwe.backend.model.dto.ArticleDTO;
import top.xianyume.iwe.backend.model.dto.ToolCallDTO;
import top.xianyume.iwe.backend.service.intf.ArticleService;

import java.util.List;

/**
 * @author Xianyume
 * @date 2025/05/05 19:23
 **/
@RestController
@RequestMapping("/articles")
@Validated
public class ArticleController {

    private final ArticleService articleService;

    private final OpenAiChatModel chatModel;


    public ArticleController(ArticleService articleService, OpenAiChatModel chatModel) {
        this.articleService = articleService;
        this.chatModel = chatModel;
    }

    @GetMapping("/{id}")
    public SaResult getArticleInfo(@PathVariable Integer id) {
        ArticleDTO article = articleService.getArticleInfo(id);
        return SaResult.ok("获取文章成功")
                .setData(article);
    }

    @GetMapping
    public SaResult getArticleInfoList(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        IPage<ArticleDTO> articleList = articleService.getArticleInfoList(title, pageNum, pageSize);
        return SaResult.ok("获取文章列表成功")
                .setData(articleList);
    }

    @PostMapping
    public SaResult createArticle(@ModelAttribute @Valid ArticleDTO article) {
        articleService.createArticle(article.getTitle());
        return SaResult.ok("创建文章成功");
    }

    @PutMapping("/title")
    public SaResult updateArticleTitle(@ModelAttribute @Valid ArticleDTO article) {
        articleService.updateTitle(article.getId(), article.getTitle());
        return SaResult.ok("更新文章成功");
    }

    @PutMapping("/content")
    public SaResult updateArticleContent(@ModelAttribute @Valid ArticleContentDTO article) {
        articleService.updateContent(article.getId(), article.getContent());
        return SaResult.ok("更新文章成功");
    }

    @DeleteMapping("/{id}")
    public SaResult deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
        return SaResult.ok("删除文章成功");
    }

    @GetMapping("/tools")
    public SaResult getArticleTools(@RequestParam Integer id) {
        JSON toolList = articleService.getToolList(id);
        return SaResult.ok("获取文章工具成功")
                .setData(toolList);
    }

    @PutMapping("/tools")
    public SaResult updateArticleTools(@ModelAttribute @Valid ArticleDTO article) {
        articleService.updateTool(article.getId(), article.getTools());
        return SaResult.ok();
    }

    @PostMapping("/call")
    public SaResult getToolCall(@ModelAttribute @Valid ToolCallDTO call) {
        List<Message> messages = List.of(
                new SystemMessage(call.getFc()),
                new UserMessage(call.getOriginalContent())
        );
        Prompt prompt = new Prompt(messages);
        String result = chatModel.call(prompt).getResult().getOutput().getText();
        return SaResult.ok("文章的小工具调用成功")
                .setData(result);
    }
}

