<?php
if (isset($_POST['action']) && $_POST['action'] == 'bookinfo') {
    echo '<pre>';
    print_r($_POST);
    echo '<a href="'. $_SERVER['PHP_SELF'] .'">�����»�ȡ��</a>';
    echo '</pre>';
} else {
?>
<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
    ����: <input type="text" name="book[title]"><br>
    ����: <input type="text" name="book[author]"><br>
    �½�: <br>
    <select multiple name="chapters[]">
        <option value="ch01">��1�� ��������������</option>
        <option value="ch02">��2�� �﷨�ṹ�ͳ��ú���</option>
        <option value="ch08">��8�� �û��Ự����</option>
    </select><br>
    <input type="hidden" name="action" value="bookinfo">
    <input type="submit" name="submit" value="��ȡ��Ϣ">
</form>
<?php
}
?>