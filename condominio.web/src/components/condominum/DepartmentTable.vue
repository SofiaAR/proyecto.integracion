<template>
  <q-table
    title="Departamentos"
    bordered
    :rows="departments"
    :columns="columns"
    row-key="identification"
  >
  </q-table>
</template>

<script setup lang="ts">

import {Department} from 'components/models';
import {onMounted, ref} from 'vue';
import {condominiumApi} from 'boot/axios';

const props = defineProps({
  towerId: Number
})

const columns = [
  {
    name: 'name',
    label: 'N Departamento',
    align: 'left',
    field: 'number',
    sortable: true
  },
  {
    name: 'habitante',
    label: 'Habitante',
    align: 'left',
    field: (row: Department) => {
      if (row.user) {
        return `${row.user.name} ${row.user.dni}` ;
      } else {
        return 'NO HABITADO'
      }
    },
    sortable: true
  },
  {
    name: 'actions',
    label: '',
    align: 'center',
  },
];

const departments = ref([]);

onMounted(() => {
  findAllByTowerId();
})

const findAllByTowerId = () => {
  condominiumApi.get(`/departments/tower/${props.towerId}`).then((response) => {
    departments.value = response.data;
  });
}
</script>
