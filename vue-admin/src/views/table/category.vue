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
      <el-table-column label="Tên thể loại" width="150">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Mô tả">
        <template slot-scope="scope">
          <span>{{ scope.row.description }}</span>
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
    <el-dialog title="Edit category" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="category" label-position="left" style="width: 400px; margin-left:50px;">
        <el-form-item label="Tên thể loại" prop="title">
          <el-input v-model="category.name" />
        </el-form-item>
        <el-form-item label="Mô tả" prop="description">
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
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getListCategory, updateCategory, deleteCategory } from '@/api/category'

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
      category: null,
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
      getListCategory().then(response => {
        this.list = response.data
        if (this.list.length > 0) {
          this.category = this.list[0]
        } else {
          this.category = {
            name: '',
            description: ''
          }
        }
        this.listLoading = false
      })
    },
    updateCategory(category) {
      updateCategory(category).then(
        response => {
          if (response.code === 200) {
            this.$notify({
              message: 'Update success',
              type: 'success'
            })
            this.dialogFormVisible = false
          }
        }
      )
    },
    handleDelete(index) {
      deleteCategory(
        this.list[index].id
      ).then(
        response => {
          if (response.code === 200) {
            this.$notify({
              message: 'Update success',
              type: 'success'
            })
            this.fetchData()
          }
        }
      )
    },
    handleUpdate(index) {
      this.category = this.list[index]
      this.dialogFormVisible = true
      this.dialogCreate = false
    }
  }
}
</script>
