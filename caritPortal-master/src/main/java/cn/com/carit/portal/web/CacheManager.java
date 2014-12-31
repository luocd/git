package cn.com.carit.portal.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import cn.com.carit.common.Constants;
import cn.com.carit.portal.LanguageConfig;
import cn.com.carit.portal.bean.BannerAd;
import cn.com.carit.portal.bean.Catalog;
import cn.com.carit.portal.bean.DemoVideo;
import cn.com.carit.portal.bean.GlobalAddress;
import cn.com.carit.portal.bean.Menu;
import cn.com.carit.portal.bean.News;
import cn.com.carit.portal.bean.ProductRelease;
import cn.com.carit.portal.bean.SupportLanguage;
import cn.com.carit.portal.bean.TreeNode;
import cn.com.carit.portal.service.BannerAdService;
import cn.com.carit.portal.service.CatalogService;
import cn.com.carit.portal.service.DemoVideoService;
import cn.com.carit.portal.service.GlobalAddressService;
import cn.com.carit.portal.service.MenuService;
import cn.com.carit.portal.service.NewsService;
import cn.com.carit.portal.service.ProductReleaseService;
import cn.com.carit.portal.service.SupportLanguageService;

public class CacheManager {
	private final Logger logger=LoggerFactory.getLogger(getClass());
	
	private MenuService<Menu> menuService;
	
	private NewsService<News> newsService;
	
	private CatalogService<Catalog> catalogService;
	
	private ProductReleaseService<ProductRelease> productReleaseService;
	
	private SupportLanguageService<SupportLanguage> supportLanguageService;
	
	private BannerAdService<BannerAd> bannerAdService;
	
	private GlobalAddressService<GlobalAddress> globalAddressService;
	
	private DemoVideoService<DemoVideo> demoVideoService;

	private List<TreeNode> menuTree;
	
	private Map<Integer, News> allNewsCache;
	
	private List<Catalog> allCatalogList;
	
	private Map<Integer, ProductRelease> allProductReleaseCache;
	
	private List<SupportLanguage> supportLanguages;
	/**已支持语言地区缓存*/
	private Map<String, Locale> supportLocaleCache;
	
//	private List<MediaGallery> topImages;
	
	private List<BannerAd> bannerAdList;
	
	private List<GlobalAddress> defaultAddressList;
	
	private List<DemoVideo> newestVideoList;
	
	private static class CacheHolder {
		private static final CacheManager INSTANCE = new CacheManager();
	}
	
	@SuppressWarnings("unchecked")
	private CacheManager() {
		logger.info(" init cache start...");
		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		menuService = (MenuService<Menu>) ctx.getBean("menuServiceImpl");
		newsService = (NewsService<News>) ctx.getBean("newsServiceImpl");
		catalogService = (CatalogService<Catalog>) ctx.getBean("catalogServiceImpl");
		productReleaseService = (ProductReleaseService<ProductRelease>) ctx.getBean("productReleaseServiceImpl");
		supportLanguageService = (SupportLanguageService<SupportLanguage>) ctx.getBean("supportLanguageServiceImpl");
//		mediaGalleryService = (MediaGalleryService<MediaGallery>) ctx.getBean("mediaGalleryServiceImpl");
		bannerAdService = (BannerAdService<BannerAd>) ctx.getBean("bannerAdServiceImpl");
		globalAddressService=(GlobalAddressService<GlobalAddress>) ctx.getBean("globalAddressServiceImpl");
		demoVideoService=(DemoVideoService<DemoVideo>) ctx.getBean("demoVideoServiceImpl");
		
		menuTree=new ArrayList<TreeNode>();
		buildMenuTree();
		
		allNewsCache=new ConcurrentHashMap<Integer, News>();
		buildNews();
		
		refreshCatalogs();
		
		allProductReleaseCache=new ConcurrentHashMap<Integer, ProductRelease>();
		buildProductReleases();
		
		supportLocaleCache=new HashMap<String, Locale>();
		refreshSupportLanguages();
		
		refreshBannerAd();
		refreshAddressList();
		refreshVideo();
		logger.info(" init cache end ...");
	}
	
	public static CacheManager getInstance(){
		return CacheHolder.INSTANCE;
	}
	
	/**
	 * 刷新缓存
	 */
	public void refreshCache(){
		refreshMenu();
		refreshNews();
		refreshCatalogs();
		refreshProducts();
		refreshSupportLanguages();
		refreshBannerAd();
		refreshAddressList();
		refreshVideo();
	}
	
