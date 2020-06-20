<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/articles'}">文章管理</el-breadcrumb-item>
      <el-breadcrumb-item>{{breadTitle}}</el-breadcrumb-item>
    </el-breadcrumb>

    <el-card class="box-card">
      <div class="title-box">
        <el-input v-model="requestParam.title" class="title-input" placeholder="请输入文章标题"></el-input>
      </div>

      <el-row>
        <el-col :span="19">
          <div class="editor">
            <mavon-editor
              :toolbars="markdownOption"
              ref="editorRef"
              @imgAdd="imgAdd"
              defaultOpen="edit"
              v-model="requestParam.content"
            />
          </div>
        </el-col>
        <el-col :span="5">
          <el-card class="small-card">
            <div slot="header" class="clearfix">
              <span>文章分类</span>
            </div>
            <el-tree
              ref="categoriesTreeRef"
              :data="categoryTreeData"
              show-checkbox
              node-key="id"
              :props="defaultProps"
              class="category-tree" 
              :default-checked-keys = requestParam.categories
            ></el-tree>
          </el-card>
          <el-card class="small-card">
            <div slot="header" class="clearfix">
              <span>发布日期</span>
            </div>
            <div class="block">
              <el-date-picker
                v-model="requestParam.createTime"
                type="datetime"
                placeholder="选择日期时间"
                default-time="12:00:00"
                size="small"
                value-format="yyyy-MM-dd HH:mm:ss"
                format="yyyy-MM-dd HH:mm:ss"
              ></el-date-picker>
            </div>
          </el-card>

          <el-card class="small-card">
            <div slot="header" class="clearfix">
              <span>操作</span>
            </div>
            <div class="op-radio">
              <el-radio-group v-model="opRadio">
                <el-radio-button label="公开"></el-radio-button>
                <el-radio-button label="私有"></el-radio-button>
              </el-radio-group>
            </div>
            <div class="op-button">
              <el-button type="primary" round @click="submitArticle">立即提交</el-button>
              <el-button type="info" round @click="saveArticle"  :disabled="isEdit">保存草稿</el-button>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
   beforeRouteUpdate (to, from, next){
      if(this.$route.id == null){
          this.requestParam = this.$g.clearParams(this.requestParam);
          this.breadTitle = "发布文章";
      }
      next();
  },
  created() {
    this.getCategoryTree();

    const _this = this;
    this.$axios
      .get("auth/user", {})
      .then(function(res) {
        if (res.data.code == 200) {
          _this.requestParam.authorId = res.data.data.id;
        }
      })
      .catch(function(error) {
        console.log(error);
      });

      this.requestParam.id = this.$route.query.id;

      this.isEdit = this.$route.query.edit;

      if(this.isEdit){
        this.breadTitle = "编辑文章";
         this.getArticle(this.requestParam.id);
      }
  },
  data() {
    return {
      authorId: "",
      value: "hahaha",
      markdownOption: {
        bold: true, // 粗体
        italic: true, // 斜体
        header: true, // 标题
        underline: true, // 下划线
        strikethrough: true, // 中划线
        mark: true, // 标记
        superscript: true, // 上角标
        subscript: true, // 下角标
        quote: true, // 引用
        ol: true, // 有序列表
        ul: true, // 无序列表
        link: true, // 链接
        imagelink: true, // 图片链接
        code: true, // code
        table: true, // 表格
        fullscreen: true, // 全屏编辑
        readmodel: true, // 沉浸式阅读
        htmlcode: false, // 展示html源码
        help: true, // 帮助
        /* 1.3.5 */
        undo: true, // 上一步
        redo: true, // 下一步
        trash: true, // 清空
        save: true, // 保存（触发events中的save事件）
        /* 1.4.2 */
        navigation: true, // 导航目录
        /* 2.1.8 */
        alignleft: true, // 左对齐
        aligncenter: true, // 居中
        alignright: true, // 右对齐
        /* 2.2.1 */
        subfield: true, // 单双栏模式
        preview: true // 预览
      },
      categoryTreeData: [],
      defaultProps: {
        children: "children",
        label: "name"
      },
      requestParam: {
        id: "",
        authorId: "",
        title: "",
        content: "",
        categories: [],
        createTime: '',
        state: true,
        isDraft: false
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },
      opRadio: "公开",
      isEdit: false,
      breadTitle: '发布文章'
    };
  },
  methods: {
    submitArticle() {
      if(this.isEdit){
          this.update();
      }
      else {
          this.submit();
      }
    },
    saveArticle() {
      this.requestParam.isDraft = true;
      this.submit();
    },
    submit() {
       if(this.opRadio === "公开"){
          this.requestParam.state = true;
      }else if(this.opRadio ==="私有" ){
          this.requestParam.state = false;
      }
      this.requestParam.categories = this.$refs.categoriesTreeRef.getCheckedKeys();
      console.log(this.requestParam);
      const _this = this;
      this.$axios
        .post("articles", this.requestParam)
        .then(function(res) {
          if (res.data.code == 200) {
            _this.$message.success("发布文章成功");
            _this.$router.push("/articles");
            console.log(res);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },update() {
      if(this.opRadio === "公开"){
          this.requestParam.state = true;
      }else if(this.opRadio ==="私有" ){
          this.requestParam.state = false;
      }
      this.requestParam.categories = this.$refs.categoriesTreeRef.getCheckedKeys();
      console.log(this.requestParam);
      const _this = this;
      this.$axios
        .put("articles", this.requestParam)
        .then(function(res) {
          if (res.data.code == 200) {
            _this.$message.success("文章更新成功");
            _this.$router.push("/articles");
            console.log(res);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    imgAdd(pos, $file) {
      console.log("img add");
      var formdata = new FormData();
      formdata.append("file", $file);
      const _this = this;
      this.$axios({
        url: "upload",
        method: "post",
        data: formdata,
        headers: { "Content-Type": "multipart/form-data" }
      }).then(res => {
        console.log(res);
        _this.$refs.editorRef.$img2Url(pos, res.data.data);
      });
    },
    getCategoryTree() {
      const _this = this;
      this.$axios
        .get("categories/tree", {})
        .then(function(res) {
          if (res.data.code == 200) {
            _this.categoryTreeData = res.data.data;
          }
        })
        .catch(function(error) {
          console.log(error);
        }); 
    },
    getArticle(id){
      const _this = this;
      console.log("获取文章 id:"+id)
        this.$axios.get("articles/"+id, {})
        .then(function(res) {
          if (res.data.code == 200) {
            _this.requestParam = res.data.data;
            _this.opRadio = _this.requestParam.state?"公开":"私有";
            console.log(res.data.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
};
</script>

<style lang="less" scoped>
.title-input {
  width: 900px;
  height: 40px;
}
.title-box {
  margin-bottom: 10px;
}

.editor {
  width: 97%;
  min-height: 500px;
}

.markdown-body {
  min-height: 700px;
}
.category-tree {
  margin-top: 10px;
}

.small-card {
  margin-bottom: 40px;
}

.op-radio{
  text-align: center;
  margin-bottom: 20px;
}

.op-button{
   text-align: center;
}
</style>