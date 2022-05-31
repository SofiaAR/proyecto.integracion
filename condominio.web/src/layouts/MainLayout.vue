<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
        />

        <q-toolbar-title>
          Condominio App
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-drawer
      show-if-above
      bordered
    >
      <q-scroll-area class='fit'>
        <q-list class='q-mx-sm q-mt-sm' separator>
        <q-item clickable v-for="(item, index) in linksList" :key="index" :to="item.to">
          <q-item-section avatar>
            <q-icon :name='item.icon'></q-icon>
          </q-item-section>
          <q-item-section>
            {{ item.title }}
          </q-item-section>
        </q-item>
      </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">

const linksList = [
  {
    title: 'Condominios',
    caption: 'quasar.dev',
    icon: 'business',
    to: 'condominium'
  },
  {
    title: 'Usuarios',
    icon: 'code',
    to: 'users'
  },
];

</script>
