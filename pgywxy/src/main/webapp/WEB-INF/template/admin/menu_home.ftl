<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单 </title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="menu">
	<div class="body">
		<@sec.authorize ifAnyGranted="ROLE_HOME">
			<dl>
				<dt>
					<span>首页设置</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_HOME">
					<dd>
						<a href="${base}/product/product!list.action?ptype=1" target="mainFrame">轮播图片管理</a>
					</dd>
					<dd>
						<a href="${base}/product/product!list.action?ptype=3" target="mainFrame">底部图片管理</a>
					</dd>
					<dd>
						<a href="${base}/product/product!list.action?ptype=2" target="mainFrame">中心简介管理</a>
					</dd>
					<dd>
						<a href="${base}/product/product!list.action?ptype2=4" target="mainFrame">产品列表管理</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
		
	</div>
</body>
</html>