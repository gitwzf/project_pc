<?php
require('config.php');
require('public.php');
require('inc/db.class.php');

html_head("�����ļ�");
include('inc/head.inc.php');
now_pos('<a href=index.php>��ҳ</a> ������', '');
?>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 width=760 background='images/bg1.gif'>
<TR><TD width=100% height=100%>
<h3><?echo $site_name; ?>ʹ�ð����ļ��� </h3>
<ul>
  <li><b>���Ұ����¸�ʽ���ظ�����лл<br>
    <br>
    </b>1. ���ص�ַ: <a href=ftp://10.11.11.11/music/>ftp://10.11.11.11/music/ </a> ���Խ�Ŀ¼������ɾ����<br>
    <br>
    2.  ���ع淶: <br>
    <br>
    �밴ר�����أ�����Ҫ����ɢ�裡лл <br>
    ����������������� <br>
    �����ר������ͼƬ��ר����飬��һ�����ػ����˵����<br>
    <br>
    <br>
  </li>
  <li><b>����������֣�</b><br>
    <br>
    �������� �����ز���װ <a href=util/rp8-cn-setup.exe>RealPlayer</a>(5.15MB) ���, ����ʾ��װ�ɹ���, 
    �����ڱ�վ��������! <br>
    <br>
  </li>
  <li><b>��¼ʱ��"��ס����"��ʲô��˼��</b><br>
    <br>
    ������õ���˽�˻���, ��ô���, ������ʹ���رջ���, �ٴ򿪱�վʱ, �����������µ�¼, ������վ����������������, �����!
	<br>
	<br>
	����ǹ��û���, Ϊ�˰�ȫ���, �벻Ҫѡ�����!
    <br>
  </li>
  <li><b><a name=mem>��γ�Ϊע���Ա����Ա����Щ��ֵ����</a><br>
    <br>
    </b>1. �㰴��ҳ�� 
    <input class=button name=Button onClick="window.open('user_register.php','register','width=330,height=400,top=20,left=20')" type=button value=ע�� target="_blank">
    , �������, ȷ�������ύ�ɹ���, ����Ϊ��վע���Ա. <br>
    <br>
    2. ע��Ϊ��վע���Ա, �����������·���: <br>
    <br>
    ��ʹ�� �ҵĲ����б� <br>
    ��ʹ�� �ҵ�ר����ѡ <br>
    �ɶԸ����������� <br>
    ���� �������� ����ظ����� <br>
    ��ʹ�õ���͸蹦��, ����ϵͳ���Զ��������� ���� �� E-mail<br>
    ���Խ����������������. <br>
    <br>
  </li>
  <li><b>���ʹ�� &quot;�ҵĲ����б�&quot; �� &quot;�ҵ�ר����ѡ&quot;?</b><br>
    <br>
    ע���¼�󣬸�����ʾ��� &quot;�ղ�&quot; ���ɡ�ÿ���������100��ר����ѡ��100�����������¼��<br>
    �������ҵĹ�����(��¼�������)���Ǩ�������Լ�����ϲ���ĸ�����<br>
    <br>
  </li>
  <li><b>���ʹ�� &quot;�������&quot; ?<br>
    <br>
    </b>ע���¼���ڸ����б��ϵ�� &quot;���&quot; �����ˡ�������������ʾ����<br>
    <br>
  </li>
  <li><b>Ϊʲô�Ҳ��� &quot;�޸ĸ��&quot; , ˵�ҵ�Ȩ�޵ȼ�����?<br>
    <br>
    </b>��¼��վ��Ϊ��ȫ���Ҫ�����ľ���ֵ������ 450 �����ϻ����Ǳ�վվ�������޸ĸ�ʣ���л���Ա�վ�ĺ񰮡�<br>
    <br>
  </li>
  <li><b>��������ʱ֧��HTMLô? ��ΪBBcode ?<br>
    <br>
    </b>�������»���������������<font color="#FF0000">��֧��HTML�﷨</font>����֧��<font color="#FF0000">BBcode</font>,bbcode��������һЩ�򵥵�HTML<br>
    �﷨�������÷����£� <br>
    <br>
  </li>
  <ol type=1>
    <li>url֧�֣�<br>
      �÷�1. <b>[url]</b>������ӵ�ַ<b>[/url]</b><br>
      ʾ��:<b>[url]</b>http://cheng.yeah.net<b>[/url]</b>�� ��ʾ<a href="http://cheng.yeah.net" target=_blank>http://cheng.yeah.net</a><br>
      <br>
      �÷�2. <b>[url=������ӵ�ַ]</b>������ӱ���<b>[/url]</b><br>
      ʾ��:<b>[url=http://cheng.yeah.net]</b>��԰������<b>[/url]</b>�� ��ʾ<a href="http://cheng.yeah.net" target=_blank>��԰������</a><br>
      <br>
      �÷�3. <b>[url]</b>���ӵ�ַ<b>[/url]</b><br>
      ʾ��:<b>[url]</b>cheng.yeah.net<b>[/url]</b>�� ��ʾ<a href="cheng.yeah.net" target=_blank>cheng.yeah.net</a><br>
      <br>
    <li>EMAIL֧��:<br>
      �÷�1. <b>[email]</b>EMAIL��ַ<b>[/email]</b><br>
      ʾ��:<b>[email]</b>cheng@263.net<b>[/email]</b>�� ��ʾ:<a href="mailto:cheng@263.net">cheng@263.net</a><br>
      <br>
      �÷�2. <b>[email=EMAIL��ַ]</b>��ʾ����<b>[/email]</b><br>
      ʾ��:<b>[email=cheng@263.net]</b>cheng<b>[/email]</b>�� ��ʾ:<a href="mailto:cheng@263.net">cheng</a><br>
      <br>
    <li>����֧�֣�<br>
      �÷�1.<b>[b]</b>�Ӵ�����<b>[/b]</b>�� ��ʾ:<b>�Ӵ�����</b><br>
      <br>
      �÷�2.<b>[i]</b>��б����<b>[/i]</b>�� ��ʾ:<i>��б����</i><br>
      <br>
      <br>
  </ol>
  <li> <b>��վ�ľ���ֵ����μ���ģ� �������ҵĵȼ��кι�ϵ�� <br>
    </b><br>
    <font color=red>����ֵ = ��վ����+����������*2+ע������/3</font><br>
	ע�����ͨ�����ṩ�ĸ�ʣ����������Ҳ����1��������2�㾭��ֵ��
   <pre>
		 if($exp < 1) return "�����ο�";

		 else
			 if($exp < 100) return "������·";
		 else
			 if($exp < 450) return "һ�㷢��";
		 else
			 if($exp < 850) return "�м�����";
		 else
			 if($exp < 1500) return "�߼�����";
		 else
			 if($exp < 2500) return "���ϼ�";
		 else 
			 if($exp < 3500) return "Ԫ�ϼ�";
		 else
			 return "��������";
			 </pre>
	����ȼ�Ϊ ����ֵ/150, �羭��ֵ��750��������ȼ�Ϊ5 <br>
	������ 5�����Ͽ�����HTML(img,script�ĳ���)��8�����Ͽ��Կ�IP�������˱������˱�10�����(��վ��ӵ��)���������ˡ����ˡ���Ȩ��

  </li>
  <li> <b>�����������ô��ϵ��<br>
    </b><br>
    �����κν�������<a href="mailto:cheng@263.net?subject=%C0%B4%D7%D4%D2%F4%C0%D6%D5%BE">cheng</a>��ϵ.
  </li>
</ul>
</TD></TR></TABLE>
<?php
include('inc/foot.inc.php');
exit;
?>