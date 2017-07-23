<html>
<head>
<title>一行输出多条记录</title>
</head>
<body>
<table>
<tr>
{section name=loop loop=$record step=1}
{if $smarty.section.loop.index % 5==0}
</tr>
<tr>
{/if}
<td><font size="2">{$record[loop].recordId}</font></td>
<td><font size="2">{$record[loop].content}</font></td>
{/section}
</tr>
</table>
</body>
</html>