<template>
    <div>
          <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/friends'}">友链管理</el-breadcrumb-item>
    </el-breadcrumb>




    
      <!-- 卡片 -->
    <el-card class="box-card">
      <div class="text item">
        <el-button type="primary" @click="addFriend">添加友链</el-button>
        <el-table :data="friendsList" border stripe>
          <el-table-column align="center" type="index" label="#"></el-table-column>
          <el-table-column align="center" sortable label="id" width="100px" prop="id"></el-table-column>
          <el-table-column align="center" sortable label="友链名称" prop="name"></el-table-column>
          <el-table-column align="center"  label="友链网址" sortable prop="website">
              <template slot-scope="scope">
                  <el-link type="primary" :href=scope.row.website  target="_blank">{{scope.row.website}}</el-link>
              </template>
          </el-table-column>
          <el-table-column align="center"  label="友链图标" prop="website">
                    <template slot-scope="scope">
                             <el-avatar :size="30" src="https://empty" @error="errorHandler">
                              <img :src="scope.row.iconUrl"/>
                        </el-avatar>
                    </template>
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="编辑此友链"
                :enterable="false"
                placement="top-start"
              >
                <el-button type="primary" icon="el-icon-edit" @click="editFriend(scope.row.id)"></el-button>
              </el-tooltip>

                  <el-tooltip
                class="item"
                effect="dark"
                content="删除此友链"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete" @click="deleteFriend(scope.row.id)"></el-button>
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
            @close="dialogClosed()"
            >



          <el-form :model="dlgForm" :rules="dlgFormRules" label-width="70px">

        <el-form-item v-if="dlgForm.id != ''" label="ID" prop="id">
          <el-input v-model="dlgForm.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="友链名称" prop="name">
          <el-input v-model="dlgForm.name"></el-input>
        </el-form-item>

        <el-form-item label="友链网址" prop="website">
            <el-input v-model="dlgForm.website"></el-input>
        </el-form-item>

         <el-form-item label="图标url" prop="iconUrl">
            <el-input v-model="dlgForm.iconUrl"></el-input>
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
    created() {
        this.getFriends();
    },
    data() {
        return {
            friendsList:[],
            queryParams: {
        page: 1,
        limit: 5
      },
      dialogTitle: '',
      total: 0,
      dialogVisible: false,
      dlgFormRules: {
           title: [
        ],
          
      },
      dlgForm: {
        id: '',
        name: '',
        website: '',
        iconUrl: ''
      },
        }
    },
    methods: {
        dialogClosed(){
            this.dlgForm = this.$g.clearParams(this.dlgForm);
            console.log("关闭");
            console.log(this.dlgForm);
        },
         handleSizeChange(newSize) {
         this.queryParams.limit = newSize;
         this.getFriends();
    },
    handleCurrentChange(newPage) {
         this.queryParams.page = newPage;
         this.getFriends();
        },
        getFriends(){
            const _this = this;
            this.$axios
        .get("friends", { params: _this.queryParams })
        .then(function(res) {
          if (res.data.code === 200) {
              _this.friendsList = res.data.data.list;
              _this.total = res.data.data.total;
            console.log(res.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
        },
        editFriend(id) {
           this.dialogTitle = "编辑友链";
           this.dlgForm.id = id;
            const _this = this;
           this.$axios.get("friends/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.dlgForm = res.data.data;
                            _this.dialogVisible = true;
                             console.log(_this.dlgForm);
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
           
        },
        addFriend() {
            this.dialogTitle = "添加友链";
            this.dialogVisible = true;
        },
        submit(){
              const _this = this;
              switch(this.dialogTitle){
                  case "编辑友链":
                      console.log("编辑友链");
                    this.$axios.put("friends/"
                    , _this.dlgForm
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("编辑友链成功");
                            _this.dialogVisible=false;
                            _this.getFriends();
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
                      break;
                  case "添加友链":
                       console.log("添加友链");
                        this.$axios.post("friends"
                        , _this.dlgForm
                        )
                        .then(function(res) {
                            if (res.data.code == 200) {
                                _this.$message.success("添加友链成功");
                                _this.dialogVisible=false;
                                _this.getFriends();
                            }
                        })
                        .catch(function(error) {
                            console.log(error);
                        });
                      break;
              }
        },deleteFriend(id) {
            const _this = this;
          this.$confirm('此操作将删除该友链, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete("friends/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getFriends();
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
        }
    }
}
</script>

<style>

</style>