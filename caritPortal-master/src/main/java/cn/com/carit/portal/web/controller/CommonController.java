package cn.com.carit.portal.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.carit.common.Constants;
import cn.com.carit.common.utils.DataGridModel;
import cn.com.carit.common.utils.JsonPage;
import cn.com.carit.portal.bean.Catalog;
import cn.com.carit.portal.bean.MediaGallery;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.service.MediaGalleryService;
import cn.com.carit.portal.service.NewsService;
import cn.com.carit.portal.service.ProductReleaseService;
import cn.com.carit.portal.web.CacheManager;

@Controller
@RequestMapping(value="common", method=RequestMethod.GET)
public class CommonController {

	@Resource
	private ProductReleaseService<ProductRelease> productReleaseService;
	
	@Resource
	private NewsService<News> newsService;
	
	@Resource
	private MediaGalleryService<MediaGallery> mediaGalleryService;
	/**
	 * 获取所有车型分类
	 * @return
	 */
	@RequestMapping(value="query/catalogs")
	public @ResponseBody List<Catalog> allCatalogs(){
		return CacheManager.getInstance().getAllCatalogList();
	}
	
	/**
	 * 按类别获取产品列表
	 * @param catalogId
	 * @param dgm
	 * @return
	 */
	@RequestMapping(value="query/product/{language}")
	public @ResponseBody JsonPage<ProductRelease> queryProductByCatalog(@PathVariable String language,@RequestParam(required=false) Integer catalogId, DataGridModel dgm){
		ProductRelease t=new ProductRelease();
		t.setLanguage(language);
		if (catalogId!=null) {
			t.setCatalogId(catalogId);
		}
		return productReleaseService.queryByExemple(t, dgm);
	}
	
	/**
	 * 查询新闻动态
	 * @param language
	 * @param dgm
	 * @return
	 */
	@RequestMapping(value="query/news")
	public @ResponseBody JsonPage<News> queryNewsByLanguage(@ModelAttribute(value="news") News t, BindingResult result, DataGridModel dgm){
		t.setStatus(Constants.STATUS_VALID);
		return newsService.queryByExemple(t, dgm);
	}

	/**
	 * 检测是否存在
	 * @param name
	 * @return
	 */
	@RequestMapping(value="check/media")
	public @ResponseBody int checkExisted(@RequestParam String name){
		return mediaGalleryService.checkExisted(name);
	}
}
