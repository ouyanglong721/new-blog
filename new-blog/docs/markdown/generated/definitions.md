
<a name="definitions"></a>
## 定义

<a name="category"></a>
### Category

|名称|说明|类型|
|---|---|---|
|**children**  <br>*可选*|**样例** : `[ "[category](#category)" ]`|< [Category](#category) > array|
|**description**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**parentId**  <br>*可选*|**样例** : `0`|integer (int64)|


<a name="comment"></a>
### Comment

|名称|说明|类型|
|---|---|---|
|**articleId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**children**  <br>*可选*|**样例** : `[ "[comment](#comment)" ]`|< [Comment](#comment) > array|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**email**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**parentId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**toId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**website**  <br>*可选*|**样例** : `"string"`|string|


<a name="friend"></a>
### Friend

|名称|说明|类型|
|---|---|---|
|**iconUrl**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**website**  <br>*可选*|**样例** : `"string"`|string|


<a name="notice"></a>
### Notice

|名称|说明|类型|
|---|---|---|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**endTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**startTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**title**  <br>*可选*|**样例** : `"string"`|string|


<a name="result"></a>
### Result

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `"object"`|object|
|**msg**  <br>*可选*|**样例** : `"string"`|string|
|**ok**  <br>*可选*|**样例** : `true`|boolean|


<a name="c1781e5e2dbb907f97761770e9a345e6"></a>
### Result«List»

|名称|说明|类型|
|---|---|---|
|**code**  <br>*可选*|**样例** : `0`|integer (int32)|
|**data**  <br>*可选*|**样例** : `[ "object" ]`|< object > array|
|**msg**  <br>*可选*|**样例** : `"string"`|string|
|**ok**  <br>*可选*|**样例** : `true`|boolean|


<a name="setting"></a>
### Setting

|名称|说明|类型|
|---|---|---|
|**commentEnabled**  <br>*可选*|**样例** : `true`|boolean|
|**description**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**name**  <br>*可选*|**样例** : `"string"`|string|
|**providerNum**  <br>*可选*|**样例** : `"string"`|string|
|**url**  <br>*可选*|**样例** : `"string"`|string|


<a name="user"></a>
### User

|名称|说明|类型|
|---|---|---|
|**avatarUrl**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**email**  <br>*可选*|**样例** : `"string"`|string|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**nickname**  <br>*可选*|**样例** : `"string"`|string|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|


<a name="c2a9557f6fd07b6b3e5a1c723dfa5376"></a>
### 文章的实体类

|名称|说明|类型|
|---|---|---|
|**authorId**  <br>*可选*|**样例** : `0`|integer (int64)|
|**categories**  <br>*可选*|**样例** : `[ 0 ]`|< integer (int64) > array|
|**commentCount**  <br>*可选*|**样例** : `0`|integer (int32)|
|**content**  <br>*可选*|**样例** : `"string"`|string|
|**createTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**id**  <br>*可选*|**样例** : `0`|integer (int64)|
|**isDraft**  <br>*可选*|**样例** : `true`|boolean|
|**modifyTime**  <br>*可选*|**样例** : `"string"`|string (date-time)|
|**state**  <br>*可选*|**样例** : `true`|boolean|
|**title**  <br>*可选*|**样例** : `"string"`|string|


<a name="7f61806f222ab2c8da0e5a667a6d90a6"></a>
### 更新密码的vo

|名称|说明|类型|
|---|---|---|
|**captcha**  <br>*可选*|**样例** : `"string"`|string|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|


<a name="27bb0c12cfc0bd81a4c201280c67d8aa"></a>
### 登入用户

|名称|说明|类型|
|---|---|---|
|**captcha**  <br>*可选*|**样例** : `"string"`|string|
|**cid**  <br>*可选*|**样例** : `"string"`|string|
|**password**  <br>*可选*|**样例** : `"string"`|string|
|**rememberMe**  <br>*可选*|**样例** : `"string"`|string|
|**username**  <br>*可选*|**样例** : `"string"`|string|



