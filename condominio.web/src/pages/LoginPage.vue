<template>
  <q-page class="row items-center justify-evenly">
    Este es el login
    <q-btn label="login" @click="login" />
  </q-page>
</template>

<script setup lang="ts">

import {ref} from 'vue';
import {useRouter} from 'vue-router';

const router = useRouter();
const username = ref();
const password = ref();

const login = () => {
  console.log(username)
  console.log(password)
  router.push('/index')
}

</script>

<style scoped>

</style>
