<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.wzf.pubvari.Variable" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%><!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ECharts">
    <meta name="author" content="linzhifeng@baidu.com">
    <title>ECharts · Example</title>

    <link rel="shortcut icon" href="./asset/ico/favicon.png">

    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="./asset/css/bootstrap.css" rel="stylesheet">
    <link href="./asset/css/carousel.css" rel="stylesheet">
    <link href="./asset/css/echartsHome.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="./asset/js/esl/esl.js"></script>
    <script src="./asset/js/codemirror.js"></script>
    <script src="./asset/js/javascript.js"></script>

    <link href="./asset/css/codemirror.css" rel="stylesheet">
    <link href="./asset/css/monokai.css" rel="stylesheet">
    <style type="text/css">
        .CodeMirror {
            height: 550px;
        }
    </style>
</head>

<body>
    <!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation" id="head"></div>


    <div class="container-fluid">
        <div class="row-fluid example">
            <div id="sidebar-code" class="col-md-4">
                <div class="well sidebar-nav">
                    <div class="nav-header"><a href="#" onclick="autoResize()" class="glyphicon glyphicon-resize-full" id ="icon-resize" ></a>option</div>
                    <textarea id="code" name="code">
option = {
    title : {
        text: '微信用户全国分布',
        subtext: '测试',
        x:'center'
    },
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['粉丝数']
    },
    dataRange: {
        min: 0,
        max: 2500,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true
    },
    toolbox: {
        show: true,
        orient : 'vertical',
        x: 'right',
        y: 'center',
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    roamController: {
        show: true,
        x: 'right',
        mapTypeControl: {
            'china': true
        }
    },
    series : [
        {
            name: '粉丝数',
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:<%=Variable.str_province %>
        }
    ]
};
                    </textarea>
              </div><!--/.well -->
            </div><!--/span-->
            <div id="graphic" class="col-md-8">
                <div id="main" class="main" style="height: 530px;"></div>
                <div>
                    <button type="button" class="btn btn-sm btn-success" onclick="refresh(true)">刷 新</button>
                    <span class="text-primary">切换主题</span>
                    <select id="theme-select"></select>

                    <span id='wrong-message' style="color:red"></span>
                </div>
            </div><!--/span-->
        </div><!--/row-->
        
        </div><!--/.fluid-container-->

    <footer id="footer"></footer>
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="./asset/js/jquery.min.js"></script>
    <script type="text/javascript" src="./asset/js/echartsHome.js"></script>
    <script src="./asset/js/bootstrap.min.js"></script>
    <script src="./asset/js/echartsExample.js"></script>
</body>
</html>
