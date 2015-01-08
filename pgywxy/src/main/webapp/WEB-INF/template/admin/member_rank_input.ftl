<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>add/edit��Ա�ȼ�</title>
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/admin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.js"></script>
<script type="text/javascript" src="${base}/template/common/js/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/base.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/admin.js"></script>
<script type="text/javascript">
$().ready(function() {

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $validateForm = $("#validateForm");
	
	// �?��֤
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
					  "memberRank.isDefault": {
				        required: true
			      }
				      ,
					  "memberRank.name": {
				        required: true
			      }
				      ,
					  "memberRank.preferentialScale": {
				        required: true
			      }
				      ,
					  "memberRank.score": {
				        required: true
			      }
		},
		messages: {
		      
		      
		      
		      
					  "memberRank.isDefault": {
				        required: "isDefault is required"
			      }
				      ,
		      
					  "memberRank.name": {
				        required: "name is required"
			      }
				      ,
		      
					  "memberRank.preferentialScale": {
				        required: "preferentialScale is required"
			      }
				      ,
		      
					  "memberRank.score": {
				        required: "score is required"
			      }
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
		<#if isAddAction>add<#else>edit</#if>
	</div>
	<div id="validateErrorContainer" class="validateErrorContainer">
		<div class="validateErrorTitle">The following information is filled in incorrectly, please re-fill</div>
		<ul></ul>
	</div>
	<div class="body">
		<form id="validateForm" action="<#if isAddAction>member_rank!save.action<#else>member_rank!update.action</#if>" method="post">
			<table class="inputTable">
				<input type="hidden" name="id" value="${id}" />
				    <tr>
					   <th>
						  isDefault: 
					   </th>
					   <td>
						  <input type="text" name="memberRank.isDefault" class="formText" value="${(memberRank.isDefault)!}" />
						  <label class="requireField">*</label>
					   </td>
				    </tr>
				    <tr>
					   <th>
						  name: 
					   </th>
					   <td>
						  <input type="text" name="memberRank.name" class="formText" value="${(memberRank.name)!}" />
						  <label class="requireField">*</label>
					   </td>
				    </tr>
				    <tr>
					   <th>
						  preferentialScale: 
					   </th>
					   <td>
						  <input type="text" name="memberRank.preferentialScale" class="formText" value="${(memberRank.preferentialScale)!}" />
						  <label class="requireField">*</label>
					   </td>
				    </tr>
				    <tr>
					   <th>
						  score: 
					   </th>
					   <td>
						  <input type="text" name="memberRank.score" class="formText" value="${(memberRank.score)!}" />
						  <label class="requireField">*</label>
					   </td>
				    </tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="confrim" hidefocus />&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="back" hidefocus />
			</div>
		</form>
	</div>
</body>
</html>