
<a name="paths"></a>
## 资源

<a name="f1fb59494cef6c843ce8bfda0cea08aa"></a>
### 主页的接口
Home Controller


<a name="getsimplearticlesusingget"></a>
#### 获取首页文章
```
GET /home/articles
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|
|**Query**|**title**  <br>*可选*|title|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/home/articles
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="e09fc49c9998fe150a63dad2b6b78f40"></a>
### 分类相关的接口
Category Controller


<a name="addcategoryusingpost"></a>
#### 添加分类
```
POST /categories
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**category**  <br>*必填*|category|[Category](#category)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "children" : [ {
    "children" : [ "..." ],
    "description" : "string",
    "id" : 0,
    "name" : "string",
    "parentId" : 0
  } ],
  "description" : "string",
  "id" : 0,
  "name" : "string",
  "parentId" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getallcategoriesusingget"></a>
#### 获取所有分类
```
GET /categories
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatecategoryusingput"></a>
#### 修改分类
```
PUT /categories
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**category**  <br>*必填*|category|[Category](#category)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "children" : [ {
    "children" : [ "..." ],
    "description" : "string",
    "id" : 0,
    "name" : "string",
    "parentId" : 0
  } ],
  "description" : "string",
  "id" : 0,
  "name" : "string",
  "parentId" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="gettreeusingget"></a>
#### 查询树状菜单
```
GET /categories/tree
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories/tree
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getchildcategoriesusingget_1"></a>
#### 根据id获取分类
```
GET /categories/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="addcategoryusingdelete"></a>
#### 删除分类
```
DELETE /categories/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getchildcategoriesusingget"></a>
#### 根据父类id获取子类
```
GET /categories/{id}/childCategories
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/categories/0/childCategories
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="c0378ea4d42788cf331c1e24417be76d"></a>
### 友链相关的接口
Friend Controller


<a name="insertfriendusingpost"></a>
#### 添加友链
```
POST /friends
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**friend**  <br>*必填*|friend|[Friend](#friend)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/friends
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "iconUrl" : "string",
  "id" : 0,
  "name" : "string",
  "website" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getfriendsusingget"></a>
#### 获取所有友链
```
GET /friends
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/friends
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatefriendusingput"></a>
#### 更新友链
```
PUT /friends
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**friend**  <br>*必填*|friend|[Friend](#friend)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/friends
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "iconUrl" : "string",
  "id" : 0,
  "name" : "string",
  "website" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getfriendbyidusingget"></a>
#### 通过ID获取指定友链
```
GET /friends/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/friends/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deletefriendusingdelete"></a>
#### 删除友链
```
DELETE /friends/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/friends/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="83188b9a8fb88ee44a182fb06c49c70d"></a>
### 图片上传接口
File Controller


<a name="uploadfileusingpost"></a>
#### 上传图片
```
POST /upload
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**FormData**|**file**  <br>*可选*|file|file|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `multipart/form-data`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/upload
```


###### 请求 header
```
json :
"string"
```


###### 请求 formData
```
json :
"file"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="51b9a7fd8f4411b796d96f445a9226cf"></a>
### 授权相关的接口
Auth Controller


