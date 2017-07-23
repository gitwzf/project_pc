<?php
if (isset($_POST['action']) && $_POST['action'] == 'bookinfo') {
    echo '<pre>';
    print_r($_POST);
    echo '<a href="'. $_SERVER['PHP_SELF'] .'">请重新获取！</a>';
    echo '</pre>';
} else {
?>
<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
    标题: <input type="text" name="book[title]"><br>
    作者: <input type="text" name="book[author]"><br>
    章节: <br>
    <select multiple name="chapters[]">
        <option value="ch01">第1章 基本环境的配置</option>
        <option value="ch02">第2章 语法结构和常用函数</option>
        <option value="ch08">第8章 用户会话管理</option>
    </select><br>
    <input type="hidden" name="action" value="bookinfo">
    <input type="submit" name="submit" value="获取信息">
</form>
<?php
}
?>