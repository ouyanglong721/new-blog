<template>
  <div>
       <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/categories'}">分类管理</el-breadcrumb-item>
    </el-breadcrumb>


    
      <!-- 卡片 -->
    <el-card class="box-card">
      <div class="text item">
        <el-button type="primary" @click="addCategory">添加分类</el-button>
        <el-table :data="categoriesList" border stripe>
          <el-table-column align="center" type="index" label="#"></el-table-column>
          <el-table-column align="center" sortable label="id" width="100px" prop="id"></el-table-column>
          <el-table-column align="center" sortable label="分类名" prop="name"></el-table-column>
          <el-table-column align="center" sortable label="描述信息" prop="description"></el-table-column>
          <el-table-column align="center" sortable label="子分类数量">
                <template slot-scope="scope">   
                  <el-link type="success" v-if="scope.row.childrenCount != 0" @click="getChildren(scope.row.id)"> {{"共有 "+scope.row.childrenCount+" 个分类"}}    </el-link>    
                  <el-link type="info" disabled v-if="scope.row.childrenCount === 0"> {{"无子分类"}} </el-link>    
                </template>

          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="编辑此分类"
                :enterable="false"
                placement="top-start"
              >
                <el-button type="primary" icon="el-icon-edit" @click="editCategory(scope.row.id)"></el-button>
              </el-tooltip>
              <el-tooltip
                class="item"
                effect="dark"
                content="添加子分类"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="success" icon="el-icon-circle-plus" @click="addChild(scope.row.id)"></el-button>
              </el-tooltip>

                  <el-tooltip
                class="item"
                effect="dark"
                content="删除此分类"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete" @click="deleteCategory(scope.row.id)"></el-button>
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
                <el-dialog
            :title="dialogTitle"
            :visible.sync="dialogVisible"
            width="50%" 
            :close-on-click-modal = "false" 
            @close="dialogClosed"
            >

          <el-form :model="dlgForm" :rules="dlgFormRules" ref="dlgFormRef" label-width="70px">


        <el-form-item v-if="dlgForm.id != ''" label="ID" prop="id">
          <el-input v-model="dlgForm.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="分类名" prop="name">
          <el-input v-model="dlgForm.name"></el-input>
        </el-form-item>

        <el-form-item label="描述信息" prop="description">
          <el-input v-model="dlgForm.description"></el-input>
        </el-form-item>

          </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </span>

        </el-dialog>


  </div>
</template>

<script>
export default {
  beforeRouteUpdate (to, from, next){
     next();
       if(this.$route.query.parentId != null){
            this.getCategories(this.$route.query.parentId);  
            this.parentId = this.$route.query.parentId;
        } else {
             this.getCategories(-1);  
        }  
       
  },
    created() {
        if(this.$route.query.parentId != null){
            this.getCategories(this.$route.query.parentId);  
            this.parentId = this.$route.query.parentId;
        } else {
             this.getCategories(-1);  
        }   
       
    },
    data(){
    return {
      total: 0,
      categoriesList: [],
      queryParams: {
        page: 1,
        limit: 5
      },
      dialogVisible: false,
      dialogTitle: "",
      dlgForm:{
          id: '',
          parentId: '',
          name: '',
          description: ''
      },
      dlgFormRules:{
      },
      parentId: -1
    }
  },methods: {
      dialogClosed(){
          this.$refs.dlgFormRef.resetFields();
          this.dlgForm.id = '';
      },
      deleteCategory(id){
          const _this = this;
          this.$confirm('此操作将删除该分类及其子分类, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete("categories/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getCategories(_this.parentId); 
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
      getChildren(parentId){
            this.$router.push({
                path: "/categories", 
                query:{
                    parentId: parentId
                }
            });
      },editCategory(id){
          this.dialogTitle = "修改分类";
         this.dialogVisible=true;
          const _this = this;
           this.$axios.get("categories/"+id
                    , _this.dlgForm
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.dlgForm = res.data.data;
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
      }, submit(){
            switch (this.dialogTitle){
                case "添加分类":
                    this.confirmAdd();
                break;
                case "修改分类":
                    this.confirmUpdate();
                break;
            }
        },addChild(parentId){
                this.dlgForm.parentId = parentId;
                this.addCategory();
        }
      ,addCategory(){
          this.dialogTitle = "添加分类";
          this.dialogVisible = true;
      }, confirmAdd(){
          const _this = this;
           this.$axios.post("categories"
                    ,_this.dlgForm)
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.dialogVisible = false;
                            _this.$message.success("添加分类成功");
                            _this.getCategories(_this.parentId); 
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
      },confirmUpdate(){
          this.dialogTitle = "修改分类";
          const _this = this;
           this.$axios.put("categories"
                    ,_this.dlgForm)
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.dialogVisible = false;
                            _this.$message.success("修改分类成功");
                            _this.getCategories(_this.parentId); 
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
      },
      getCategories(id){
          const _this = this;
          this.$axios.get("categories/"+id+"/childCategories", 
                    {params:this.queryParams})
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.categoriesList = res.data.list;
                            _this.total = res.data.total;
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
      },
    handleSizeChange(newSize) {
      this.queryParams.limit = newSize;
      this.getCategories(this.parentId);
    },
    handleCurrentChange(newPage) {
      this.queryParams.page = newPage;
      this.getCategories(this.parentId);
    }
  }

}
</script>

<style>

</style>