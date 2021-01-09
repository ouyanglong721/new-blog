<template>
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div>
        <img src="../assets/icon.png" class="image_logo" alt />
        <span>{{settings.name}}博客管理</span>
      </div>
            <div>
      <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link dropdown-link dropdown-span">
          您好,{{nickname}}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="main" icon="el-icon-house" >回到首页</el-dropdown-item>
            <el-dropdown-item command="user" icon="el-icon-user">个人信息</el-dropdown-item>
          <el-dropdown-item command="write" icon="el-icon-edit-outline">发布文章</el-dropdown-item>
          <el-dropdown-item command="setting" icon="el-icon-setting">网站设置</el-dropdown-item>
          <el-dropdown-item command="logout" icon="el-icon-s-promotion">注销账号</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-avatar size="medium" :src="userInfo.avatarUrl"></el-avatar>
        </div>
    </el-header>
    <!-- 主题 -->
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="isCollapse?'64px':'200px'">
        <div class="toggle-button" @click="toggleCollapse">|||</div>
        <el-menu background-color="#333744" text-color="#fff" active-text-color="#409eff"
         :collapse="isCollapse" :default-active="$route.path"
         :collapse-transition="false"
          router>
            <el-menu-item :index="menu.welcome" >
                <i class="el-icon-s-home"></i>
                <span>首页</span>
            </el-menu-item>

             <el-menu-item :index="menu.write" >
                <i class="el-icon-edit-outline"></i>
                <span>发布文章</span>
            </el-menu-item>

            <el-menu-item :index="menu.articles">
                <i class="el-icon-notebook-1"></i>
                <span>文章管理</span>
            </el-menu-item>

            <el-menu-item :index="menu.categories">
                <i class="el-icon-copy-document"></i>
                <span>分类管理</span>
            </el-menu-item>

            <el-menu-item :index="menu.comments" >
                <i class="el-icon-chat-dot-square"></i>
                <span>评论管理</span>
            </el-menu-item>

            <el-menu-item :index="menu.users">
                <i class="el-icon-s-custom"></i>
                <span>用户管理</span>
            </el-menu-item>

             <el-menu-item :index="menu.friends" >
                <i class="el-icon-location"></i>
                <span>友链管理</span>
            </el-menu-item>

                 <el-menu-item :index="menu.notice">
                <i class="el-icon-bell"></i>
                <span>通知公告</span>
                 </el-menu-item>

             <el-menu-item :index="menu.settings">
                <i class="el-icon-setting"></i>
                <span>网站设置</span>
            </el-menu-item>
        </el-menu>
      </el-aside>
      <!-- 右侧主题 -->

       <el-dialog title="修改用户"  :visible.sync="userDialogVisible"   width="50%" :close-on-click-modal="false">
      
        <el-form :model="userInfo" :rules="FormRules" ref="editFormRef" label-width="70px">

       <el-form-item label="ID" prop="id">
          <el-input v-model="userInfo.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="用户名" prop="username">
          <el-input v-model="userInfo.username"></el-input>
        </el-form-item>

        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="userInfo.nickname"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="userInfo.password"></el-input>
        </el-form-item>
         <el-form-item label="头像" prop="avatarUrl">
        <el-input v-if="false" v-model="userInfo.avatarUrl" style="width: 200px"></el-input>
        <el-upload
            class="avatar-uploader"
            :action="uploadUrl"
            :show-file-list="false"
            :headers="headers"
            :on-success="handleEditAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="userInfo.avatarUrl" :src="userInfo.avatarUrl" style="width: 100px" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        </el-form-item>


        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userInfo.email"></el-input>
        </el-form-item>
      </el-form>

  
  <span slot="footer" class="dialog-footer">
    <el-button @click="userDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="updateUser">修 改</el-button>
  </span>
</el-dialog>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>


<script>
export default {
  created() {
    this.getUserInfo();
    this.getSettings();
  },
  data() {
    return {
      userDialogVisible: false,
      FormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 10, message: "长度在 5 到 10 个字符", trigger: "blur" }
        ],password: [
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
      },
      nickname: ''
      ,
      settings:{
      },
      userInfo: {
        password: '',
      },
      menu: {
        write: "/write",
        welcome: "/welcome",
        articles: "/articles",
        categories: "/categories",
        friends: "/friends",
        users: "/users",
        settings: "/settings",
        notice: "/notices",
        comments: "/comments"
      },
      isCollapse: false,
      activeIndex: "",
    };
  },
  methods: {
    updateUser(){
        this.$refs.editFormRef.validate(validate=>{
               if(!validate) return;
                const _this = this;
                this.$axios
                    .put("users", this.userInfo)
                    .then(function(res) {
                        if (res.data.code == 200) {
                            console.log(_this.userInfo.password)
                            if(_this.userInfo.password == '' || _this.userInfo.password == null){ 
                               _this.$message.success("修改成功");
                              _this.userDialogVisible = false;
                                 _this.getUsers();
                            }  else {
                               _this.$message.success("修改成功,请重新登入");
                              sessionStorage.removeItem('token');
                              setTimeout(() =>{
                                _this.$router.push("/login")
                              },1500);
                            }
                           
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
                    })
      },
      getSettings(){
        const _this = this;
        this.$axios.get("settings"
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            console.log(res.data.data)
                            _this.settings = res.data.data;
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });  
        },
    getUserInfo() {
      const _this = this;
       this.$axios
          .get("auth/user", {})
          .then(function(res) {
            if (res.data.code == 200) {
              _this.userInfo = res.data.data;
              _this.nickname = _this.userInfo.nickname;
            }
          })
          .catch(function(error) {
            console.log(error);
          });
    },
    handleCommand(command) {
      switch(command) {
        case "logout":
           const _this = this;
           this.$axios
          .delete("auth/logout", {})
          .then(function(res) {
            if (res.data.code == 200) {
              _this.$message.success("注销成功");
              window.sessionStorage.removeItem("token");
              _this.$router.push("/login");
            } else {
              window.sessionStorage.removeItem("token");
              _this.$router.push("/login");
            }
          })
          .catch(function(error) {
            console.log(error);
          });
          break;
        case "main":
          console.log("home");
          this.$router.push("home");
          break;
        case "write":
          this.$router.push("write");
          break;
        case "setting":
         this.$router.push("settings");
          break;
          case "user":
            this.getUserInfo();
            this.userDialogVisible = true;
            break;
      }
   
    },
    toggleCollapse() {
        this.isCollapse = !this.isCollapse
    }
  }
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}

.dropdown-link {
  font-size: 18px;
  color: #778899;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 22px;

  > div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  background-color: #333744;
  .el-menu {
    border-right: 0;
  }
}

.el-main {
  background-color: #eaedf1;
}

.image_logo {
  width: 17%;
}

.toggle-button{
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;  
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}

.dropdown{
    text-align: center;
}
</style>