
<TABLE border=0 cellPadding=3 cellSpacing=0 width=100% bgcolor="<?php echo $message[2];?>">
<TBODY>
<TR bgcolor="<?php echo $message[14];?>" align=center>
 <TD colSpan=2 height=18 align=center valign=bottom>
  <font color=#E7E7E7>:::</font> <font color=#FFFFFF>�� Ա �� ѯ</font> <font color=#E7E7E7>:::</font>
</TD></TR>
<tr><form action=user_query.php onsubmit="return QueryUser(this.user_name.value);" name=queryform><td>
���ţ�<input type=text name=user_name size=10 maxlength=20 class=input>
<input type=submit name=submit value=Go class=button onclick=this.blur()><br>
<br><a href=user_query.php target=_blank>��ʾ���л�Ա?</a>
</TD></form></TR>
</TBODY></TABLE>