/// <reference path="jquery-1.7.2.min.js" />

(function ($) {
    var touclick =window.TouClick || {};

    //遮罩函数 输入1 弹出 输入0  消失
    touclick.mask = function (e) {
        var mask = $('mask');
        if (e) {
            mask.css({ display: 'block' });
        } else {
            mask.css({ display: 'none' });
        }
        return this;
    }
    //弹出框控制函数  输入文本内容弹出， 输入0 或 不带参数 则消失
    touclick.float = function (text) {
        var float = $('float');
        if (text) {
            float.css({ 'display': 'block' }).children('p').text(text);
        } else {
            float.css({ 'display': 'none' })
        }
        return this;
    }

    //添加工作经历
    touclick.work_exper = function (e) {
        var form = $('#add_table_form');
        var table = $('.add_table')[0];
        if (e) {
            //点击添加工作经历

            
            var client_dom = $(table).clone().css('display', 'block');
            client_dom.find('input').each(function (i, v) {
                var that = $(v);
                var name = that.attr('name');
                if (name === 'name-index') {
                    that.val(touclick.work_exper_index);//序号赋值
                    return;
                }
           //     that.attr('name', name + touclick.work_exper_index);

            });
            form.append(client_dom);
            touclick.work_exper_index++;//序号自增
            touclick.work_exper_status++;
        } else {
            touclick.work_exper_status--;
        }
    }
    //保存是否弹出添加工作的表单 状态值 计数器，0  代表没有弹出工作经历的表单
    touclick.work_exper_status = 0;
    touclick.work_exper_index = 0;
    //保存添加工作  中的字段 值
    touclick.work_exper_data = {};

   
    
    



    window.TouClick = touclick;
})(jQuery);