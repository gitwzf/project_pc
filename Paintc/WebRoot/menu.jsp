<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <div class="footer_menu">
          <ul>
            <li class="dropup">
            <span>我的作品</span>
            <dl class="submenu">
            <dd><a href="./adeclarative.jsp">书画社简介</a></dd>
              <dd><a href="Worklist?type=left">未评价作品</a></dd>
              <dd><a href="Worklist?type=right">已评价作品</a></dd>
              <dd><a href="./loading.jsp">上传作品</a></dd>
              <div class="angle3"></div>
            </dl>
            </li>                           
            <li class="dropup">
            
            <span>个人中心 </span>
            <dl class="submenu">
              <dd><a href="./Login?action=getout">退出</a></dd>
              <dd><a href="./changepassword.jsp">修改密码 </a></dd>
              <dd><a href="./Editpersonal">个人信息</a></dd>
              <dd><a href="./Buy">缴费充值</a></dd>
              <dd><a href="Personalcenter">账目明细</a></dd>
            <div class="angle3"></div>  
            </dl>
            </li>
          </ul>
        </div>
    </div>
    
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script>
      $(document).ready(function() {
      	  var isup=true;
		  var that;
		  $("li.dropup").click(function () {
		      $('li.dropup').css('background-color', '#fff');
		      var _this = $(this);
		      var val = _this.data('isup') || 0;
		      if (val) {
		          _this.children('dl').slideUp('fast');//收起
		          _this.data('isup', 0);
		      } else {
		          _this.siblings().children('dl').slideUp('fast');//收起兄弟节点
		          _this.siblings().data('isup', 0);
		          _this.children('dl').slideDown('fast');//弹出
		          _this.data('isup', 1);
		          isup = true;
		      }
		      that = _this;
		      _this.css('background-color', '#d9d9d9');

		      that = $(this);
		      isup = true;
		  });
		  $(document).mouseup(function () {
		      if (isup && that) {
		          that.children('dl').slideUp('fast');
		      }
		      isup = false;
		  });
	
      });

    </script>
