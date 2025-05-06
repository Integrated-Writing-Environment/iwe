package top.xianyume.iwe.backend.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import top.xianyume.iwe.backend.mapper.ArticleMapper;
import top.xianyume.iwe.backend.mapper.UserArticleLinkMapper;
import top.xianyume.iwe.backend.model.dto.ArticleDTO;
import top.xianyume.iwe.backend.model.entity.Article;
import top.xianyume.iwe.backend.model.entity.UserArticleLink;
import top.xianyume.iwe.backend.service.intf.ArticleService;

/**
 * @author Xianyume
 * @date 2025/05/05 19:24
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;
    private final UserArticleLinkMapper userArticleLinkMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, UserArticleLinkMapper userArticleLinkMapper) {
        this.articleMapper = articleMapper;
        this.userArticleLinkMapper = userArticleLinkMapper;
    }

    @Override
    public ArticleDTO getArticleInfo(Integer id) {
        Article articleFromSql = articleMapper.selectById(id);
        if (articleFromSql == null) {
            throw new RuntimeException("文章不存在");
        }
        Integer userId = StpUtil.getLoginIdAsInt();
        QueryWrapper<UserArticleLink> wrapper = new QueryWrapper<UserArticleLink>()
                .eq("user_id", userId)
                .eq("article_id", id);
        UserArticleLink userArticleLink = userArticleLinkMapper.selectOne(wrapper);
        if (userArticleLink == null) {
            throw new RuntimeException("用户没有权限查看该文章");
        }

        ArticleDTO article = new ArticleDTO();
        article.setId(articleFromSql.getId());
        article.setTitle(articleFromSql.getTitle());
        article.setContent(articleFromSql.getContent());
        article.setTools(articleFromSql.getTools());

        return article;
    }

    @Override
    public IPage<ArticleDTO> getArticleInfoList(String title, Integer pageNum, Integer pageSize) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "nickname", "description", "create_time")
                .like("title", title)
                .orderByDesc("create_time");

        IPage<Article> articlePage = articleMapper.selectPage(page, queryWrapper);
        return articlePage.convert(article -> {
            ArticleDTO vo = new ArticleDTO();
            vo.setId(article.getId());
            vo.setTitle(article.getTitle());
            vo.setContent(article.getContent());
            vo.setTools(article.getTools());
            return vo;
        });
    }

    @Override
    public void createArticle(String title) {
        Integer userId = StpUtil.getLoginIdAsInt();

        Article article = new Article();
        article.setTitle(title);
        articleMapper.insert(article);

        UserArticleLink userArticleLink = new UserArticleLink();
        userArticleLink.setUserId(userId);
        userArticleLink.setArticleId(article.getId());

        userArticleLinkMapper.insert(userArticleLink);

    }

    @Override
    public void updateTitle(Integer id, String title) {
        if (id == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        if (title.equals(article.getTitle())) {

        } else {
            article.setTitle(title);
            articleMapper.updateById(article);
        }
    }

    @Override
    public void updateContent(Integer id, String content) {
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        article.setContent(content);
        articleMapper.updateById(article);
    }

    @Override
    public JSON getToolList(Integer articleId) {
        if (articleId == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }
        Article article = articleMapper.selectById(articleId);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        if (article.getTools() == null) {
            return JSONUtil.createArray();
        }
        return article.getTools();
    }

    @Override
    public void deleteArticle(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }
        Article article = articleMapper.selectById(id);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        Integer userId = StpUtil.getLoginIdAsInt();
        articleMapper.deleteById(id);
        userArticleLinkMapper.delete(new QueryWrapper<UserArticleLink>().eq("user_id", userId));
    }

    @Override
    public void updateTool(Integer articleId, JSON tools) {
        if (articleId == null) {
            throw new IllegalArgumentException("文章ID不能为空");
        }
        Article article = articleMapper.selectById(articleId);
        if (article == null) {
            throw new RuntimeException("文章不存在");
        }
        if (tools == null) {
            throw new IllegalArgumentException("工具列表不能为空");
        }
        article.setTools(tools);
        articleMapper.updateById(article);
    }
}

