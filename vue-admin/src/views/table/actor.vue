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
      <el-table-column label="Tên">
        <template slot-scope="scope">
          {{ scope.row.full_name }}
        </template>
      </el-table-column>
      <el-table-column label="Quốc gia" width="150" align="center">
        <template slot-scope="scope">
          <span v-if=" scope.row.country != undefined">{{ scope.row.country.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Châu lục" width="150" align="center">
        <template slot-scope="scope">
          <span v-if=" scope.row.country != undefined">{{ scope.row.country.continent }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Năm sinh" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.year_birthday }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Giới tính" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.gender }}</span>
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
    <el-dialog title="Edit actor" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :model="category" label-position="left" style="width: 400px; margin-left:50px;">
        <el-form-item label="Họ tên" prop="name">
          <el-input v-model="actor.full_name" />
        </el-form-item>
        <el-form-item label="Quốc gia" prop="country">
          <el-input v-model="actor.country.name" />
        </el-form-item>
        <el-form-item label="Năm sinh" prop="year_birthday">
          <el-input v-model="actor.year_birthday" />
        </el-form-item>
        <el-form-item label="Giới tính" prop="gender">
          <el-input v-model="actor.gender" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          Cancel
        </el-button>
        <el-button v-if="!dialogCreate" type="primary" @click="updateActor(category)">
          Update
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getListActor, updateActor, deleteActor } from '@/api/actor'

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
      actor: null,
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
      getListActor().then(response => {
        this.list = response.data
        if (this.list.length > 0) {
          this.actor = this.list[0]
        } else {
          this.actor = {
            name: '',
            description: ''
          }
        }
        this.listLoading = false
      })
    },
    updateActor(actor) {
      console.log(actor)
      updateActor(actor).then(
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
      deleteActor(
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
      this.actor = this.list[index]
      this.dialogFormVisible = true
      this.dialogCreate = false
    }
  }
}
</script>
