<?php
class Cart{
    var $items;  //��ǰ���ﳵ�е���Ʒ����Ŀ
    // ����Ʒ���빺�ﳵ��
    function add_item ($name, $num){
        $this->items[$name] += $num;
    }
    // ����Ʒ�ӹ�����ȡ��
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
$item0_name = "PHP������";
$item0_num = 2;
$mycart->add_item($item0_name, $item0_num);
$item1_name = "MySQL������ָ��";
$item1_num = 1;
$mycart->add_item($item1_name, $item1_num);
print_r($mycart->items); //��ӡ�����һ�β����Ľ��
$mycart->remove_item($item0_name,1);
print_r($mycart->items); //��ӡ����ڶ��β����Ľ��
?>
