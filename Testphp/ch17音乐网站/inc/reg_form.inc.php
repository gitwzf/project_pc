<?php
//注册表格
html_head("会员注册");
print "
<table border=0 background='images/bg1.gif'>
<form method=post action=$PHP_SELF>
<tr><td bgcolor=$message[3] colspan=2 class=p2>请认真仔细填写以下项目(带<font color=red>*</font>为必填)</td></tr>
<tr><td bgcolor=$message[3]>使用代号 </td><td><input type=text class=input name=user_name value='$user_name' size=13><font color=red>*</font>(可以是中文)</td></tr>
<tr><td bgcolor=$message[3]>使用密码 </td><td><input type=password class=input name=passwd size=13><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>重复密码 </td><td><input type=password class=input name=passwd1 size=13><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>电子邮件 </td><td><input type=text class=input name=email size=30 value='$email'><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>您的性别 </td><td><input type=radio name=sex value='葛格'>葛格
<input type=radio name=sex value='美眉' checked>美眉
<input type=radio name=sex value='底迪'>底迪
<input type=radio name=sex value='解结'>解结
<font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>您的年龄 </td><td><input type=text class=input name=yearold size=8 value='$yearold'>周岁</td></tr>
<tr><td bgcolor=$message[3]>OICQ号码 </td><td><input type=text class=input name=oicq size=15 value='$oicq'></td></tr>
<tr><td bgcolor=$message[3]>所在省份 </td><td><select name=province size=1>
        <!--所在省份-->
		<option>请选择</option>
        <option value='北京'>北京</option>
        <option value='上海'>上海</option>
        <option value='天津'>天津</option>
        <option value='重庆'>重庆</option>
        <option value='山西'>山西</option>
        <option value='内蒙古'>内蒙古</option>
        <option value='辽宁'>辽宁</option>
        <option value='吉林'>吉林</option>
        <option value='黑龙江'>黑龙江</option>
        <option value='江苏'>江苏</option>
        <option value='浙江'>浙江</option>
        <option value='安徽'>安徽</option>
        <option value='福建'>福建</option>
        <option value='江西'>江西</option>
        <option value='山东'>山东</option>
        <option value='河北'>河北</option>
        <option value='河南'>河南</option>
        <option value='湖北'>湖北</option>
        <option value='湖南'>湖南</option>
        <option value='广东'>广东</option>
        <option value='广西'>广西</option>
        <option value='海南'>海南</option>
        <option value='四川'>四川</option>
        <option value='贵州'>贵州</option>
        <option value='云南'>云南</option>
        <option value='陕西'>陕西</option>
        <option value='甘肃'>甘肃</option>
        <option value='宁夏'>宁夏</option>
        <option value='青海'>青海</option>
        <option value='新疆'>新疆</option>
        <option value='西藏'>西藏</option>
        <option value='香港'>香港</option>
        <option value='澳门'>澳门</option>
        <option value='台湾'>台湾</option>        
        <option value='其他'>其他</option>        
</select><font color=red>*</font></td></tr>
<tr><td bgcolor=$message[3]>详细地址 </td><td><input type=text class=input name=addr value='$addr' size=30></td></tr>
<tr><td bgcolor=$message[3]>你的主页</td><td><input type=text class=input name=homepage size=30 value='http://'></td></tr>
<tr><td bgcolor=$message[3]>个人签名 </td><td><textarea class=input name=sign cols=35 rows=3>$sign</textarea></td></tr>
<tr><td bgcolor=$message[3] colspan=2 align=center>
<input type=submit name=submit value=提交 class=button>
<input type=reset value=重写 class=button></td></tr>
</form>
</table>
<br>
<center>$message[0]</center>
</body></html>
";