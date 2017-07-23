<html>
<head><title>模板中的流程控制</title><head>
<body leftmargin="0" topmargin="0">
<table border="0" align="center">
{assign var="tbColor" value="green"}
{section name=loop loop=$record}
{if $tbColor == "green"}
<tr bgcolor="{$tbColor}">
{assign var="tbColor" value="orange"}
{else $tbColor == "orange"}
<tr bgcolor = "{$tbColor}">
{assign var="tbColor" value="green"}
{/if}
<td><font size="2">{$record[loop].recordId}</font></td>
<td><font size="2">{$record[loop].content}</font></td>
<tr>
{/section}
</table>
</body>
</html>