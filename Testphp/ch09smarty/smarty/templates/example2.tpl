<html>
<head><title>smarty示例2</title></head>
<body>
1. 第一句把cHengWeitS@163.com全部变为小写：<{$str1|lower}><br>
2. 第二句首字母要大写：<{$str2|capitalize}><br>
3. 第三句模板变量 + 程伟：<{$str3|cat:"程伟"}><br>
4. 第四句把变量中的chengweits替换成：程伟：<{$str4|replace:"chengweits":"程伟"}><br>
5. 第五句要让它缩进5个空白字母位，并使用"*"取替这5个空白字符：<{$str5|indent:5:"*"}><br>
6. 第六句输出当前日期：<{$str6|date_format:"%Y-%m-%d"|cat:"日期"}><br>
7. 第七句.php程序中不处理，它显示默认值：<{$str8|default:"没有值！"}>
</body>
</html>