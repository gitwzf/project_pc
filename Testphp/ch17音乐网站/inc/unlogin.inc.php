
	  <script language=javascript>
	   function dosub(){
			  if(window.form1.user_tmpid.value == '' || window.form1.user_tmppass.value == ''){
				  alert('����д������');
				  return false;
			  }
	   }
	  </script>
	  <TABLE border=0 cellPadding=2 cellSpacing=0 width=100% bgcolor="<?php echo $message[2];?>">
        <TBODY>
        <TR bgcolor="<?php echo $message[14];?>" align=center>
          <TD colSpan=2 height=18 align=center valign=bottom>
		   <font color=#E7E7E7>:::</font> <font color=#FFFFFF>�� Ա �� ¼</font> <font color=#E7E7E7>:::</font>
		  </TD></TR>
        <FORM action=user_login.php name=form1 method=post target=_self onsubmit='return dosub();'>
        <TR>
          <TD  width=35%>�� ��:</TD>
          <TD width=65%><INPUT class=input name=user_tmpid 
            onfocus=this.select() size=12 value="<?php echo @$m_user_tmpid1; ?>"> 
          </TD></TR>
        <TR>
          <TD width=35%>�� ��:</TD>
          <TD width=65%><INPUT class=input name=user_tmppass
            onfocus=this.select() size=12 type=password>
		  </TD></TR>
        <TR>
          <TD colspan=2 align=center>
		  ��ס��¼���룿<INPUT type=checkbox name='__keep' value='on' onclick=this.blur()>
		  </TD></TR>
        <TR valign=middle>
          <TD align=center width=100% colspan=2>
          <INPUT class=button type=submit name=submit value="�� ��" onclick=this.blur()>&nbsp;&nbsp;&nbsp;
		  <INPUT class=button name=Button onclick="window.open('user_register.php','register','width=330,height=400,top=20,left=20');this.blur()" type=button value="ע ��" target="_blank"> 
          </TD></TR></FORM>
         <TR> <TD colspan=2 align=center> 
		  <?php echo $message[5];?> <a href=help.php#mem>��Ա����Ȩ��</a>!</TD></TR> 
        </TBODY></TABLE>