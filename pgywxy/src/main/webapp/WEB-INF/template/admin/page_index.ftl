<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 </title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
</head>
<body class="index">
	<div class="bar">
		欢迎使用管理系统！
	</div>
	<div class="body">
		<div class="bodyLeft">
			<table class="listTable">
				<tr>
					<th colspan="2">
						软件信息
					</th>
				</tr>
				<tr>
					<td width="110">
						系统名称: 
					</td>
					<td>
						${setting.systemName}
					</td>
				</tr>
				<tr>
					<td>
						系统版本: 
					</td>
					<td>
						${setting.systemVersion}
					</td>
				</tr>
				<tr>
					<td>
						BUG反馈邮箱: 
					</td>
					<td>
						<a href="mailto:bug@shopxx.net">dony@pgywxy.com.cn</a>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp; 
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						
					</td>
				</tr>
			</table>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">
						待处理事务
					</th>
				</tr>
				<tr>
					<td width="110">
						未读消息: 
					</td>
					<td>
						${unreadMessageCount} <a href="message!inbox.action">[收件箱]</a>
					</td>
				</tr>
				<tr>
					<td width="110">
						&nbsp; 
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;  
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
					<td>
						
					</td>
				</tr>
			</table>
		</div>
		<div class="bodyRight">
			<table class="listTable">
				<tr>
					<th colspan="2">
						系统信息
					</th>
				</tr>
				<tr>
					<td width="110">
						Java版本: 
					</td>
					<td>
						${javaVersion}
					</td>
				</tr>
				<tr>
					<td>
						操作系统名称: 
					</td>
					<td>
						${osName}
					</td>
				</tr>
				<tr>
					<td>
						操作系统构架: 
					</td>
					<td>
						${osArch}
					</td>
				</tr>
				<tr>
					<td>
						操作系统版本: 
					</td>
					<td>
						${osVersion}
					</td>
				</tr>
				<tr>
					<td>
						Server信息: 
					</td>
					<td>
						${serverInfo}
					</td>
				</tr>
				<tr>
					<td>
						Servlet版本: 
					</td>
					<td>
						${servletVersion}
					</td>
				</tr>
			</table>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">
						信息统计
					</th>
				</tr>
				<tr>
					<td width="110">
						&nbsp;
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp; 
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp; 
					</td>
					<td>
						
					</td>
				</tr>
				<tr>
					<td>
						&nbsp; 
					</td>
					<td>
						
					</td>
				</tr>
			</table>
		</div>
		<p class="copyright">Copyright © 2011-2012 xunx.com.cn All Rights Reserved.</p>
	</div>
</body>
</html>