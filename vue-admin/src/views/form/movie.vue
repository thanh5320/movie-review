<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Tên phim">
        <el-col :span="10">
          <el-input v-model="form.name" />
        </el-col>
      </el-form-item>
      <el-form-item label="Đạo diễn">
        <el-col :span="10">
          <el-input v-model="form.name" />
        </el-col>
      </el-form-item>
      <el-form-item label="Diễn viên">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="Quốc gia">
        <el-select v-model="form.region" placeholder="Chọn quốc gia">
          <el-option label="Việt Nam" value="vn" />
          <el-option label="Hàn Quốc" value="hai" />
          <el-option label="Mỹ" value="hai" />
          <el-option label="Nhật" value="hai" />
          <el-option label="Trung Quốc" value="hai" />
        </el-select>
      </el-form-item>
      <el-form-item label="Ngày phát hành">
        <el-col :span="11">
          <el-date-picker v-model="form.date1" type="date" placeholder="Chọn ngày phát hành phim" style="width: 100%;" />
        </el-col>
      </el-form-item>
      <el-form-item label="Thể loại">
        <el-radio-group v-model="form.type">
          <el-radio label="Hành động" name="type" />
          <el-radio label="Hài" name="type" />
          <el-radio label="Lãng mạn" name="type" />
          <el-radio label="Cổ trang" name="type" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Mô tả">
        <el-input v-model="form.desc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      }
    }
  },
  methods: {
    onSubmit() {
      this.$message('submit!')
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

