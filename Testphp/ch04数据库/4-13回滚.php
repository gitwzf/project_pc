<?php
  	//�������ݿ�
  	$dbcon = mysqli_connect($host, $user, $pass, $db);
��	//���ȣ��ر��Զ��ύ
��	mysqli_autocommit($dbcon, FALSE);
��	//��������ݿ����
  	$sql1 = "insert into User(id,name) values(NULL,'phpbook')";
��	$result = mysqli_query($dbcon, $sql1);
��	if ($result !== TRUE) {
����		mysqli_rollback($dbcon); //���������ع���ִ��ǰ��״̬
��	}
��	$sql2 = "insert into UserInfo(uid,realname,address) values(302,'PHP������','�����к�����')";
��	$result = mysqli_query($dbcon, $sql2);
��	if ($result !== TRUE) {
����		mysqli_rollback($dbcon); //���������ع���ִ��ǰ��״̬
��	}
��	//�����û�д������ύ����
��	mysqli_commit($dbcon);
��	//�ر����ݿ�����
��	mysqli_close($dbcon);
?>