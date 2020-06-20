<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/settings'}">网站设置</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="settigs-box-card">
      <el-form
        :model="settingsForm"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="网站名称" prop="name">
          <el-input v-model="settingsForm.name"></el-input>
        </el-form-item>


             <el-form-item label="网站地址" prop="url">
          <el-input v-model="settingsForm.url"></el-input>
        </el-form-item>

     <el-form-item label="网站描述" prop="description">
          <el-input v-model="settingsForm.description"></el-input>
        </el-form-item>

         <el-form-item label="备案号" prop="providerNum">
          <el-input v-model="settingsForm.providerNum"></el-input>
        </el-form-item>

             <el-form-item label="开启评论" prop="commentEnabled">
          <el-switch
  v-model="settingsForm.commentEnabled"
  active-color="#13ce66"
  inactive-color="#ff4949"
  active-text="开启"
  inactive-text="关闭"
  class="comment-switch">
</el-switch>
        </el-form-item>
         <el-form-item >
        <el-button class="center" type="primary" @click="onSubmit">修改</el-button>
          </el-form-item>
      </el-form>

    </el-card>
    </div>
</template>
<script>
export default {
    created() {
        this.getSettings();      
    },
  data() {
    return {
        ruleForm: {

        },rules:{

        },
        settingsForm: {
            name: '',
            url: '',
            description: '',
            providerNum: '',
            commentEnabled: true
        }
    };
  },
  methods: {
        getSettings(){
            const _this = this;
        this.$axios.get("settings"
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            console.log(res.data.data)
                            _this.settingsForm = res.data.data
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });  
        } ,
      onSubmit(){
             const _this = this;
        this.$axios.put("settings"
                    ,_this.settingsForm
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                           _this.$message({message:'更新网站设置成功',type:'success',customClass:'zZindex'});
                           setTimeout(() =>{
                             location.reload();
                           },1500);
                           
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
 .settigs-box-card {
    width: 500px;

    border: 1px solid #000;
    border-radius: 10px;
    box-shadow: 10em;

    margin: 0 auto;
    margin-top: 125px;
  }

  .center{
      position: absolute;
      left: 25%;
  }

  .comment-switch{
      left: 80px;
  }

</style>