<a name="getcaptchausingget"></a>
#### 获取验证码
```
GET /auth/captcha
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/captcha
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="loginusingpost"></a>
#### 登入接口
```
POST /auth/login
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**loginUser**  <br>*必填*|loginUser|[登入用户](#27bb0c12cfc0bd81a4c201280c67d8aa)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/login
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "captcha" : "string",
  "cid" : "string",
  "password" : "string",
  "rememberMe" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="logoutusingdelete"></a>
#### 注销
```
DELETE /auth/logout
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*必填*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/logout
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="forgetpasswordusingget"></a>
#### 找回密码,发送邮箱
```
GET /auth/password
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**username**  <br>*必填*|username|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/password
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatepasswordusingput"></a>
#### 找回密码,验证验证码
```
PUT /auth/password
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**vo**  <br>*必填*|vo|[更新密码的vo](#7f61806f222ab2c8da0e5a667a6d90a6)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/password
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "captcha" : "string",
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="istokenavalableusingget"></a>
#### 验证token是否有效
```
GET /auth/token
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**token**  <br>*必填*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/token
```


###### 请求 query
```
json :
{
  "token" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getuserinfousingget"></a>
#### 根据token获取用户信息
```
GET /auth/user
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*必填*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/auth/user
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="e483a9180d68049d4acfed9bbbba959b"></a>
### 文章相关的接口
Article Controller


<a name="addarticleusingpost"></a>
#### 添加文章
```
POST /articles
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**article**  <br>*必填*|article|[文章的实体类](#c2a9557f6fd07b6b3e5a1c723dfa5376)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "authorId" : 0,
  "categories" : [ 0 ],
  "commentCount" : 0,
  "content" : "string",
  "createTime" : "string",
  "id" : 0,
  "isDraft" : true,
  "modifyTime" : "string",
  "state" : true,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getarticleusingget"></a>
#### 查询文章
```
GET /articles
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatearticleusingput"></a>
#### 修改文章
```
PUT /articles
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**article**  <br>*必填*|article|[文章的实体类](#c2a9557f6fd07b6b3e5a1c723dfa5376)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "authorId" : 0,
  "categories" : [ 0 ],
  "commentCount" : 0,
  "content" : "string",
  "createTime" : "string",
  "id" : 0,
  "isDraft" : true,
  "modifyTime" : "string",
  "state" : true,
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="testusingget"></a>
#### test
```
GET /articles/test
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles/test
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getarticlebyidusingget"></a>
#### 根据id查询文章
```
GET /articles/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updataearticlestateusingput"></a>
#### 修改文章状态
```
PUT /articles/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|
|**Body**|**stateJson**  <br>*必填*|stateJson|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles/0
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deletearticleusingdelete"></a>
#### 删除文章
```
DELETE /articles/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/articles/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="6a32ef2ab131287cabe8f779ed8d2b59"></a>
### 用于通知公告的接口
Notice Controller


<a name="insertnoticeusingpost"></a>
#### 添加公告
```
POST /notices
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**notice**  <br>*必填*|notice|[Notice](#notice)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/notices
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "content" : "string",
  "endTime" : "string",
  "id" : 0,
  "startTime" : "string",
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getnoticesusingget"></a>
#### 获取通知公告
```
GET /notices
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/notices
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatenoticeusingput"></a>
#### 更新公告
```
PUT /notices
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**notice**  <br>*必填*|notice|[Notice](#notice)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/notices
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "content" : "string",
  "endTime" : "string",
  "id" : 0,
  "startTime" : "string",
  "title" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="selectnoticebyidusingget"></a>
#### 根据id获取公告
```
GET /notices/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/notices/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deletenoticeusingdelete"></a>
#### 删除公告
```
DELETE /notices/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/notices/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="c9bf87eec3f0ca86f8693027a557f5e9"></a>
### 用户相关的接口
User Controller


<a name="adduserusingpost"></a>
#### 添加用户
```
POST /users
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**user**  <br>*必填*|user|[User](#user)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "avatarUrl" : "string",
  "createTime" : "string",
  "email" : "string",
  "id" : 0,
  "nickname" : "string",
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="findalluserusingget"></a>
#### 查找所有用户,传入username则开启模糊查询,否则全部查询
```
GET /users
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|
|**Query**|**username**  <br>*可选*|username|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result«List»](#c1781e5e2dbb907f97761770e9a345e6)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0,
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : [ "object" ],
  "msg" : "string",
  "ok" : true
}
```


<a name="updateuserusingput"></a>
#### 更新用户
```
PUT /users
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**user**  <br>*必填*|user|[User](#user)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "avatarUrl" : "string",
  "createTime" : "string",
  "email" : "string",
  "id" : 0,
  "nickname" : "string",
  "password" : "string",
  "username" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deleteuserusingdelete_1"></a>
#### 根据用户名删除用户
```
DELETE /users
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**username**  <br>*必填*|username|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="getuservobyidusingget"></a>
#### 根据id返回用户
```
GET /users/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deleteuserusingdelete"></a>
#### 根据id删除用户
```
DELETE /users/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/users/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="f0dcad510488416a48e4bcf1b6902597"></a>
### 系统设置相关的接口
Setting Controller


<a name="getsettingusingget"></a>
#### 获取当前设置
```
GET /settings
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/settings
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="updatesettingusingput"></a>
#### 更新设置
```
PUT /settings
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**setting**  <br>*必填*|setting|[Setting](#setting)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/settings
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "commentEnabled" : true,
  "description" : "string",
  "id" : 0,
  "name" : "string",
  "providerNum" : "string",
  "url" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="a26aab697bc9e818b77fff41b4bcb707"></a>
### 评论有关的接口
Comment Controller


<a name="insertcommentusingpost"></a>
#### 添加评论
```
POST /comments
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*必填*|token|string|
|**Body**|**comment**  <br>*必填*|comment|[Comment](#comment)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{
  "articleId" : 0,
  "children" : [ {
    "articleId" : 0,
    "children" : [ "..." ],
    "content" : "string",
    "createTime" : "string",
    "email" : "string",
    "id" : 0,
    "nickname" : "string",
    "parentId" : 0,
    "toId" : 0,
    "website" : "string"
  } ],
  "content" : "string",
  "createTime" : "string",
  "email" : "string",
  "id" : 0,
  "nickname" : "string",
  "parentId" : 0,
  "toId" : 0,
  "website" : "string"
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="selectallcommentusingget"></a>
#### 查询所有的评论
```
GET /comments
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Query**|**articleId**  <br>*可选*|articleId|integer (int64)|
|**Query**|**content**  <br>*可选*|content|string|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "articleId" : 0,
  "content" : "string",
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deletecommentusingdelete_1"></a>
#### 批量删除评论
```
DELETE /comments
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Body**|**deleteList**  <br>*必填*|deleteList|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments
```


###### 请求 header
```
json :
"string"
```


###### 请求 body
```
json :
{ }
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="selectcommentbyarticleidusingget"></a>
#### 查询所有的评论,有子评论
```
GET /comments/{articleId}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**articleId**  <br>*必填*|articleId|integer (int64)|
|**Query**|**limit**  <br>*必填*|limit|integer (int32)|
|**Query**|**page**  <br>*必填*|page|integer (int32)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments/0
```


###### 请求 header
```
json :
"string"
```


###### 请求 query
```
json :
{
  "limit" : 0,
  "page" : 0
}
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```


<a name="deletecommentusingdelete"></a>
#### 删除评论
```
DELETE /comments/{id}
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Header**|**token**  <br>*可选*|token|string|
|**Path**|**id**  <br>*必填*|id|integer (int64)|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|[Result](#result)|
|**204**|No Content|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|


##### 生成

* `*/*`


##### HTTP请求示例

###### 请求 path
```
/comments/0
```


###### 请求 header
```
json :
"string"
```


##### HTTP响应示例

###### 响应 200
```
json :
{
  "code" : 0,
  "data" : "object",
  "msg" : "string",
  "ok" : true
}
```



