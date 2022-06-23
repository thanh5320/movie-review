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
      <el-table-column label="Tên phim">
        <template slot-scope="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>
      <el-table-column label="Đạo diễn" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Diễn viên" width="110" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column label="Quốc gia" width="110" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column label="Thể loại" width="80" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column label="Đánh giá" width="85" align="center">
        <!-- <template slot-scope="scope">
        </template> -->
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="Ngày phát hành" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.display_time }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getList } from '@/api/table'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList().then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    }
  }
}
</script>
