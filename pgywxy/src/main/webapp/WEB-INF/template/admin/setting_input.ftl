<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>系统设置</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.tools.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	
	var $tab = $("#tab");
	var $isLoginFailureLockInput = $(".isLoginFailureLock");
	var $loginFailureLockCountTr = $("#loginFailureLockCountTr");
	var $loginFailureLockTimeTr = $("#loginFailureLockTimeTr");
	var $smtpFromMail = $("#smtpFromMail");
	var $smtpHost = $("#smtpHost");
	var $smtpPort = $("#smtpPort");
	var $smtpUsername = $("#smtpUsername");
	var $smtpPassword = $("#smtpPassword");
	var $smtpToMailWrap = $("#smtpToMailWrap");
	var $smtpToMail = $("#smtpToMail");
	var $smtpTest = $("#smtpTest");
	var $smtpTestStatus = $("#smtpTestStatus");
	var $scoreType = $(".scoreType");
	var $scoreScaleTr = $("#scoreScaleTr");
	var $scoreScale = $("#scoreScale");
	
	// Tab效果
	$tab.tabs(".tabContent", {
		tabs: "input"
	});
	
	$isLoginFailureLockInput.click( function() {
		var $this = $(this);
		if($this.val() == "true") {
			$loginFailureLockCountTr.show();
			$loginFailureLockTimeTr.show();
		} else {
			$loginFailureLockCountTr.hide();
			$loginFailureLockTimeTr.hide();
		}
	});
	
	// 邮箱测试
	$smtpTest.click( function() {
		var $this = $(this);
		if ($this.val() == "邮箱测试") {
			$this.val("发送邮件");
			$smtpToMailWrap.show();
		} else {
			if ($.trim($smtpFromMail.val()) == "") {
				$.dialog({type: "warn", content: "请输入发件人邮箱!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if (!/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test($.trim($smtpFromMail.val()))) {
				$.dialog({type: "warn", content: "发件人邮箱格式错误!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if ($.trim($smtpHost.val()) == "") {
				$.dialog({type: "warn", content: "请输入SMTP服务器地址!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if ($.trim($smtpPort.val()) == "") {
				$.dialog({type: "warn", content: "请输入SMTP服务器端口!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if (!/^[0-9]+$/.test($.trim($smtpPort.val()))) {
				$.dialog({type: "warn", content: "SMTP服务器端口格式错误!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if ($.trim($smtpUsername.val()) == "") {
				$.dialog({type: "warn", content: "请输入SMTP用户名!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if ($.trim($smtpPassword.val()) == "") {
				$.dialog({type: "warn", content: "请输入SMTP密码!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if ($.trim($smtpToMail.val()) == "") {
				$.dialog({type: "warn", content: "请输入收件人邮箱!", modal: true, autoCloseTime: 3000});
				return false;
			}
			if (!/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/.test($.trim($smtpToMail.val()))) {
				$.dialog({type: "warn", content: "收件人邮箱格式错误!", modal: true, autoCloseTime: 3000});
				return false;
			}
			
			$.ajax({
				url: "setting!ajaxSendSmtpTest.action",
				data: {"setting.smtpFromMail": $smtpFromMail.val(), "setting.smtpHost": $smtpHost.val(), "setting.smtpPort": $smtpPort.val(), "setting.smtpUsername": $smtpUsername.val(), "setting.smtpPassword": $smtpPassword.val(), "smtpToMail": $smtpToMail.val()},
				type: "POST",
				dataType: "json",
				cache: false,
				beforeSend: function(data) {
					$smtpTestStatus.html('<span class="loadingIcon">&nbsp;</span>正在发送测试邮件,请稍后...');
					$this.attr("disabled", true);
				},
				success: function(data) {
					$smtpTestStatus.empty();
					$this.attr("disabled", false);
					$.dialog({type: data.status, content: data.message, width: 400, ok: "确 定", modal: true});
				}
			});
		}
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		ignore: ".ignoreValidate",
		rules: {
			"setting.shopName": "required",
			"setting.shopUrl": "required",
			"shopLogo": "imageFile",
			"setting.loginFailureLockCount": {
				required: true,
				positiveInteger: true
			},
			"setting.loginFailureLockTime": {
				required: true,
				digits: true
			},
			"setting.smtpFromMail": {
				required: true,
				email: true
			},
			"setting.smtpHost": "required",
			"setting.smtpPort": {
				required: true,
				digits: true
			},
			"setting.smtpUsername": "required"
		},
		messages: {
			"setting.shopName": "请填写网店名称",
			"setting.shopUrl": "请填写网店网址",
			"shopLogo": "网店LOGO格式错误",
			"setting.loginFailureLockCount": {
				required: "请填写连续登录失败最大次数",
				positiveInteger: "连续登录失败最大次数请输入合法的正整数"
			},
			"setting.loginFailureLockTime": {
				required: "请填写自动解锁时间",
				digits: "自动解锁时间必须为零或正整数"
			},
			"setting.smtpFromMail": {
				required: "请填写发件人邮箱",
				email: "发件人邮箱格式错误"
			},
			"setting.smtpHost": "请填写SMTP服务器地址",
			"setting.smtpPort": {
				required: "请填写SMTP服务器端口",
				digits: "SMTP服务器端口必须为零或正整数"
			},
			"setting.smtpUsername": "请填写SMTP用户名"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});
	
})
</script>
</head>
<body class="input">
	<div class="bar">
		系统设置
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" class="form" action="setting!update.action" enctype="multipart/form-data" method="post">
			<ul id="tab" class="tab">
				<li>
					<input type="button" value="基本设置" hidefocus />
				</li>
				<li>
					<input type="button" value="安全设置" hidefocus />
				</li>
				<li>
					<input type="button" value="邮件设置" hidefocus />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						网店名称: 
					</th>
					<td>
						<input type="text" name="setting.shopName" class="formText" value="${setting.shopName}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						网店网址: 
					</th>
					<td>
						<input type="text" name="setting.shopUrl" class="formText" value="${setting.shopUrl}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						网店LOGO: 
					</th>
					<td>
						<input type="file" name="shopLogo" />
						<a href="${base}${setting.shopLogoPath}" target="_blank">查看</a>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						是否自动锁定账号: 
					</th>
					<td>
						<label><input type="radio" name="setting.isLoginFailureLock" class="isLoginFailureLock" value="true"<#if setting.isLoginFailureLock> checked</#if> />是</label>
						<label><input type="radio" name="setting.isLoginFailureLock" class="isLoginFailureLock" value="false"<#if !setting.isLoginFailureLock> checked</#if> />否</label>
					</td>
				</tr>
				<tr id="loginFailureLockCountTr"<#if !setting.isLoginFailureLock> class="hidden"</#if>>
					<th>
						连续登录失败最大次数: 
					</th>
					<td>
						<input type="text" name="setting.loginFailureLockCount" class="formText" value="${setting.loginFailureLockCount}" title="只允许输入正整数,当连续登录失败次数超过设定值时,系统将自动锁定该账号" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr id="loginFailureLockTimeTr"<#if !setting.isLoginFailureLock> class="hidden"</#if>>
					<th>
						自动解锁时间: 
					</th>
					<td>
						<input type="text" name="setting.loginFailureLockTime" class="formText" value="${setting.loginFailureLockTime}" title="只允许输入零或正整数,账号锁定后,自动解除锁定的时间,单位: 分钟,0表示永久锁定" />
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<th>
						发件人邮箱: 
					</th>
					<td>
						<input type="text" id="smtpFromMail" name="setting.smtpFromMail" class="formText" value="${setting.smtpFromMail}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						SMTP服务器地址: 
					</th>
					<td>
						<input type="text" id="smtpHost" name="setting.smtpHost" class="formText" value="${setting.smtpHost}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						SMTP服务器端口: 
					</th>
					<td>
						<input type="text" id="smtpPort" name="setting.smtpPort" class="formText" value="${setting.smtpPort}" title="默认端口为25" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						SMTP用户名: 
					</th>
					<td>
						<input type="text" id="smtpUsername" name="setting.smtpUsername" class="formText" value="${setting.smtpUsername}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						SMTP密码: 
					</th>
					<td>
						<input type="password" id="smtpPassword" name="setting.smtpPassword" class="formText" title="留空则不进行密码修改" />
					</td>
				</tr>
				<tr>
					<th>
						邮箱配置测试: 
					</th>
					<td>
						<span id="smtpToMailWrap" class="hidden">
							<div>收件人邮箱: </div>
							<input type="text" id="smtpToMail" name="smtpToMail" class="formText" />
						</span>
						<input type="button" id="smtpTest" class="formButton" value="邮箱测试" hidefocus />
						<span id="smtpTestStatus"></span>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>