	public void refreshMenu(){
		menuTree.clear();
		buildMenuTree();
	}
	
	private void buildMenuTree(){
		List<Menu> topMenus=menuService.queryTopMenus();
		for (Menu menu : topMenus) {
			TreeNode node=new TreeNode();
			node.setId(menu.getId());
			node.setCode(menu.getCode());
			node.setUrl(menu.getUrl());
			menuTree.add(node);
			buildSubTree(node);
		}
	}
	
	private void buildSubTree(TreeNode node){
		List<Menu> subMenus=menuService.queryByParent(node.getId());
		if (subMenus!=null && subMenus.size() > 0) {
			List<TreeNode> children=new ArrayList<TreeNode>();
			for (Menu menu : subMenus) {
				TreeNode subNode=new TreeNode();
				subNode.setId(menu.getId());
				subNode.setCode(menu.getCode());
				subNode.setUrl(menu.getUrl());
				children.add(subNode);
				buildSubTree(subNode);
			}
			node.setChildren(children);
		}
	}
	
	public List<TreeNode> getMenuTree(){
		return menuTree;
	}
	
	public void refreshNews(){
		allNewsCache.clear();
		buildNews();
	}
	
	private void buildNews(){
		News sample=new News();
		sample.setStatus(Constants.STATUS_VALID);
		List<News> allNewsList = newsService.queryByExemple(sample);
		for (News t : allNewsList) {
			allNewsCache.put(t.getId(), t);
		}
	}
	
	public News getNews(int id) {
		return allNewsCache.get(id);
	}
	
	private void buildProductReleases(){
		ProductRelease sample = new ProductRelease();
		sample.setStatus(Constants.STATUS_VALID);
		List<ProductRelease> list=productReleaseService.queryByExemple(sample);
		for (ProductRelease productRelease : list) {
			allProductReleaseCache.put(productRelease.getId(), productRelease);
		}
	}
	
	public void refreshProducts(){
		allProductReleaseCache.clear();
		buildProductReleases();
	}
	
	public ProductRelease getProductRelease(int id){
		return allProductReleaseCache.get(id);
	}
	
	public void refreshCatalogs(){
		Catalog sample=new Catalog();
		sample.setStatus(Constants.STATUS_VALID);
		allCatalogList=catalogService.queryByExemple(sample);
	}

	public List<Catalog> getAllCatalogList() {
		return allCatalogList;
	}
	
	public void refreshSupportLanguages(){
		SupportLanguage sample=new SupportLanguage();
		sample.setStatus(Constants.STATUS_VALID);
		supportLanguages=supportLanguageService.queryByExemple(sample);
		
		for (SupportLanguage language : supportLanguages) {
			supportLocaleCache.put(language.getIsoCode(), 
					LanguageConfig.getInstance().getLocale(language.getIsoCode()));
		}
	}

	public List<SupportLanguage> getSupportLanguages() {
		return supportLanguages;
	}

//	public List<MediaGallery> getTopImages() {
//		return topImages;
//	}
//	
//	public void refreshMedia(){
//		MediaGallery sample=new MediaGallery();
//		sample.setStatus(Constants.STATUS_VALID);
//		sample.setTop(MediaGallery.TOP);
//		topImages=mediaGalleryService.queryByExemple(sample);
//	}
	
	public List<BannerAd> getBannerAdList() {
		return bannerAdList;
	}
	
	public void refreshBannerAd(){
		bannerAdList=bannerAdService.query(Constants.MAX_BANNER_AD_COUNT);
	}
	

	public List<GlobalAddress> getDefaultAddressList() {
		return defaultAddressList;
	}
	
	public void refreshAddressList(){
		defaultAddressList=globalAddressService.query(Constants.DEAFULD_LANGUAGE);
	}
	
	public List<DemoVideo> getNewestVideoList() {
		return newestVideoList;
	}

	public void refreshVideo(){
		newestVideoList=demoVideoService.queryNewest(Constants.INDEX_SHOW_LIMIT);
	}
	/**
	 * 按照ISO语言代码获取已经支持的语言国家/地区，如该地区还没有支持则返回{@link Locale.US}
	 * @param languageCode
	 * @return
	 */
	public Locale getSupportedLocale(String languageCode){
		if (supportLocaleCache.containsKey(languageCode)) {
			return supportLocaleCache.get(languageCode);
		}
		return Locale.US;
	}

}