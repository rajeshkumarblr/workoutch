var workouts = [];
var activities = [];


function findWorkout (workoutId) {
  return workouts[findWorkoutKey(workoutId)];
}

function findWorkoutKey (workoutId) {
  for (var key = 0; key < workouts.length; key++) {
    if (workouts[key].id == workoutId) {
      return key;
    }
  }
}

function findActivity (activityId) {
  return activities[findActivityKey(workoutId)];
}

function findActivityKey (activityId) {
  for (var key = 0; key < activities.length; key++) {
    if (activities[key].id == activityId) {
      return key;
    }
  }
}

var workoutService = {
  findAll(fn) {
    axios
      .get('/api/v1/workouts')
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
      .get('/api/v1/workouts/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  create(workout, fn) {
    axios
      .post('/api/v1/workouts', workout)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  update(id, workout, fn) {
    axios
      .put('/api/v1/workouts/' + id, workout)
      .then(response => fn(response))
      .catch(error => console.log(error))
  },

  deleteWorkout(id, fn) {
    axios
      .delete('/api/v1/workouts/' + id)
      .then(response => fn(response))
      .catch(error => console.log(error))
  }
}

var activityService = {
  findAll(fn) {
    axios
        .get('/api/v1/activities')
        .then(response => fn(response))
  .catch(error => console.log(error))
  },

  findById(id, fn) {
    axios
        .get('/api/v1/activities/' + id)
        .then(response => fn(response))
  .catch(error => console.log(error))
  },

  create(workout, fn) {
    axios
        .post('/api/v1/activities', workout)
        .then(response => fn(response))
  .catch(error => console.log(error))
  },

  update(id, workout, fn) {
    axios
        .put('/api/v1/activities/' + id, workout)
        .then(response => fn(response))
  .catch(error => console.log(error))
  },

  deleteWorkout(id, fn) {
    axios
        .delete('/api/v1/activities/' + id)
        .then(response => fn(response))
  .catch(error => console.log(error))
  }
}

var List = Vue.extend({
  template: '#workout-list',
  data: function () {
    return {workouts: [], searchKey: '', activities: []};
  },
  computed: {
    filteredWorkouts() {
      /*return this.workouts.filter((workout) => {
      	return  workout.activity.indexOf(this.searchKey) > -1
      	  || workout.calories.toString().indexOf(this.searchKey) > -1
            || workout.distance.toString().indexOf(this.searchKey) > -1
      })*/
      return this.workouts;
    }
  },
  mounted() {
    workoutService.findAll(r => {this.workouts = r.data; workouts = r.data})
    activityService.findAll(r => {
      activities = r.data;
      console.log(activities)
    })
  }
});

var Workout = Vue.extend({
  template: '#workout',
  data: function () {
    return {workout: findWorkout(this.$route.params.workout_id)};
  }
});

var WorkoutEdit = Vue.extend({
  template: '#workout-edit',
  data: function () {
    return {workout: findWorkout(this.$route.params.workout_id)};
  },
  methods: {
    updateWorkout: function () {
      workoutService.update(this.workout.id, this.workout, r => router.push('/'))
    }
  }
});

var WorkoutDelete = Vue.extend({
  template: '#workout-delete',
  data: function () {
    return {workout: findWorkout(this.$route.params.workout_id)};
  },
  methods: {
    deleteWorkout: function () {
      workoutService.deleteWorkout(this.workout.id, r => router.push('/'))
    }
  }
});

AddWorkout = Vue.extend({
  template: '#add-workout',
  data() {
    return {
      workout: {activity: {}, calories: 0, distance: 0}
    }
  },
  methods: {
    createWorkout() {
      var myJSON = JSON.stringify(this.workout);
      console.log("createWorkout: " + myJSON)
      workoutService.create(this.workout, r => router.push('/'))
    }
  },
  computed: {
    allActivities() {
      return activities;
    }
  },
  mounted() {
  }
});

var router = new VueRouter({
	routes: [
		{path: '/', component: List},
		{path: '/workout/:workout_id', component: Workout, name: 'workout'},
		{path: '/add-workout', component: AddWorkout},
		{path: '/workout/:workout_id/edit', component: WorkoutEdit, name: 'workout-edit'},
		{path: '/workout/:workout_id/delete', component: WorkoutDelete, name: 'workout-delete'}
	]
});

new Vue({
  router
}).$mount('#app')
