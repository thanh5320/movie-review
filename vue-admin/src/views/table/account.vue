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
          {{ scope.row.full_name }}
        </template>
      </el-table-column>
      <el-table-column label="Số điện thoại" width="130" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phone_number }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Email" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.email }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Username" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
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
    <el-dialog title="Edit movie" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="user" label-position="left" style="width: 400px; margin-left:50px;">
        <el-form-item label="Full name" prop="title">
          <el-input v-model="user.full_name" />
        </el-form-item>
        <el-form-item label="Email" prop="description">
          <el-input v-model="user.email" />
        </el-form-item>
        <el-form-item label="Phone number" prop="description">
          <el-input v-model="user.phone_number" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button type="primary" @click="updateUser(user)">
          Update
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { updateUser, deleteUser, listingUser} from '@/api/user'

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
      listingUser().then(response => {
        this.list = response.data
        if (this.list.length > 0) {
          this.user = this.list[0]
        } else {
          this.user = {
            name: '',
            description: ''
          }
        }
        this.listLoading = false
      })
    },
    updateUser(user) {
      updateUser(user).then(
        response => {
          if (response.code === 200) {
            this.$notify({
              message: 'Update success',
              type: 'success'
            })
            this.dialogFormVisible = false
          }
          this.fetchData()
        }
      )
    },
    handleDelete(index) {
      deleteUser(
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
      this.user = this.list[index]
      this.dialogFormVisible = true
      this.dialogCreate = false
    }
  }
}
</script>
