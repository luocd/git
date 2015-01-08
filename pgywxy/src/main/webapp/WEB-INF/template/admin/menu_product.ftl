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
		<@sec.authorize ifAnyGranted="ROLE_COOPERATE,ROLE_PRODUCT">
			<dl>
				<dt>
					<span>产品管理</span>
				</dt>
				<@sec.authorize ifAnyGranted="ROLE_PRODUCT">
					<dd>
						<a href="${base}/product/product!list.action?ptype=4" target="mainFrame">自有产品管理</a>
					</dd>
				</@sec.authorize>
				<@sec.authorize ifAnyGranted="ROLE_COOPERATE">
					<dd>
						<a href="${base}/product/product!list.action?ptype=5" target="mainFrame">合作产品管理</a>
					</dd>
				</@sec.authorize>
			</dl>
		</@sec.authorize>
	</div>
</body>
</html>