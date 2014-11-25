你好啊，${user}，今天你的精神不错！
-----------------------------------
                           测试if语句
*************************
<#if user=="老高">
   真的是老高啊
</#if>
**************************
<#if random gte 60>
   及格咯
 <#else>
    不及格咯
</#if>
**************************
<#if random gte 90>
    及格
 <#elseif random gte 80>
    良好
 <#elseif random gte 60>
    及格
 <#else>
     不及格
</#if>
-----------------------------------
                                  测试list指令：
<#list addresses as address >
	<b>${address.country}</b> <br/>
</#list>
-----------------------------------
       测试include指令：
<#include "included.txt"/>              
-----------------------------------
自定义指令(macro指令)
<#macro m1>   <#-- 定义指令m1 -->
	<b>第一行</b>
	<b>第二行</b>
</#macro>
<#--调用上面的宏指令 -->
<@m1 />  
<@m1 />  
**************************
定义带参的宏指令：
<#macro m2 a b c >
	${a}--${b}--${c}
</#macro>
<@m2 a="老高" b="老张" c="老马" />
**************************
nested指令：
<#macro border> 
  <table border=4 cellspacing=0 cellpadding=4><tr><td> 
    <#nested> 
  </td></tr></table> 
</#macro>
<@border>表格中的内容！</@border>
-----------------------------------
测试命名空间：
<#import "b.ftl" as bb  />
<@bb.copyright date="2010-2011" />
${bb.mail}
<#assign mail="my@163.com"  />
${mail}
<#assign mail="my@163.com" in bb  />
${bb.mail}
-----------------------------------
测试数据类型
<#assign b="sss">
${date1?string("yyyy-MM-dd HH:mm:ss")}
-----------------------------------
测试内建函数
${htm2?html}
-----------------------------------
测试空值处理：
<#-- ${sss} 没有定义这个变量，会报异常！ -->
${sss!} <#--没有定义这个变量，默认值是空字符串！ -->
${sss!"abc"} <#--没有定义这个变量，默认值是字符串abc！ -->
