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
      <el-table-column label="Mô tả phim">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column label="Đạo diễn" width="140" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.director }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Quốc gia" width="140" align="center">
        <template slot-scope="scope">
          <span v-if=" scope.row.country != undefined">{{ scope.row.country.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Thể loại" width="140" align="center">
        <template slot-scope="scope">
          <span v-if=" scope.row.category != undefined">{{ scope.row.category.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Đánh giá" width="85" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.rating }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="Năm phát hành" width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.release_year }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.$index)">
            Edit
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index)">
            Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- <el-dialog title="Edit category" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="category" label-position="left" style="width: 400px; margin-left:50px;">
        <el-form-item label="Name" prop="title">
          <el-input v-model="category.name" />
        </el-form-item>
        <el-form-item label="Description" prop="description">
          <el-input v-model="category.description" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button v-if="!dialogCreate" type="primary" @click="updateCategory(category)">
          Update
        </el-button>
        <el-button v-if="dialogCreate" type="primary" @click="createMovie(category)">
          Create
        </el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import { getListMovie } from '@/api/movie'

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
      movie: null,
      listLoading: true,
      dialogFormVisible: false,
      dialogCreate: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getListMovie().then(response => {
        this.list = response.data
        if (this.list.length > 0) {
          this.movie = this.list[0]
        } else {
          this.movie = {
            name: '',
            description: ''
          }
        }
        this.listLoading = false
      })
    },
    handleUpdate(index) {
      this.movie = this.list[index]
      this.dialogFormVisible = true
      this.dialogCreate = false
    },
    handleCreate() {
      this.movie = {
        name: null,
        description: null
      }
      this.dialogFormVisible = true
      this.dialogCreate = true
    }
  }
}
</script>
