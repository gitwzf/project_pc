
	   <TABLE border=0 cellPadding=0 cellSpacing=0 width=100%>
   		<form action=ordersong_log.php method=post onsubmit="if(this.keyword.value == '') { alert('�ؼ��ֲ���Ϊ��'); return false;}">
        <TR>
          <TD colSpan=4 height=22 align=left>
           �� <input type=radio name=type value='sender' <?php if($type=='sender') echo 'checked'; else $type='receiver';?>>����� <input type=radio name=type value='receiver' <?php if($type=='receiver') echo 'checked';?>>�ո��� ����</TD></TR>
        <TR>
		<TD width=100%><input type=text name=keyword size=18 maxlength=30 value='<?php echo $keyword;?>' onfocus=this.select() class=input>
		<input type=hidden name=search value='keyword'>
		<input type=submit name=submit value=���� class=button onfocus=this.blur()>(����all��ʾ����)
		</TD>
		</form>
   		<form action=ordersong_log.php method=post onsubmit="if(this.day.value == '') {alert('���ڲ���Ϊ��'); return false;}">
		</TR>
        <TR><TD colSpan=4 height=22 align=left>
           �����ʱ������</TD></TR>
        <TR>
		<TD width=100%>����
		 <?php 
		 echo "\t\t\t<select name=year size=1>\n";
		 $j = intval(date(Y));
 		 for($i=2001;$i<=$j;$i++) {
			 $s = "\t\t\t\t<option value='$i'";
			 if($i == $j) $s .= " selected";
			 $s .= ">$i</option>\n";
			 echo $s;
		 }
		 echo "\t\t\t</select>��\n\t\t\t<select name=month size=1>\n";

		 $j = intval(date(m));
 		 for($i=1;$i<=12;$i++) {
			 $s = "\t\t\t\t<option value='$i'";
			 if($i == $j) $s .= " selected";
			 $s .= ">$i</option>\n";
			 echo $s;;
		 }
		 echo "\t\t\t</select>��\n";
		 ?>
		<input type=text name=day value='<?php echo date(d);?>' size=2 onfocus=this.select()>
		<input type=hidden name=search value='date'>
		<input type=submit name=submit value=���� class=button onfocus=this.blur()>
		</TD>
		</TR>
    	</form>
	   </TABLE>
