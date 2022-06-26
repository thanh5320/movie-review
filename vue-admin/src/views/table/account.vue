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
      <el-table-column align="center" label="ID" width="40">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column label="Họ và tên" width="220">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="Số điện thoại" width="130" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Email" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column label="Username" width="150" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column label="Password" width="150" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        active: 'success',
        locked: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      user: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  }
}
</script>
