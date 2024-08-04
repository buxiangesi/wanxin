# 表

### 片区表 T_JC_PQ


<TABLE BORDER="1">
<TR><TH>Name</TH><TH>Virtual</TH><TH>Type</TH><TH>Nullable</TH><TH>Default/Expr.</TH><TH>Storage</TH><TH>Comments</TH></TR>
<TR><TD>ID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>N</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20027;&#38190;</TD></TR>
<TR><TD>PQBH</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#29255;&#21306;&#32534;&#21495;</TD></TR>
<TR><TD>PQMC</TD><TD>N</TD><TD>VARCHAR2(200)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#29255;&#21306;&#21517;&#31216;</TD></TR>
<TR><TD>ZXBZ</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>0</TD><TD>&nbsp;</TD><TD>&#27880;&#38144;&#26631;&#24535;0&#27491;&#24120;1&#27880;&#38144;</TD></TR>
<TR><TD>BZ</TD><TD>N</TD><TD>VARCHAR2(500)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22791;&#27880;</TD></TR>
<TR><TD>KZ1</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;1</TD></TR>
<TR><TD>KZ2</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;2</TD></TR>
</TABLE>




### 街道表-T_JC_PQ_JD


<TABLE BORDER="1">
<TR><TH>Name</TH><TH>Virtual</TH><TH>Type</TH><TH>Nullable</TH><TH>Default/Expr.</TH><TH>Storage</TH><TH>Comments</TH></TR>
<TR><TD>ID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>N</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20027;&#38190;</TD></TR>
<TR><TD>PQID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#29255;&#21306;ID(&#21442;&#32771;&#29255;&#21306;&#34920;&#30340;&#20027;&#38190;)</TD></TR>
<TR><TD>JDBH</TD><TD>N</TD><TD>VARCHAR2(200)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#34903;&#36947;&#32534;&#21495;</TD></TR>
<TR><TD>JDMC</TD><TD>N</TD><TD>VARCHAR2(200)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#34903;&#36947;&#21517;&#31216;</TD></TR>
<TR><TD>ZXBZ</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>0</TD><TD>&nbsp;</TD><TD>&#27880;&#38144;&#26631;&#24535;0&#27491;&#24120;1&#27880;&#38144;</TD></TR>
<TR><TD>BZ</TD><TD>N</TD><TD>VARCHAR2(500)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22791;&#27880;</TD></TR>
<TR><TD>KZ1</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;1</TD></TR>
<TR><TD>KZ2</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;2</TD></TR>
</TABLE>


### 房产表-houseTable

