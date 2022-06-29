<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Tên phim">
        <el-col :span="10">
          <el-input v-model="form.title" />
        </el-col>
      </el-form-item>
      <el-form-item label="Đạo diễn">
        <el-input v-model="form.director" />
      </el-form-item>
      <el-form-item label="Quốc gia">
        <el-select v-model="form.country">
          <el-option
            v-for="item in valueCountry"
            :key="item.code"
            :label="item.name"
            :value="item.code"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="Năm">
        <el-col :span="11">
          <el-date-picker v-model="form.year" type="date" placeholder="Chọn năm" style="width: 100%;" />
        </el-col>
      </el-form-item>
      <el-form-item label="Năm phát hành">
        <el-col :span="11">
          <el-date-picker v-model="form.release_year" type="date" placeholder="Chọn năm phát hành" style="width: 100%;" />
        </el-col>
      </el-form-item>
      <el-form-item label="Thể loại">
        <el-radio-group v-model="form.category">
          <el-radio v-for="item in valueCategory" :key="item.id" :label="item.name" :value="item.id" />
        </el-radio-group>
      </el-form-item>
      <el-form-item label="Trailer">
        <el-col :span="10">
          <el-input v-model="form.trailer" />
        </el-col>
      </el-form-item>
      <el-form-item label="Thumbnail">
        <el-col :span="10">
          <el-input v-model="form.thumbnail" />
        </el-col>
      </el-form-item>
      <el-form-item label="Loại">
        <el-col :span="10">
          <el-select v-model="form.type">
            <el-option
              v-for="item in valueType"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="Mô tả">
        <el-input v-model="form.description" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { getListCountry } from '@/api/country'
import { getListCategory } from '@/api/category'
import { createMovie } from '@/api/movie'

export default {
  data() {
    return {
      form: {
        title: null,
        category: null,
        country: null,
        director: null,
        year: null,
        description: null,
        release_year: null,
        trailer: null,
        thumbnail: null,
        type: null
      },
      valueType: [
        {
          value: 'film',
          label: 'Film'
        },
        {
          value: 'tv',
          label: 'TV'
        }
      ],
      valueCountry: [

      ],
      valueCategory: [

      ]
    }
  },
  mounted() {
    getListCountry().then(
      response => {
        if (response.code === 200) {
          this.valueCountry = response.data
        }
      }
    )

    getListCategory().then(response => {
      if (response.code === 200) {
        this.valueCategory = response.data
      }
    })
  },
  methods: {
    onSubmit() {
      for (let i = 0; i < this.valueCategory.length; i++) {
        if (this.valueCategory[i].name === this.form.category) {
          this.form.category = this.valueCategory[i].id
        }
      }

      this.form.year = new Date(this.form.year).getFullYear()
      this.form.release_year = new Date(this.form.release_year).getFullYear()

      createMovie(this.form).then(response => {
        if (response.code === 200) {
          this.$notify({
            message: 'Create success',
            type: 'success'
          })
          this.$router.push('/quanlyphim/list')
        }
      })
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

