<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Username">
        <el-col :span="10">
          <el-input v-model="form.username" />
        </el-col>
      </el-form-item>
      <el-form-item label="Password">
        <el-col :span="10">
          <el-input v-model="form.password" type="password" />
        </el-col>
      </el-form-item>
      <el-form-item label="Email">
        <el-col :span="10">
          <el-input v-model="form.email" />
        </el-col>
      </el-form-item>
      <el-form-item label="Role">
        <el-col :span="10">
          <el-input v-model="form.roles" />
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit(form)">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { createUser } from '@/api/user'
export default {
  data() {
    return {
      form: {
        username: '',
        password: '',
        email: '',
        roles: ''
      }
    }
  },
  methods: {
    onSubmit(form) {
      this.form = {
        username: null,
        password: null,
        email: null,
        roles: null
      }
      createUser(form).then(
        response => {
          if (response.code === 200) {
            this.$notify({
              message: 'Create success',
              type: 'success'
            })
          }
        }
      )
    },
    onCancel() {
      this.$message({
        message: 'cancel!',
        type: 'warning'
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