<TABLE BORDER="1">
<TR><TH>Name</TH><TH>Virtual</TH><TH>Type</TH><TH>Nullable</TH><TH>Default/Expr.</TH><TH>Storage</TH><TH>Comments</TH></TR>
<TR><TD>ID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>N</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20027;&#38190;</TD></TR>
<TR><TD>JDID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#34903;&#36947;ID</TD></TR>
<TR><TD>FCBH</TD><TD>N</TD><TD>VARCHAR2(20)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#20135;&#32534;&#21495;</TD></TR>
<TR><TD>FWZL</TD><TD>N</TD><TD>VARCHAR2(200)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#23627;&#24231;&#33853;</TD></TR>
<TR><TD>CQR</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20135;&#26435;&#20154;</TD></TR>
<TR><TD>CQRSFZ</TD><TD>N</TD><TD>VARCHAR2(18)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20135;&#26435;&#20154;&#36523;&#20221;&#35777;&#21495;&#30721;</TD></TR>
<TR><TD>CQRLXDH</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20135;&#26435;&#20154;&#32852;&#31995;&#30005;&#35805;</TD></TR>
<TR><TD>JZR</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#23621;&#20303;&#20154;</TD></TR>
<TR><TD>JZRLXDH</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#23621;&#20303;&#20154;&#32852;&#31995;&#30005;&#35805;</TD></TR>
<TR><TD>FWCQZ</TD><TD>N</TD><TD>VARCHAR2(100)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#23627;&#20135;&#26435;&#35777;&#21495;</TD></TR>
<TR><TD>TDSYZH</TD><TD>N</TD><TD>VARCHAR2(100)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22303;&#22320;&#20351;&#29992;&#35777;&#21495;</TD></TR>
<TR><TD>TDSYMJ</TD><TD>N</TD><TD>NUMBER(10,2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22303;&#22320;&#20351;&#29992;&#38754;&#31215;&#65288;&#24179;&#26041;&#31859;&#65289;</TD></TR>
<TR><TD>QLLX</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#26435;&#21033;&#31867;&#22411;0&#21010;&#25320;1&#20986;&#35753;</TD></TR>
<TR><TD>QLXZ</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#26435;&#21033;&#24615;&#36136;0&#22269;&#26377;1&#38598;&#20307;</TD></TR>
<TR><TD>SFWZF</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#26159;&#21542;&#26080;&#35777;&#25151;</TD></TR>
<TR><TD>FCCS</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#20135;&#20986;&#21806;0&#20844;&#20135;1&#20010;&#20154;</TD></TR>
<TR><TD>ZXBZ</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>0</TD><TD>&nbsp;</TD><TD>&#27880;&#38144;&#26631;&#24535;0&#27491;&#24120;1&#27880;&#38144;</TD></TR>
<TR><TD>BZ</TD><TD>N</TD><TD>VARCHAR2(500)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22791;&#27880;</TD></TR>
<TR><TD>KZ1</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;1</TD></TR>
<TR><TD>KZ2</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;2</TD></TR>
<TR><TD>FWCB</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#23627;&#20135;&#21035;0&#31169;&#20135;1&#36335;&#20135;</TD></TR>
</TABLE>




### 房屋表-roomTable





<TABLE BORDER="1">
<TR><TH>Name</TH><TH>Virtual</TH><TH>Type</TH><TH>Nullable</TH><TH>Default/Expr.</TH><TH>Storage</TH><TH>Comments</TH></TR>
<TR><TD>ID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>N</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#20027;&#38190;</TD></TR>
<TR><TD>FCID</TD><TD>N</TD><TD>VARCHAR2(50)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#20135;ID&#65288;&#21442;&#32771;&#25151;&#20135;&#30340;id&#65289;</TD></TR>
<TR><TD>FWJS</TD><TD>N</TD><TD>NUMBER(10,2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25151;&#23627;&#38388;&#25968;</TD></TR>
<TR><TD>JZMJ</TD><TD>N</TD><TD>NUMBER(10,2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#24314;&#31569;&#38754;&#31215;&#65288;&#24179;&#26041;&#31859;&#65289;</TD></TR>
<TR><TD>ZXBZ</TD><TD>N</TD><TD>VARCHAR2(2)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#27880;&#38144;&#26631;&#24535;0&#27491;&#24120;1&#27880;&#38144;</TD></TR>
<TR><TD>BZ</TD><TD>N</TD><TD>VARCHAR2(500)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#22791;&#27880;</TD></TR>
<TR><TD>KZ1</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;1</TD></TR>
<TR><TD>KZ2</TD><TD>N</TD><TD>VARCHAR2(4000)</TD><TD>Y</TD><TD>&nbsp;</TD><TD>&nbsp;</TD><TD>&#25193;&#23637;2</TD></TR>
</TABLE>




# 页面

### layout

左侧

`片区`、`街道`、`房产信息`

右侧mainPane：

 `routerview`



### 片区

**header：**

+ 片区编号

+ 片区名称
+ 注销标记
+ 搜素（通过以上关键字）

**mainPane：**

`header button`：添加，修改，删除

`column button`：编辑，删除

`tabular`：片区编号，片区名称，注销标记，备注，`操作`

`操作`：修改，删除

**页面响应：**

1. 点击编辑片区按钮：

​	`action`：弹窗

​	`content`：**片区**名称（radio/select），**片区编号**（textarea），注销标志（radio/select），备注（textfield）

​	`tips`：片区编号已存在时报错

2. 点击添加片区按钮：

   `action`：弹窗

   `content`：**片区编号**（textarea），**片区名称**（textarea），注销标志（radio/select），备注（textfield）

3. 点击删除片区按钮：

​	`action`：成功->显示提升信息

​	`tips`：片区内街道数量>0时，删除片区报错



### 街道

**header：**

+ 街道编号

+ 街道名称
+ 片区名称
+ 搜素（通过以上关键字）

**mainPane：**

`header button`：添加，修改，删除，导出

`column button`：编辑，删除

`tabular`：街道编号，街道名称，片区名称，注销标记，备注，`操作`

`操作`：修改，删除

**页面响应：**

1. 点击编辑片区按钮：

​	`action`：弹窗

​	`content`：**片区**（radio/select），**街道编号**（textarea），**街道名称**（textarea），注销标志（radio/select），备注（textfield）

​	`tips`：街道编号已存在时报错

2. 点击添加片区按钮：

   `action`：弹窗

   `content`：**片区编号**（textarea），**片区名称**（textarea），注销标志（radio/select），备注（textfield）

   

### 房产

**sidebar**：

+ 片区与街道树形图

**header**：

+ 房产编号
+ 产权人
+ 产权人身份证号码
+ 注销标志
+ 搜索

**mainPane**：

`header button`：添加，修改，删除，导出

`column button`：修改，删除

`tabular`：房产编号，所属片区，所属街道，位置，产权人，产权人身份证号码，产权人电话号码，`操作`

`操作`：修改，删除

**页面响应：**

1. 点击添加房产按钮：

​	`action`：弹窗（含子表以添加房屋信息）

​	`content`：**街道名称**（textarea），**房屋产别**（radio/select），**房屋坐落**（textarea），**产权人**（textarea），产权人身份证号（textarea），产权人联系电话（textarea），居住人（textarea），居住人联系电话（textarea），房屋产权证号（textarea），土地使用证号（textarea），土地使用面积（Number Area），权利类型（radio/select），权利性质（radio/select），是否无证房（radio/select），房产出售（radio/select），注销标志（radio/select），备注（textfield）

​	`tips`：街道编号已存在时报错

​	`childTableContent`：**建筑面积**（Number Area），**房屋数量**（Number Area），**注销标志**（radio/select），备注（textfield）

2. 点击修改房产按钮：

​	`action`：弹窗

​	`content`：**街道名称**（textarea），**房屋产别**（radio/select），**房屋坐落**（textarea），**产权人**（textarea），产权人身份证号（textarea），产权人联系电话（textarea），居住人（textarea），居住人联系电话（textarea），房屋产权证号（textarea），土地使用证号（textarea），土地使用面积（Number Area），权利类型（radio/select），权利性质（radio/select），是否无证房（radio/select），房产出售（radio/select），注销标志（radio/select），备注（textfield）

​	`tips`：街道编号和街道名称已存在时报错

   `childTableContent`：**建筑面积**（Number Area），**房屋数量**（Number Area），**注销标志**（radio/select），备注（textfield）

