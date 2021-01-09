<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/comments'}">评论管理</el-breadcrumb-item>
      <el-breadcrumb-item v-if="title != ''">文章标题为 {{title}} 的评论</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card>
      <el-row :gutter="300">
        <el-col :span="10">
          <el-input clearable v-model="queryParams.content" @clear="getComments" placeholder="请输入评论内容">
            <el-button slot="append" icon="el-icon-search" @click="getComments"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
            <el-button type="danger" @click="deleteComments" 
            :disabled="deleteButtonEnable" 
            >删除选中</el-button>
        </el-col>
      </el-row>
      <el-table
        :data="commentsList"
        tooltip-effect="dark"
        style="width: 100%" 
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column label="id" width="120" prop="id"> </el-table-column>
        <el-table-column prop="content" label="评论内容" width="120"></el-table-column>
        <el-table-column prop="title" label="评论文章" width="120">
            <template slot-scope="scope">
                    <el-link type="primary">{{scope.row.title}}</el-link>
            </template>
        </el-table-column>
        <el-table-column prop="nickname" label="评论用户" width="120">
            <template slot-scope="scope">
                 <el-popover
                    @show="showUserMsg(scope.row.nickname,scope.row.email,scope.row.website)"
                    placement="top-start"
                    title="用户信息"
                    width="200"
                    trigger="hover"
                    :content="userShowdMsg">
                    <el-link slot="reference">{{scope.row.nickname}}<i class="el-icon-view el-icon--right"></i> </el-link>
                </el-popover>
            </template>

        </el-table-column>

        <el-table-column prop="createTime" label="评论时间" width="200"></el-table-column>
        <el-table-column prop="name" label="操作" >
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="删除此评论"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete" @click="deleteComment(scope.row.id)"></el-button>
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

    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      queryParams: {
        content: '',
        articleId: -1,
        page: 1,
        limit: 5
      },
      total: 0,
      commentsList: [],
      userShowdMsg: '',
      deleteButtonEnable: true,
      deleteParams:{
           deleteList: [],
      },
      title: ''
    }
  },
  beforeRouteUpdate(){
          if(this.$route.query.articleId != null){
          this.queryParams.articleId = this.$route.query.articleId;
      }
       if(this.$route.query.title != null){
          this.title = this.$route.query.title;
      }
      this.getComments();
  },
  created(){
      if(this.$route.query.articleId != null){
          this.queryParams.articleId = this.$route.query.articleId;
      }
       if(this.$route.query.title != null){
          this.title = this.$route.query.title;
      }
      this.getComments();
  },
  methods: {
        handleSizeChange(newSize) {
        this.queryParams.limit = newSize;
         this.getComments();
    },
        handleCurrentChange(newPage) {
         this.queryParams.page = newPage;
         this.getComments();
        },
        getComments(){
            const _this = this;
            this.$axios
        .get("/comments", { params: _this.queryParams })
        .then(function(res) {
          if (res.data.code === 200) {
              _this.commentsList = res.data.list;
              _this.total = res.data.total;
            console.log(res.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
        },
        showUserMsg(nickname,email,website){
            this.userShowdMsg = "评论昵称: "+ nickname +"\n\n评论邮箱: "+email+"\n\n个人网页: "+website;
        },
        deleteComment(id) {
            const _this = this;
            console.log("delete:"+id);
            this.$confirm('此操作将删除该评论及其子评论吗, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
                this.$axios
                    .delete("comments/"+id)
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getComments();
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
        deleteComments(){
             const _this = this;
             console.log(_this.deleteList)
            this.$confirm('此操作将删除这些评论及其子评论吗, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
                this.$axios
                    .delete("comments",{data:_this.deleteList})
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getComments();
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
        handleSelectionChange(val){
            this.deleteList = [];
            var j;
            for(j = 0;j < val.length;j++) {
                this.deleteList.push(val[j].id)
            }
            if(this.deleteList.length == 0){
                this.deleteButtonEnable = true;
            } else {
                this.deleteButtonEnable = false;
            }
            console.log(this.deleteList);
        }
  }
};
</script>

<style>
.el-popover{
    white-space: pre-line;
}
</style>