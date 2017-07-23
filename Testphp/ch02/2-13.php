<?php
class Cart{
    var $items;  //当前购物车中的物品和数目
    // 把物品放入购物车中
    function add_item ($name, $num){
        $this->items[$name] += $num;
    }
    // 把物品从购物中取出
    function remove_item ($name, $num){
        if ($this->items[$name] > $num) {
            $this->items[$name] -= $num;
            return true;
        } else {
            return false;
        }
    }
}
$mycart = new Cart();
$item0_name = "PHP网络编程";
$item0_num = 2;
$mycart->add_item($item0_name, $item0_num);
$item1_name = "MySQL网络编程指南";
$item1_num = 1;
$mycart->add_item($item1_name, $item1_num);
print_r($mycart->items); //打印输出第一次操作的结果
$mycart->remove_item($item0_name,1);
print_r($mycart->items); //打印输出第二次操作的结果
?>
