<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head>
<title>表单传递参数示例</title>
<meta name="generator" content="editplus">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">
</head>

<body>
<table>
	<tr>
		<td>
			<form name='f' action='formparas2.php' method='post'>
				<p>用户名：<input type='text' name='name' value='' size='12' maxlength='12'></p>
				<p>用户密码：<input type='password' name='pwd' value='' size='12' maxlength='12'></p>
				<p>性别：<input type='radio' name='sex' value='1'>男&nbsp;&nbsp;&nbsp;&nbsp;<input type='radio' name='sex' value='2'>女</p>
				<p>工作单位性质：
					<select name='jobs' size='1'>
						<option value='国企' selected>国企</option>
						<option value='私企' selected>私企</option>
						<option value='外企' selected>外企</option>
						<option value='合资' selected>合资</option>
						<option value='公益' selected>公益</option>
					</select>
				</p>
				<p><input type='submit' name='submit' value='提交'></p>
				<p>&nbsp;</p>
			</form>
		</td>
	</tr>
</table>
</body>
</html>