package top.xianyume.iwe.backend.service.intf;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xianyume.iwe.backend.model.vo.ArticleVO;

public interface ArticleService {
    ArticleVO getArticleInfo(Integer id);
    IPage<ArticleVO> getArticleInfoList(String title, Integer pageNum, Integer pageSize);
    void createArticle(String title);
    void updateTitle(Integer id, String title);
    void updateContent(Integer id, String content);
    void updateTool(Integer articleId, String tools);
    String getToolList(Integer articleId);
    void deleteArticle(Integer id);
    IPage<ArticleVO> getMe(Integer id, Integer pageNum, Integer pageSize);
}
