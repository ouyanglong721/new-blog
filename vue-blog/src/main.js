import Vue from "vue";
import App from "./App.vue";
import router from "./router";

import "./plugins/element.js";


import "./assets/css/global.css";

import axios from "axios";


import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
    // use
Vue.use(mavonEditor)

import g from './global'

Vue.prototype.$g = g



import { Message } from 'element-ui'

Vue.prototype.$axios = axios; //全局注册，使用方法为:this.$axios


axios.defaults.baseURL = g.baseUrl;

axios.interceptors.request.use(config => {
  config.headers.token = window.sessionStorage.getItem('token');
  return config;
})


// http response 拦截器
axios.interceptors.response.use(
  response => {
    if (response.data.code === 200) {
      return response
    }
    
    //拦截响应，做统一处理 
    if (response.data.code === 3001) {
      Message.warning("登入状态失效,请重新登入");
      window.sessionStorage.removeItem('token');
      window.location.href = '/login'
    } else {
      console.log(response.data)
      Message.error(response.data.msg);
    }
      
    console.log("下一步")
    return response;
  },
  //接口错误状态处理，也就是说无响应时的处理
  error => {
    console.log("error:"+error);
    return Promise.reject(error.response) // 返回接口返回的错误信息
  })

Vue.config.productionTip = false;

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
