# jetbrains-products-crack-tool

## 简介
基于ja-netfilter破解jetbrains旗下的产品，自定义激活时限的激活码。
源码来自网络，我只是整理，原地址：[Idea 激活整理](https://linux.do/t/topic/9132?filter=summary)。可支持idea,pycharm等，
只验证了idea和pycharm，其他jetbrains产品因为我没安装所以未知，可自行验证，不行可调整LicenseGenerator入参中的code。

## 使用
### 1.下载ja-netfilter目录下的jetbra.zip
jetbra.zip 就是ja-netfilter的压缩包，解压到本地，找个地方保存起来。如果你之前使用过，先运行scripts目录下的
uninstall-all-users.vbs，稍等一会，会有一个提示语为Done的弹窗。然后执行同目录下的install-all-users.vbs或者
install-current-user.vbs，同样等待一个提示语为Done的弹窗。

### 2.运行CertificateGenerator生成证书
如果出现错误，把ca.key和ca.crt加上你本地磁盘的地址，比如D:\\ca.key和D:\\ca.crt

### 3.运行PowerConfRuleGen生成power.conf节点数据
把生成的数据写入到第1步的解压文件，config-jetbrains目录下的power.conf文件的[Result]节点下，如果该节点下有数据，删掉它。
power.conf 内容如下：
```
[Result]
EQUAL,445723476679.....这个就是PowerConfRuleGen生成的数据
```

### 4.运行LicenseGenerator拿到激活码
如果出错，记得把读取ca.key和ca.crt的地方替换成你第2步设置的路径，拿到激活码去idea以激活码的形式激活。如果提示 Specified activation code is not applicable to this product,则是入参中的codes不对，可以在网上找激活码，提取其中的code。 比如这个网页提供的激活码：[Pycharm激活码](https://www.bilibili.com/read/cv34966236/?jump_opus=1),以“-”分隔的第二段里就有code，用base64解码，解码后是一个json字符串，再格式化一下，如下,其中products中的那些code对应的值，就是 需要的code,整理出来再运行LicenseGenerator

 ```
 {
     "licenseId": "9H1390TRAK",
     "licenseeName": "永久激活 www·ajihuo·com",
     "assigneeName": "",
     "assigneeEmail": "",
     "licenseRestriction": "",
     "checkConcurrentUse": false,
     "products": [
         {
             "code": "DPN",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "DB",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "PS",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "II",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RSC",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "GO",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "DM",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RSF",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PC",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RC",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "CL",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "WS",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RD",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RS0",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RM",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "AC",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RSV",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "DC",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "RSU",
             "paidUpTo": "2021-11-21",
             "extended": false
         },
         {
             "code": "DP",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PDB",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PWS",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PSI",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PCWMP",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PPS",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PGO",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PPC",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PRB",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "PSW",
             "paidUpTo": "2021-11-21",
             "extended": true
         },
         {
             "code": "RS",
             "paidUpTo": "2021-11-21",
             "extended": true
         }
     ],
     "metadata": "0120211022PPAM000005",
     "hash": "27188224/0:-130578208",
     "gracePeriodDays": 7,
     "autoProlongated": false,
     "isAutoProlongated": false
 }
 ```
 
 ### 5.直接使用的版本
 如果不想跑上面的代码，可以直接使用ja-netfilter下的压缩包文件。
 - jetbra.zip 配合https://3.jetbra.in/ 提供的激活码使用，默认显示的激活期限是2年；
 - jetbra-100.zip直接使用“激活码.txt”里的激活码，默认激活期限100年。
 - 使用说明参考压缩包里的“说明.docx”
 

