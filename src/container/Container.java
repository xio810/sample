package container;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;

import controller.ExportController;
import dao.ArticleDao;
import dao.MemberDao;
import service.ArticleService;
import service.ExportService;
import service.MemberService;

public class Container {
    public static ArticleDao articleDao;
    public static MemberDao memberDao;
    public static ArticleService articleService;
    public static MemberService memberService;
    public static ExportService exportService;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
        articleService = new ArticleService();
        memberService = new MemberService();
        exportService = new ExportService();
    }
}