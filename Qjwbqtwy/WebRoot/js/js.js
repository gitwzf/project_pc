    /// <reference path="jquery-1.7.2.min.js" />
/// <reference path="jslib.js" />

$(function () {
    //绑定弹出框的确定事件，点击确定按钮浮动框与 背景遮罩 同时消失
    $('float .but').on('click', function () {
        TouClick.mask(0).float();
    });
    
    //工作经历 添加工作经验 的相关代码
    //点击添加，显示输入框
    $('#work_exper_add').on('click', function () {
        TouClick.work_exper(1);//显示
    });
    //移除工作经历
    $('.gzjl-close').on('click', function () {
        $(this).parents('.table').remove();
        //$('#work_exper_save').css('display','none');
        TouClick.work_exper(0);
    });
    
    $('.gzjl-close-a').on('click', function () {
        $(this).parents('form').hide();
        $('#work_exper_save').css('display','none');
        TouClick.work_exper(0);
    });
    
    //保存添加工作经历
    $('#work_exper_save').on('click', function () {
        var form = $('#add_table_form');
      //  if (!TouClick.work_exper_status) {
            //没有点击添加
       //     return;
      //  }
        var start_time, end_time;
        form.find('input').each(function (i, v) {
            var that = $(v);
            var val = that.val();
            if (!val.trim()) {
                TouClick.mask(1).float('请填写完整');
                return;
            }
            if (that.attr('data-role') === 'datebox') {
                if (that.attr('id') === 'start_time') {
                    start_time = val;
                } else {
                    end_time = val;
                }
            }
        });
     //  start_time=null;alert(start_time);
        if (!start_time || !end_time) {
        	return;
        }
        if ( start_time.valueOf() > end_time.valueOf()) {
            TouClick.mask(1).float('开始时间不可以大于结束时间');
            return;
        }
        //校验完毕 提交  to峰哥：填写form表单的action地址  start-time 、end-time 在服务器段接受到的是 2014-05-06 形式的字符串
        //submit action地址 最好就是本来的页面地址，这样 form submit 后，不需要做过多处理
 //  $('#add_table_form').submit();
        $.post("Workexperi",{companyname:$('#companyname').val(),department:$('#department').val(),position:$('#position').val(),
        	salary:$('#salary').val(),info:$('#info').val(),beg_tim:$('#start_time').val(),end_tim:$('#end_time').val()},function(data){
        		if(data=='11'){location.href="./Workexperi";}
        		else if(data=='00')TouClick.mask(1).float('添加失败');
        		
        	});
//        location.href="./Workexperi"
    });

    //我的简历 性别选择
    $('.tr-sex div.td').on('click', function () {
        var that = $(this);
        var val = that.data('value');
        $('.tr-sex div.td').removeClass('active');
        that.addClass('active');
        $('#tr-sex-input').val(val);
    });

    //初始化性别
    $('.tr-sex div.td[data-value=' + $('#tr-sex-input').val() + ']').addClass('active');

    //我的简历提交按钮点击事件
    $('#my_resume_save').on('click', function () {
        var form = $('#my_resume_form');
        var is_suc = false;
        form.find('input').each(function (i, v) {
            var that = $(v);

            var val = that.val();
            if(that.attr("name")=='real_nam'||that.attr("name")=='telphone'||that.attr("name")=='email')
            if (!val.trim()) {
                TouClick.mask(1).float('请完整填写表单');
                is_suc = true;
                return;
            }


        });
        // 说明有不合理项
        if (is_suc) {
            return;
        }
        // 提交表单，也可以采用 $.post 实现，或者是使用 jQuery.form.js 插件实现
    // form.submit();
        $.post("Resume",{real_nam:$('#real_nam').val(),gender:$('#tr-sex-input').val(),
        	birthday:$('#birthday').val(),school:$('#school').val(),gradu_tim:$('#gradu_tim').val(),
        	years:$('#years').val(),salary:$('#salary').val(),expectsalary:$('#expectsalary').val(),
        	degree:$('#degree').val(),major:$('#major').val(),address:$('#address').val(),
        	self_evaluate:$('#self_evaluate').val(),telphone:$('#telphone').val(),
        	email:$('#email').val()},function(data){
        	 if(data=='11')TouClick.mask(1).float('添加成功');
        	 else if(data=='00')TouClick.mask(1).float('添加失败');
        });
    });


    //行业分类的背景图处理
//  $('.flex').each(function (i, v) {
//  var that = $(v);
//  var img = new Image();
//  img.src = '${modelpics[0]}';
//  img.width="90";
//  img.className = "flex-img";
//
//  that.append(img);
//});
$('.clicktimes').click(function(){
  var id=$(this).attr("adid");
  $.post("AdvServ",{id:id});
});

});