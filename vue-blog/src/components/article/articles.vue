<template>
  <div>
  <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/articles'}">文章管理</el-breadcrumb-item>
    </el-breadcrumb>

      <!-- 卡片 -->
    <el-card class="box-card">
      <div class="text item">
        <el-row :gutter="50">
          <el-col :span="8">
            <el-input
              v-model="queryParams.title"
              clearable
              @clear="getArticles"
              placeholder="请输入文章标题"
            >
              <el-button slot="append" icon="el-icon-search" @click="getArticles"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="writeArticles">发布文章</el-button>
          </el-col>
        </el-row>

        <el-table :data="articleList" border stripe>
          <el-table-column align="center" type="index" label="#"></el-table-column>
          <el-table-column align="center" sortable label="id" width="100px" prop="id"></el-table-column>
          <el-table-column align="center" sortable label="标题" prop="title"></el-table-column>
          <el-table-column align="center" sortable label="作者" prop="nickname"></el-table-column>
          <el-table-column align="center" sortable label="创建时间" prop="createTime"></el-table-column>
          <el-table-column align="center" sortable label="修改时间" prop="modifyTime"></el-table-column>
          <el-table-column align="center" sortable label="状态">
                <template slot-scope="scope">  
                          <el-switch
                          @change="updateState(scope.row)"
                          v-model="scope.row.state"
                          active-text="公开"
                          inactive-text="私有"
                          active-color="#13ce66"
                          inactive-color="#FF8C00">
                        </el-switch>
        </template>
          </el-table-column>
          <el-table-column align="center" sortable label="评论数" prop="">
            <template slot-scope="scope">
                <el-link type="primary" :disabled="scope.row.commentCount==0" @click="getCommentByArticleId(scope.row.id,scope.row.title)">
                  {{scope.row.commentCount}}
                </el-link>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="编辑此文章"
                :enterable="false"
                placement="top-start"
              >
                <el-button type="primary" icon="el-icon-edit" @click="editArticle(scope.row)"></el-button>
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除此文章"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete" @click="deleteArticle(scope.row.id)"></el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>

        <div style="text-align:center">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="queryParams.page"
            :page-sizes="[5, 10, 50, 100]"
            :page-size="queryParams.limit"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
          ></el-pagination>
        </div>
      </div>
    </el-card>


  </div>
</template>

<script>
export default {
  created(){
    this.getArticles();
  },
  data(){
    return {
      total: 0,
      articleList: [],
      queryParams: {
        title: "",
        page: 1,
        limit: 5
      }
    }
  },
  methods: {
    editArticle(article){
        this.$router.push({
          path: '/write',
          query: {
            id: article.id,
            edit: true
          }
        })
    },
    getCommentByArticleId(id, title){
       this.$router.push({
          path: '/comments',
          query: {
            articleId: id,
            title: title
          }
        })
    },
    deleteArticle(id){
    const _this = this;
          this.$confirm('此操作将删除该文章, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete("articles/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getArticles();
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    writeArticles(){
      this.$router.push("/write")
    },getArticles() {
      const _this = this;
      this.$axios
        .get("/home/articles", { params: _this.queryParams })
        .then(function(res) {
          if (res.data.code === 200) {
            _this.articleList = res.data.data.list;
            _this.total = res.data.data.total;
            console.log(res.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    searchArticles(){
      this.queryParams.page = 1;
      this.getArticles();
    },
    handleSizeChange(newSize) {
      this.queryParams.limit = newSize;
      this.getArticles();
    },
    handleCurrentChange(newPage) {
      this.queryParams.page = newPage;
      this.getArticles();
    },updateState(article) {
      console.log(article)
      const _this = this;
      const id = article.id;
      const state = article.state;
      console.log(id,state);
      const successMsg = state===true?"公开":"私有";
        this.$axios
        .put("articles/"+id, { state:state })
        .then(function(res) {
          if (res.data.code == 200) {
              _this.$message.success("已将文章更改为: "+successMsg);
          }else{
            console.log("失败")
              article.state = !state;
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  }
}
</script>


<style lang="less" scoped>

</style>