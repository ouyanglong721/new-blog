import Vue from "vue";
import VueRouter from "vue-router";

import Login from "../components/Login.vue";
import Home from "../components/Home";
import Welcome from "../components/Welcome"
import Users from "../components/user/users"
import Articles from "../components/article/articles"
import Axios from "axios";

Vue.use(VueRouter);

const router = new VueRouter({
  routes: [
    { path: "/", redirect: "/login" },
    { path: "/login", component: Login },
    { 
    path: "/home", 
    component: Home, 
    redirect: '/welcome',
    children: [
      {path:'/welcome', component: Welcome},
      {path:'/users', component: Users},
      {path:'/articles', component: Articles}
  ] 
    
  }
  ],
});

router.beforeEach((to, from, next) => {
  if (to.path === "/login") return next();
  const token = window.sessionStorage.getItem("token");
  if (!token) {
    next("/login");
  }
  Axios.get("auth/token", {
    params: {
      token: token,
    },
  })
    .then(function(res) {
      if (res.data.code != 200) {
        window.sessionStorage.removeItem('token');
        next("/login");
      }
      else next();
    })
    .catch(function(error) {
      console.log(error);
    });
});

export default router;
