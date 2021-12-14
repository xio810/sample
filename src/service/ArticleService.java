package service;

import java.util.List;

import container.Container;
import dao.ArticleDao;
import dto.Article;

public class ArticleService {
    ArticleDao articleDao;

    public ArticleService() {
        this.articleDao = Container.articleDao;
    }

    public List<Article> getForPrintArticles(String searchKeyword) {
        return articleDao.getArticles(searchKeyword);
    }

    public int getArticleIndexById(int id) {
        return articleDao.getArticleIndexById(id);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }

    public void remove(Article foundArticle) {
        articleDao.remove(foundArticle);
    }
}
