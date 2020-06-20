import Vue from "vue";
import VueRouter from "vue-router";

import Login from "../components/Login.vue";
import Home from "../components/Home";
import Welcome from "../components/Welcome"
import Users from "../components/user/users"
import Articles from "../components/article/articles"
import Categories from "../components/categories/categories"
import Comments from "../components/comments/comments"
import Friends from "../components/friends/friends"
import Notice from "../components/notice/notice"
import Settings from "../components/settings/settings"
import Editor from "../components/article/editor"
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
      {path:'/articles', component: Articles},
      {path:'/categories', component: Categories},
      {path:'/comments', component: Comments},
      {path:'/friends', component: Friends},
      {path:'/notices', component: Notice},
      {path:'/settings', component: Settings},
      {path:'/write', component: Editor}
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
