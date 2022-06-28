<template>
  <div class="app-container">
    <el-form ref="form" :model="category" label-width="120px">
      <el-form-item label="Tên thể loại">
        <el-col :span="10">
          <el-input v-model="category.name" />
        </el-col>
      </el-form-item>
      <el-form-item label="Mô tả">
        <el-input v-model="category.description" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit(category)">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { createCategory } from '@/api/category'
export default {
  data() {
    return {
      category: {
        name: '',
        description: ''
      }
    }
  },
  methods: {
    onSubmit(category) {
      this.category = {
        name: null,
        description: null
      }
      createCategory(category).then(
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

