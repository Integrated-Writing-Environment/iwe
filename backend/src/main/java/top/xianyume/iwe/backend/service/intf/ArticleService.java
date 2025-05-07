package top.xianyume.iwe.backend.service.intf;

import cn.hutool.json.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.xianyume.iwe.backend.model.dto.ArticleDTO;

public interface ArticleService {
    ArticleDTO getArticleInfo(Integer id);
    IPage<ArticleDTO> getArticleInfoList(String title, Integer pageNum, Integer pageSize);
    void createArticle(String title);
    void updateTitle(Integer id, String title);
    void updateContent(Integer id, String content);
    void updateTool(Integer articleId, JSON tools);
    JSON getToolList(Integer articleId);
    void deleteArticle(Integer id);
}
