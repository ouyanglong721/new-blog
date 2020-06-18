<template>
  <el-container class="home-container">
    <!-- 头部 -->
    <el-header>
      <div>
        <img src="../assets/icon.png" class="image_logo" alt />
        <span>NewBlog博客管理</span>
      </div>
            <div>
      <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link dropdown-link dropdown-span">
          您好,{{userInfo.nickname}}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="main" icon="el-icon-house" >回到首页</el-dropdown-item>
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
  },
  data() {
    return {
      userInfo: {},
      menu: {
        write: "/write",
        welcome: "/welcome",
        articles: "/articles",
        categories: "/categories",
        friends: "/friends",
        users: "/users",
        settings: "/settings",
        notice: "/notice",
        comments: "/comments"
      },
      isCollapse: false,
      activeIndex: "",
    };
  },
  methods: {
    getUserInfo() {
      const _this = this;
       this.$axios
          .get("auth/user", {})
          .then(function(res) {
            if (res.data.code == 200) {
              _this.userInfo = res.data.data;
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