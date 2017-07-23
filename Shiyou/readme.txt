①数据库及上传路径修改参看vari.properties文件

②日志
  可编辑log4j.properties文件，修改log4j.appender.logfile.File对应值


③域名（把http://xiaowangzi.touclick.com改为对应的新域名地址）
文件 \WEB-INF\classes》reportconfig》report》shiyou.xml》
3,072: btn="<input type=\"button\" value=\"材料下载\" onclick=\"javascript:location.href='http://xiaowangzi.touclick.com/image/shiyou/"+urlMeterial+"';\">";   
3,168: btn="<input type=\"button\" value=\"材料下载\" onclick=\"javascript:location.href='http://xiaowangzi.touclick.com/image/shiyou/"+urlMeterial+"';\">";   

文件webresources》test.js》
110: $('body').append("<div id='pic' style='position: fixed;'><img src='http://xiaowangzi.touclick.com/image/"+obj.attributes["showname"].value+"/"+obj.value+"'/></div>");  