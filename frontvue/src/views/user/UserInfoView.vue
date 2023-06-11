<template>
  <div>
    <b-table :items="tableData" :fields="tableFields">
      <template #cell(name)="row">
        <div>{{ row.label }}</div>
        <div>{{ row.value }}</div>
      </template>
      <template #cell(age)="row">
        <div>{{ row.label }}</div>
        <div>{{ row.value }}</div>
      </template>
      <template #cell(gender)="row">
        <div>{{ row.label }}</div>
        <div>{{ row.value }}</div>
      </template>
    </b-table>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        tableData: [
          { name: "John Doe", age: 32, gender: "Male" },
          { name: "Jane Smith", age: 28, gender: "Female" },
          { name: "Mike Johnson", age: 41, gender: "Male" },
        ],
        tableFields: [
          { key: "name", label: "Name" },
          { key: "age", label: "Age" },
          { key: "gender", label: "Gender" },
        ],
      };
    },
  };
</script>
