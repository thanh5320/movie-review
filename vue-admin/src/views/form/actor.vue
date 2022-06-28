<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="Họ Tên">
        <el-col :span="10">
          <el-input v-model="form.full_name" />
        </el-col>
      </el-form-item>
      <el-form-item label="Quốc gia">
        <el-col :span="10">
          <el-select v-model="form.country_code">
            <el-option
              v-for="item in valueCountry"
              :key="item.code"
              :label="item.name"
              :value="item.code"
            />
          </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="Năm sinh">
        <el-col :span="10">
          <el-input v-model="form.year_birthday" type="number" />
        </el-col>
      </el-form-item>
      <el-form-item label="Giới tính">
        <el-col :span="10">
          <el-select v-model="form.gender">
            <el-option
              v-for="item in valueGender"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">Create</el-button>
        <el-button @click="onCancel">Cancel</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { createActor } from '@/api/actor'
import { getListCountry } from '@/api/country';

export default {
  data() {
    return {
      form: {
        full_name: null,
        country_code: null,
        year_birthday: null,
        gender: 'male'
      },
      valueGender: [
        {
          value: 'male',
          label: 'Male'
        },
        {
          value: 'female',
          label: 'Female'
        }
      ],
      valueCountry: [

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
  },
  methods: {
    onSubmit() {
      createActor(this.form).then(
        response => {
          if (response.code === 200) {
            this.$notify({
              message: 'Create success',
              type: 'success'
            })
            this.$router.push('/quanlyphim/list_actor')
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
