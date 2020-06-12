<template>
  <div>
    <!-- 面包屑 -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item>首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/users'}">用户管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片 -->
    <el-card class="box-card">
      <div class="text item">
        <el-row :gutter="50">
          <el-col :span="8">
            <el-input
              v-model="queryParams.username"
              clearable
              @clear="getUsers"
              placeholder="请输入用户名"
            >
              <el-button slot="append" icon="el-icon-search" @click="getUsers"></el-button>
            </el-input>
          </el-col>
          <el-col :span="4">
            <el-button type="primary" @click="dialogVisible=true ">添加用户</el-button>
          </el-col>
        </el-row>

        <el-table :data="userList" border stripe>
          <el-table-column align="center" type="index" label="#"></el-table-column>
          <el-table-column align="center" sortable label="id" width="100px" prop="id"></el-table-column>
          <el-table-column align="center" sortable label="用户名" prop="username"></el-table-column>
          <el-table-column align="center" sortable label="昵称" prop="nickname"></el-table-column>
          <el-table-column align="center" label="邮箱" prop="email"></el-table-column>
          <el-table-column align="center" sortable label="创建时间" prop="createTime"></el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="编辑此用户"
                :enterable="false"
                placement="top-start"
              >
                <el-button type="primary" icon="el-icon-edit"></el-button>
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="删除此用户"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete"></el-button>
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

    <el-dialog title="添加用户" @close="addDialogClosed" :visible.sync="dialogVisible" width="50%" :close-on-click-modal="false">
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="70px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>

        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="addForm.nickname"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="addForm.password"></el-input>
        </el-form-item>
         <el-form-item label="头像" prop="avatarUrl">
        <el-input v-if="false" v-model="addForm.avatarUrl" style="width: 200px"></el-input>
        <el-upload
            class="avatar-uploader"
            action="http://localhost:8081/upload"
            :show-file-list="false"
            :headers="headers"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="addForm.avatarUrl" :src="addForm.avatarUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        </el-form-item>


        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUser">添 加</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userList: [],
      queryParams: {
        username: "",
        page: 1,
        limit: 5
      },
      total: 1,
      userList: [],
      dialogVisible: false,
      addForm: {
        username: "",
        avatarUrl: '',
        email: '',
        nickname: '',
      },
      headers:{
          token: window.sessionStorage.getItem('token')
      }
      ,
      addFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 10, message: "长度在 5 到 10 个字符", trigger: "blur" }
        ],password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 16, message: "长度在 8 到 16 个字符", trigger: "blur" }
        ],nickname: [
          { required: true, message: "请输入昵称", trigger: "blur" },
          { min: 3, max: 16, message: "长度在 3 到 16 个字符", trigger: "blur" }
        ],email: [
         { required: true, message: '请输入邮箱地址', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
        ],avatarUrl: [
            {required: true, message: '请上传头像', trigger: 'blur'},
             { min: 1, message: "请上传头像", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getUsers();
  },
  methods: {
    getUsers() {
      const _this = this;
      this.$axios
        .get("/users", { params: _this.queryParams })
        .then(function(res) {
          if (res.data.code === 200) {
            _this.userList = res.data.data.list;
            _this.total = res.data.data.total;
            console.log(_this.userList);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    handleSizeChange(newSize) {
      this.queryParams.limit = newSize;
      this.getUsers();
    },
    handleCurrentChange(newPage) {
      this.queryParams.page = newPage;
      this.getUsers();
    }, beforeAvatarUpload(file) {
        const isJPGPNG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/bmp';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPGPNG) {
          this.$message.error('请上传图片格式');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPGPNG && isLt2M;
      },handleAvatarSuccess(res, file) {
          if(res.code == 200) {
                this.addForm.avatarUrl = res.data;
          } else {
              this.$message.error('图片上传失败,请重试');
          }
    

      },addDialogClosed(){
          this.$refs.addFormRef.resetFields();
      },addUser(){
           this.$refs.addFormRef.validate(validate=>{
               if(!validate) return;
                const _this = this;
                this.$axios
                    .post("users", this.addForm)
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("添加成功");
                            _this.dialogVisible = false;
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
                    })
      }
  }
};
</script>


<style lang="less" scoped>

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 80px;
    height: 80px;
    line-height: 80px;
    text-align: center;
  }
  .avatar {
    width: 80px;
    height: 80px;
    display: block;
  }

</style>