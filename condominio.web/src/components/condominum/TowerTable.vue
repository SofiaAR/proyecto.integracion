<template>
  <q-table
    title="Torres"
    bordered
    :rows="towers"
    :columns="columns"
    row-key="identification"
  >
    <template v-slot:header="props">
      <q-tr :props="props">
        <q-th auto-width/>
        <q-th
          v-for="col in props.cols"
          :key="col.name"
          :props="props"
        >
          {{ col.label }}
        </q-th>
      </q-tr>
    </template>

    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td auto-width>
          <q-btn size="sm" color="primary" round dense @click="props.expand = !props.expand"
                 :icon="props.expand ? 'remove' : 'add'"/>
        </q-td>
        <q-td
          v-for="col in props.cols"
          :key="col.name"
          :props="props"
        >
          {{ col.value }}
        </q-td>
      </q-tr>
      <q-tr v-if="props.expand" :props="props">
        <q-td colspan="100%">
          <DepartmentTable :tower-id="props.row.id"/>
        </q-td>
      </q-tr>
    </template>
  </q-table>
</template>

<script setup lang="ts">

import DepartmentTable from 'components/condominum/DepartmentTable.vue';
import {onMounted, ref} from 'vue';
import {condominiumApi} from 'boot/axios';

const props = defineProps({
  condominioId: Number
})

const towers = ref([]);

const columns = [
  {
    name: 'name',
    label: 'Nombre',
    align: 'left',
    field: 'identification',
    sortable: true
  },
  {
    name: 'actions',
    label: '',
    align: 'center',
  },
];

onMounted(() => {
  findAllByCondominioId();
})

const findAllByCondominioId = () => {
  condominiumApi.get(`/towers/condominio/${props.condominioId}`).then((response) => {
    towers.value = response.data;
  });
}
</script>
