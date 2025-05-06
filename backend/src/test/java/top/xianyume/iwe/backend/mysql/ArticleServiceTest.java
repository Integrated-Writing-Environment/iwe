package top.xianyume.iwe.backend.mysql;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import top.xianyume.iwe.backend.mapper.ArticleMapper;
import top.xianyume.iwe.backend.model.entity.Article;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Article实体类测试类
 * 使用@Transactional注解确保测试后数据回滚
 */
@SpringBootTest
@Transactional
public class ArticleServiceTest {

    @Autowired
    private ArticleMapper articleMapper;

    private Article testArticle;

    /**
     * 在每个测试方法执行前创建测试文章
     */
    @BeforeEach
    public void setUp() {
        testArticle = new Article();
        testArticle.setTitle("测试文章标题");
        testArticle.setContent("测试文章内容");
        articleMapper.insert(testArticle);
    }

    /**
     * 在每个测试方法执行后清理数据
     */
    @AfterEach
    public void tearDown() {
        if (testArticle != null && testArticle.getId() != null) {
            articleMapper.deleteById(testArticle.getId());
        }
    }

    /**
     * 测试新增文章
     */
    @Test
    public void testInsertArticle() {
        Article newArticle = new Article();
        newArticle.setTitle("新文章标题");
        newArticle.setContent("新文章内容");

        int result = articleMapper.insert(newArticle);
        assertEquals(1, result);
        assertNotNull(newArticle.getId());

        // 验证数据是否正确插入
        Article insertedArticle = articleMapper.selectById(newArticle.getId());
        assertEquals("新文章标题", insertedArticle.getTitle());
        assertEquals("新文章内容", insertedArticle.getContent());
    }

    /**
     * 测试根据ID查询文章
     */
    @Test
    public void testSelectArticleById() {
        Article article = articleMapper.selectById(testArticle.getId());
        assertNotNull(article);
        assertEquals("测试文章标题", article.getTitle());
        assertEquals("测试文章内容", article.getContent());
    }

    /**
     * 测试更新文章信息
     */
    @Test
    public void testUpdateArticle() {
        testArticle.setTitle("更新后的标题");
        testArticle.setContent("更新后的内容");

        int result = articleMapper.updateById(testArticle);
        assertEquals(1, result);

        // 验证更新是否成功
        Article updatedArticle = articleMapper.selectById(testArticle.getId());
        assertEquals("更新后的标题", updatedArticle.getTitle());
        assertEquals("更新后的内容", updatedArticle.getContent());
    }

    /**
     * 测试删除文章
     */
    @Test
    public void testDeleteArticle() {
        int result = articleMapper.deleteById(testArticle.getId());
        assertEquals(1, result);

        // 验证是否已删除
        Article deletedArticle = articleMapper.selectById(testArticle.getId());
        assertNull(deletedArticle);
    }

    /**
     * 测试批量插入文章
     */
    @Test
    public void testBatchInsertArticles() {
        List<Article> articles = Arrays.asList(
                createArticle("文章1", "内容1"),
                createArticle("文章2", "内容2"),
                createArticle("文章3", "内容3")
        );

        // 批量插入
        int result = 0;
        for (Article article : articles) {
            result += articleMapper.insert(article);
        }
        assertEquals(3, result);

        // 验证批量插入是否成功
        articles.forEach(article -> {
            Article insertedArticle = articleMapper.selectById(article.getId());
            assertNotNull(insertedArticle);
        });
    }

    /**
     * 测试批量更新文章
     */
    @Test
    public void testBatchUpdateArticles() {
        // 先插入几个测试文章
        List<Article> articles = Arrays.asList(
                createArticle("文章1", "内容1"),
                createArticle("文章2", "内容2")
        );
        articles.forEach(articleMapper::insert);

        // 更新这些文章
        articles.forEach(article -> {
            article.setTitle(article.getTitle() + " 更新");
            articleMapper.updateById(article);
        });

        // 验证更新是否成功
        articles.forEach(article -> {
            Article updatedArticle = articleMapper.selectById(article.getId());
            assertTrue(updatedArticle.getTitle().endsWith("更新"));
        });
    }

    /**
     * 测试批量删除文章
     */
    @Test
    public void testBatchDeleteArticles() {
        // 先插入几个测试文章
        List<Article> articles = Arrays.asList(
                createArticle("文章1", "内容1"),
                createArticle("文章2", "内容2")
        );
        articles.forEach(articleMapper::insert);

        // 批量删除
        articles.forEach(article -> articleMapper.deleteById(article.getId()));

        // 验证是否已删除
        articles.forEach(article -> {
            Article deletedArticle = articleMapper.selectById(article.getId());
            assertNull(deletedArticle);
        });
    }

    /**
     * 辅助方法：创建文章对象
     */
    private Article createArticle(String title, String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        return article;
    }
}