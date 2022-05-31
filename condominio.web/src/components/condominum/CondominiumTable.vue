<template>
  <div class="q-pa-md">
    <q-table
      bordered
      :rows="condominiums"
      :columns="columns"
      row-key="name"
    >
      <template v-slot:top-left>
        <div class="row q-pa-sm">
          <div class="text-h6 col-6">Condominios</div>
        </div>
      </template>
      <template v-slot:top-right>
        <q-btn label="crear condominio" color="primary" @click="openNewCondiminioDialog"/>
      </template>
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
            <template v-if="col.name === 'actions'">
              <div class='text-center'>
                <q-btn icon="edit" dense flat round color="primary" @click="openEditDialog(props.row)"/>
                <q-btn icon="delete" dense flat round color="negative" @click="openDeleteDialog(props.row)"/>
              </div>
            </template>
            <template v-else>
              {{ col.value }}
            </template>
          </q-td>
        </q-tr>
        <q-tr v-if="props.expand" :props="props">
          <q-td colspan="100%">
            <TowerTable :condominio-id="props.row.id"/>
          </q-td>
        </q-tr>
      </template>
    </q-table>
  </div>
  <q-dialog v-model="newCondominioDialog">
    <q-card style="width: 600px; max-width: 80vw">
      <q-card-section>
        <div class="text-h6">Nuevo condominio</div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <div class="q-col-gutter-sm">
          <q-input dense filled v-model="newCondominio.name" label="Nombre"/>
          <q-input dense filled v-model="newCondominio.address" label="Dirección"/>
        </div>
      </q-card-section>
      <q-card-section>
        <div class="text-h6">
          Torres del condominio
          <q-btn label="agregar" color="primary" @click="openNewTowerDialog"/>
        </div>
        <div>
          <q-list bordered separator>
            <q-item v-for="(tower, index) in newCondominio.towers" :key="index">
              <q-item-section>
                <q-item-label>{{ `Nombre: ${tower.identification} | Departamentos: ${tower.departments.length}` }}</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </div>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn label="cerrar" @click="newCondominioDialog = false"></q-btn>
        <q-btn label="guardar" @click="save" color="primary"></q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>
  <q-dialog v-model="dialogDelete">
    <q-card style="width: 400px; max-width: 80vw">
      <q-card-section>
        <div class="text-h6">Eliminar condominio</div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <q-banner class="bg-grey-3">
          <template v-slot:avatar>
            <q-icon name="warning" color="warning"/>
          </template>
          Por favor confirme su decisión
        </q-banner>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn label="cancelar" @click="dialogDelete = false"></q-btn>
        <q-btn label="eliminar condominio" @click="deleteUser" color="negative"></q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>
  <q-dialog v-model="towerDialog">
    <q-card style="width: 300px; max-width: 80vw">
      <q-card-section>
        <div class="text-h6">Nueva torre</div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <div class="q-col-gutter-sm">
          <q-input dense filled v-model="newTower.identification" label="Nombre"/>
          <q-input dense filled v-model="departmentsQuantity" type="number" maxlength="6" label="Cantidad de departmentos"/>
        </div>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn label="cancelar" @click="towerDialog = false"></q-btn>
        <q-btn label="guardar" @click="addNewTowerToCondominio" color="primary"></q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>

</template>

<script setup lang="ts">

import {onMounted, ref} from 'vue';
import {condominiumApi} from 'boot/axios';
import {Condominio, Department, Tower} from 'components/models';
import TowerTable from 'components/condominum/TowerTable.vue';

const columns = [
  {
    name: 'name',
    label: 'Nombre',
    align: 'left',
    field: 'name',
    sortable: true
  },
  {
    name: 'address',
    label: 'Direccion',
    align: 'left',
    field: 'address',
    sortable: true
  },
  {
    name: 'actions',
    label: '',
    align: 'center',
  },
];

const condominiums = ref();
const newCondominioDialog = ref(false);
const dialogDelete = ref(false);
const towerDialog = ref(false);
const condominioSelected = ref();

const newCondominio = ref(new Condominio());
const newTower = ref(new Tower());
const departmentsQuantity = ref();

onMounted(() => findAll());

const findAll = () => {
  condominiumApi.get('/condominiums').then((response) => {
    condominiums.value = response.data;
  });
}

const save = () => {
  if (newCondominio.value.id) {
    condominiumApi.put('/condominiums', newCondominio.value).then(() => {
      newCondominioDialog.value = false;
      findAll();
    });
  } else {
    condominiumApi.post('/condominiums', newCondominio.value).then(() => {
      newCondominioDialog.value = false;
      findAll();
    });
  }
}

const addNewTowerToCondominio = () => {
  for (let i = 0; i < departmentsQuantity.value; i++) {
    const dept = new Department();
    dept.number = i;
    newTower.value.departments.push(dept);
  }
  newCondominio.value.towers.push(newTower.value);
  departmentsQuantity.value = null;
  towerDialog.value = false;
}

const openNewTowerDialog = () => {
  newTower.value = new Tower();
  towerDialog.value = true;
}


const openEditDialog = (user: Condominio) => {
  newCondominio.value = user;
  newCondominioDialog.value = true;
}

const openNewCondiminioDialog = () => {
  newCondominio.value = new Condominio();
  newCondominioDialog.value = true;
}

const openDeleteDialog = (user: Condominio) => {
  condominioSelected.value = user;
  dialogDelete.value = true;
}

const deleteUser = () => {
  condominiumApi.delete(`/condominiums/${condominioSelected.value.id}`).then(() => {
    dialogDelete.value = false;
    findAll();
  });
}

</script>

<style scoped>

</style>
