<template>
    <b-tr class="text-center">
      <b-th>
        <router-link :to="{ name: 'TripPlan', params: { planName: planName } }">{{ planName }}</router-link>
      </b-th>
      <b-td>{{ startDay }}</b-td>
    </b-tr>
    
  </template>
  
  <script>
//   import moment from "moment";
  
  export default {
    name: "planHeaderListItem",
    props: {
      planName: String,
      startDay: String,
    },
  };
  </script>
  
  <style></style>
  