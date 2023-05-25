<template>
  <b-tr class="text-center">
    <b-td>{{ articleNo }}</b-td>
    <b-th>
      <router-link :to="{ name: 'boardDetail', params: { articleNo: articleNo } }">{{ subject }}</router-link>
    </b-th>
    <b-td>{{ id }}</b-td>
    <b-td>{{ registerTime | dateFormat }}</b-td>
    <b-td>{{ hit }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    articleNo: Number,
    id: String,
    subject: String,
    hit: Number,
    registerTime: String,
  },
  filters: {
    dateFormat(registerTime) {
      return moment(new Date(registerTime)).format("YY.MM.DD HH:mm");
    },
  },
};
</script>

<style></style>
