<template>
  <div class="q-pa-md">
    <q-table
      bordered
      :rows="users"
      :columns="columns"
      row-key="name"
    >
      <template v-slot:top-left>
        <div class="row q-pa-sm">
          <div class="text-h6 col-6">Usuarios</div>
        </div>
      </template>
      <template v-slot:top-right>
        <q-btn label="crear usuario" color="primary" @click="openNewUserDialog"/>
      </template>
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn icon="edit" dense flat round color="primary" @click="openEditDialog(props.row)"/>
          <q-btn icon="delete" dense flat round color="negative" @click="openDeleteDialog(props.row)"/>
        </q-td>
      </template>
    </q-table>
  </div>
  <q-dialog v-model="newUserDialog">
    <q-card style="width: 600px; max-width: 80vw">
      <q-card-section>
        <div class="text-h6">Nuevo usuario</div>
      </q-card-section>
      <q-separator/>
      <q-card-section>
        <div class="q-col-gutter-sm">
          <q-input dense filled v-model="newUser.dni" label="RUT"/>
          <q-input dense filled v-model="newUser.name" label="Nombre"/>
          <q-input dense filled v-model="newUser.lastName" label="Apellido"/>
          <q-input dense filled v-model="newUser.email" label="Email"/>
        </div>
      </q-card-section>
      <q-card-actions align="right">
        <q-btn label="cerrar" @click="newUserDialog = false"></q-btn>
        <q-btn label="guardar" @click="save" color="primary"></q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>
  <q-dialog v-model="dialogDelete">
    <q-card style="width: 400px; max-width: 80vw">
      <q-card-section>
        <div class="text-h6">Eliminar usuario</div>
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
        <q-btn label="eliminar usuario" @click="deleteUser" color="negative"></q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script setup lang="ts">

import {onMounted, ref} from 'vue';
import {userApi} from 'boot/axios';
import {User} from 'components/models';

const columns = [
  {
    name: 'dni',
    label: 'RUT',
    align: 'left',
    field: 'dni',
    sortable: true
  },
  {
    name: 'name',
    label: 'Nombre',
    align: 'left',
    field: 'name',
    sortable: true
  },
  {
    name: 'lastName',
    label: 'Apellido',
    align: 'left',
    field: 'lastName',
    sortable: true
  },
  {
    name: 'email',
    label: 'Email',
    align: 'left',
    field: 'email',
    sortable: true
  },
  {
    name: 'actions',
    label: '',
    align: 'center',
  },
];

const users = ref();
const newUserDialog = ref(false);
const dialogDelete = ref(false);
const userSelected = ref();

const newUser = ref(new User());

onMounted(() => findAll());

const findAll = () => {
  userApi.get('/users/all').then((response) => {
    users.value = response.data;
  });
}

const save = () => {
  if (newUser.value.id) {
    userApi.put('/users/update', newUser.value).then(() => {
      newUserDialog.value = false;
      findAll();
    });
  } else {
    userApi.post('/users/save', newUser.value).then(() => {
      newUserDialog.value = false;
      findAll();
    });
  }
}


const openEditDialog = (user: User) => {
  newUser.value = user;
  newUserDialog.value = true;
}

const openNewUserDialog = () => {
  newUser.value = new User();
  newUserDialog.value = true;
}

const openDeleteDialog = (user: User) => {
  userSelected.value = user;
  dialogDelete.value = true;
}

const deleteUser = () => {
  userApi.delete(`/users/${userSelected.value.id}`).then(() => {
    dialogDelete.value = false;
    findAll();
  });
}

</script>

<style scoped>

</style>
