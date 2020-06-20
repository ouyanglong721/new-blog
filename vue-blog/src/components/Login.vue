<template>
  <div class="login_container">
    <div class="login_box">
      <!-- 头像 -->
      <div class="avatar_box">
        <img src="../assets/icon.png" alt />
      </div>
      <!-- 登入表单 -->
      <el-form :model="loginForm" ref="loginFormRef" :rules="loginFormRules" class="login_form">
        <!-- 用户名 -->
        <el-form-item prop="username" label-width="80px" label="用户名:">
          <el-input v-model="loginForm.username" class="new-input" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item prop="password" label-width="80px" label="密码:">
          <el-input
            v-model="loginForm.password"
            type="password"
            class="new-input"
            prefix-icon="el-icon-lock"
          ></el-input>
        </el-form-item>

        <el-form-item prop="captcha" label-width="80px" label="验证码:">
          <el-input class="captcha_imput" placeholder="请输入验证码" v-model="loginForm.captcha"></el-input>
          <img :src="capchaImage" class="img_captcha" @click="getCaptcha" />
        </el-form-item>

        <!-- 按钮 -->
        <el-form-item class="btns button_group" >
          <el-button type="primary" v-loading.fullscreen.lock="fullscreenLoading" @click="login">登入</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // 登入表单的数据绑定对象
      loginForm: {
        username: "",
        password: "",
        captcha: "",
        cid: ""
      },
      // 表单的验证规则
      loginFormRules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { min: 5, max: 10, message: "请输入5-10个字符", trigger: "blur" }
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, max: 16, message: "请输入6-16个字符", trigger: "blur" }
        ],
        captcha: [
          { required: true, message: "请输入验证码", trigger: "blur" },
          { min: 4, max: 4, message: "请输入正确的验证码位数", trigger: "blur" }
        ]
      },
      capchaImage: "",
      fullscreenLoading: false
    };
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      this.$refs.loginFormRef.validate(valid => {
        if (!valid) return;
        const _this = this;
        this.fullscreenLoading = true;
        this.$axios
          .post("auth/login", this.loginForm)
          .then(function(res) {
            _this.fullscreenLoading = false;
            if (res.data.code == 200) {
               _this.$message.success("登入成功");
              window.sessionStorage.setItem("token", res.data.data);
              _this.$router.push("/home");
            } else {
              console.log("登入失败");
              _this.getCaptcha();
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      });
    },
    getCaptcha() {
      const _this = this;
      this.$axios
        .get("auth/captcha", {})
        .then(function(res) {
          _this.capchaImage = `data:image/jpeg;base64,` + res.data.data.image;
          _this.loginForm.cid = res.data.data.cid;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    isTokenAvalible() {
      const _this = this;
      const token = window.sessionStorage.getItem("token");
      if(token == null) return;
      this.$axios
        .get("auth/token", {
          params: {
            token: token
          }
        })
        .then(function(res) {
          if (res.data.code === 200) _this.$router.push("/home");
        })
        .catch(function(error) {
          console.log(error);
        });
    }
  },
  created() {
    this.getCaptcha();
    this.isTokenAvalible();
  }
};
</script>

<style lang="less" scoped>
.login_container {
  background-color: #bbeebe;
  height: 100%;
}



.login_box {
  width: 450px;
  height: 400px;
  background-color: #ffffff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

  .avatar_box {
    height: 130px;
    width: 130px;
    border: 1px solid #eee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}

.button_group{
  margin-right: 35px;
  
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.btns {
  display: flex;
  justify-content: flex-end;
}

.captcha_imput {
  position: relative;

  right: 0px;

  bottom: 12px;

  font-size: 14px;

  display: inline-block;

  width: 45%;
}

.new-input {
  width: 90%;
}

.img_captcha {
  position: relative;
  left: 50px;
  height: 38px;
  //padding-left: 100px;

  border-radius: 5px;
}

.img_captcha {
  position: relative;
  left: 23px;
  top: 2px;
  height: 39px;
  //padding-left: 100px;

  border-radius: 5px;
}
</style>
