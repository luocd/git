<#assign sec=JspTaglibs["/WEB-INF/tld/security.tld"] />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 </title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $menuItem = $("#menu .menuItem");
	var $previousMenuItem;
	
	$menuItem.click( function() {
		var $this = $(this);
		if ($previousMenuItem != null) {
			$previousMenuItem.removeClass("current");
		}
		$previousMenuItem = $this;
		$this.addClass("current");
	})

})
</script>
</head>
<body class="header">
	<div class="body">
		<div class="bodyLeft">
			<div class="logo"></div>
		</div>
		<div class="bodyRight">
			<div class="link">
				<span class="welcome">
					<strong><@sec.authentication property="name" /></strong>&nbsp;您好!&nbsp;
				</span>
			</div>
			<div id="menu" class="menu">
				<ul>
				<@sec.authorize ifAnyGranted="ROLE_HOME">
						<li class="menuItem">
							<a href="menu!home.action" target="menuFrame" hidefocus>首页管理</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_COOPERATE,ROLE_PRODUCT">
						<li class="menuItem">
							<a href="menu!product.action" target="menuFrame" hidefocus>产品管理</a>
						</li>
					</@sec.authorize>
				<#--<@sec.authorize ifAnyGranted="ROLE_PRODUCT">
						<li class="menuItem">
							<a href="#" target="menuFrame" hidefocus>自有产品</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_COOPERATE">
						<li class="menuItem">
							<a href="#" target="menuFrame" hidefocus>合作产品</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_CAROUSEL">
						<li class="menuItem">
							<a href="#" target="menuFrame" hidefocus>轮播图片</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_INTRODUCTION">
						<li class="menuItem">
							<a href="#" target="menuFrame" hidefocus>中心简介</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_FOOTER_PIC">
						<li class="menuItem">
							<a href="#" target="menuFrame" hidefocus>底部图片</a>
						</li>
					</@sec.authorize>
				   <@sec.authorize ifAnyGranted="ROLE_MEMBER_RANK">
						<li class="menuItem">
							<a href="menu!member.action" target="menuFrame" hidefocus>会员管理</a>
						</li>
					</@sec.authorize>-->
					<@sec.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROLE,ROLE_MESSAGE,ROLE_LOG">
						<li class="menuItem">
							<a href="menu!admin.action" target="menuFrame" hidefocus>管理员</a>
						</li>
					</@sec.authorize>
					<@sec.authorize ifAnyGranted="ROLE_SETTING,ROLE_INSTANT_MESSAGING,ROLE_PAYMENT_CONFIG,ROLE_DELIVERY_TYPE,ROLE_AREA,ROLE_DELIVERY_CORP">
						<li class="menuItem">
							<a href="menu!setting.action" target="menuFrame" hidefocus>网站设置</a>
						</li>
					</@sec.authorize>
	            </ul>
	            <div class="info">
					<a class="profile" href="admin_profile!edit.action" target="mainFrame">个人资料</a>
					<a class="logout" href="${base}/admin/logout" target="_top">退出</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>