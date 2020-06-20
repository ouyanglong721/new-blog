<template>
    <div>
          <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home'}">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/notices'}">通知管理</el-breadcrumb-item>
    </el-breadcrumb>




    
      <!-- 卡片 -->
    <el-card class="box-card">
      <div class="text item">
        <el-button type="primary" @click="addNotice">添加公告</el-button>
        <el-table :data="noticesList" border stripe>
          <el-table-column align="center" type="index" label="#"></el-table-column>
          <el-table-column align="center" sortable label="id" width="100px" prop="id"></el-table-column>
          <el-table-column align="center" sortable label="公告标题" prop="title"></el-table-column>
          <el-table-column align="center"  label="公告内容">
              <template slot-scope="scope">
                    <el-popover
                    @show="showUserMsg(scope.row.nickname,scope.row.email,scope.row.website)"
                    placement="top-start"
                    title="公告内容"
                    width="200"
                    trigger="hover"
                    :content="scope.row.content">
                    <el-link slot="reference">查看公告内容<i class="el-icon-view el-icon--right"></i> </el-link>
                </el-popover>
              </template>
          </el-table-column>
          <el-table-column align="center"  label="开始时间" prop="startTime">
               <template slot-scope="scope">
                    <el-link v-if="scope.row.endTime == null" type="success">长期有效</el-link>
                    <span v-else>{{scope.row.endTime}}</span>
              </template>
          </el-table-column>
          <el-table-column align="center"  label="结束时间" prop="endTime">
              <template slot-scope="scope">
                    <el-link v-if="scope.row.endTime == null" type="success">长期有效</el-link>
                    <span v-else>{{scope.row.endTime}}</span>
              </template>
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-tooltip
                class="item"
                effect="dark"
                content="编辑此公告"
                :enterable="false"
                placement="top-start"
              >
                <el-button type="primary" icon="el-icon-edit" @click="editNotice(scope.row.id)"></el-button>
              </el-tooltip>

                  <el-tooltip
                class="item"
                effect="dark"
                content="删除此公告"
                :enterable="false"
                placement="top-end"
              >
                <el-button type="danger" icon="el-icon-delete" @click="deleteNotice(scope.row.id)"></el-button>
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



          <el-form :model="dlgForm" :rules="dlgFormRules" ref="myNoticDlgFormRef" label-width="70px">

        <el-form-item v-if="dlgForm.id != ''" label="ID" prop="id">
          <el-input v-model="dlgForm.id" disabled></el-input>
        </el-form-item>

        <el-form-item label="公告标题" prop="title">
          <el-input v-model="dlgForm.title"></el-input>
        </el-form-item>

        <el-form-item label="公告内容" prop="content">
            <el-input type="textarea" v-model="dlgForm.content"></el-input>
        </el-form-item>
         
            <el-form-item label="时间">
                <el-tooltip class="item" effect="dark" content="时间留空表示长期有效" placement="top">
                    <el-date-picker
                    @change="dateChanged"
                    v-model="dlgForm.time"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    format="yyyy-MM-dd HH:mm:ss"
                    type="datetimerange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                    </el-date-picker>
                </el-tooltip>
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
        this.getNotices();
    },
    data() {
        return {
            noticesList:[],
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
        content: '',
        startTime: '',
        endTime: '',
        title: '',
        time: []
      },
        }
    },
    methods: {
        dialogClosed(){
            this.dlgForm = this.$g.clearParams(this.dlgForm);
            console.log("关闭");
            console.log(this.dlgForm);
        },
        dateChanged(){
                if(this.dlgForm.time != null){
                    this.dlgForm.startTime = this.dlgForm.time[0];
                    this.dlgForm.endTime = this.dlgForm.time[1];
                } else {
                    this.dlgForm.startTime = '';
                    this.dlgForm.endTime = '';
                }
                console.log("时间改变")
                console.log(this.dlgForm);
     
        },
         handleSizeChange(newSize) {
        this.queryParams.limit = newSize;
         this.getNotices();
    },
    handleCurrentChange(newPage) {
         this.queryParams.page = newPage;
         this.getNotices();
        },
        getNotices(){
            const _this = this;
            this.$axios
        .get("notices", { params: _this.queryParams })
        .then(function(res) {
          if (res.data.code === 200) {
              _this.noticesList = res.data.data.list;
              _this.total = res.data.data.total;
            console.log(res.data);
          }
        })
        .catch(function(error) {
          console.log(error);
        });
        },
        editNotice(id) {
           this.dialogTitle = "编辑公告";
           this.dlgForm.id = id;
            const _this = this;
           this.$axios.get("notices/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            
                            _this.dlgForm = res.data.data;
                            _this.dialogVisible = true;
                            Vue.set(_this.dlgForm, 'time', []);
                             console.log(_this.dlgForm);
                            
                            _this.dlgForm.time[0] = res.data.data.startTime;
                            _this.dlgForm.time[1] = res.data.data.endTime;

                            console.log(_this.dlgForm.time);
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
           
        },
        addNotice() {
            this.dialogTitle = "添加公告";
            this.dialogVisible = true;
        },
        submit(){
              const _this = this;
              switch(this.dialogTitle){
                  case "编辑公告":
                      console.log("编辑公告");
                    this.$axios.put("notices/"
                    , _this.dlgForm
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("编辑公告成功");
                            _this.dialogVisible=false;
                            _this.getNotices();
                        }
                    })
                    .catch(function(error) {
                        console.log(error);
                    });
                      break;
                  case "添加公告":
                       console.log("添加公告");
                    //    _this.dlgForm.startTime = _this.dlgForm.time[0];
                    //    _this.dlgForm.endTime = _this.dlgForm.time[1];
                        this.$axios.post("notices"
                        , _this.dlgForm
                        )
                        .then(function(res) {
                            if (res.data.code == 200) {
                                _this.$message.success("添加公告成功");
                                _this.dialogVisible=false;
                                _this.getNotices();
                            }
                        })
                        .catch(function(error) {
                            console.log(error);
                        });
                      break;
              }
        },deleteNotice(id) {
            const _this = this;
          this.$confirm('此操作将删除该公告, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.delete("notices/"+id
                    , {}
                    )
                    .then(function(res) {
                        if (res.data.code == 200) {
                            _this.$message.success("删除成功");
                            _this.getNotices();
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