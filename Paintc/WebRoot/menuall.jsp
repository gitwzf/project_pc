<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <div class="footer_menu">
          <ul>
            <li class="dropup">
            <span>书画社</span>
            <dl class="submenu">
              <dd><a href="../Paintc/login.jsp">家长登陆</a></dd>
              <dd><a href="../Paints/login.jsp">教师端登陆</a></dd>
              <dd><a href="../Paintc/HonorRoll">书画之星</a></dd>
              <dd><a href="../Paintc/Allwork">快乐展厅</a></dd>
              <dd><a href="../Paintc/aboutsignup.jsp">报名须知</a></dd>
              <div class="angle3"></div>
            </dl>
            </li>                           
            <li class="dropup">
            
            <span>爱童星 </span>
            <dl class="submenu">
              <dd><a href="../Paintc/loading_model.jsp">模特报名</a></dd>
              <dd><a href="../Paintc/modellist.jsp">投票 </a></dd>
              <dd><a href="../Paintc/aboutvote.jsp">赛程说明</a></dd>
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
