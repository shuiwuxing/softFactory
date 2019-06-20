<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.login_id }}
        </template>
      </el-table-column>
      <el-table-column label="真实姓名" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年龄" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.age }}
        </template>
      </el-table-column>
      <!-- <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column align="center" prop="created_at" label="地址" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.address }}</span>
        </template>
      </el-table-column>
    </el-table>
     <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :current-page="currentPage"
        :page-size="2"
        @current-change="handleCurrentChange"
       >
      </el-pagination>
  </div>
</template>

<script>
import { list } from '@/api/user'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: '男',
        2: '女'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      currentPage:1,
      params:{
        list:{
           "pageNum":"1", 
           "pageSize":"2"
        }

      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    handleCurrentChange(val) {
      this.currentPage = val
      this.params.list.pageNum=val
      this.fetchData()
    },
    fetchData() {
      this.listLoading = true
      list(this.params.list).then(response => {
        this.list = response.data.list
        this.total=response.data.total
        this.listLoading = false
      })
    }
  }
}
</script>
