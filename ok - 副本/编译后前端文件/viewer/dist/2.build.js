webpackJsonp([2],{203:function(t,a,i){"use strict";function e(t){i(229)}Object.defineProperty(a,"__esModule",{value:!0});var s=i(213),n=i(231),c=i(82),o=e,l=c(s.a,n.a,!1,o,"data-v-09ee1df4",null);a.default=l.exports},213:function(t,a,i){"use strict";var e=i(83),s=i.n(e);a.a={data:function(){return{currpage:"成果展示",menu:["成果展示"],article:{},list:[],page:{},title:"",mainText:""}},created:function(){this.loadData("成果展示");var t=this;s.a.post("/api/index/intro/get",{id:5}).then(function(a){console.log(a),200==a.data.code&&(t.title=a.data.data.title,t.mainText=a.data.data.content)}).catch(function(t){console.log(t)})},methods:{loadData:function(t){this.currpage=t}}}},229:function(t,a,i){var e=i(230);"string"==typeof e&&(e=[[t.i,e,""]]),e.locals&&(t.exports=e.locals);i(85)("28d085b1",e,!0,{})},230:function(t,a,i){a=t.exports=i(84)(!1),a.push([t.i,".zy_newsxq[data-v-09ee1df4]{display:flex;flex-direction:column;width:100%;height:auto;justify-content:center}.title[data-v-09ee1df4]{font-size:24px;font-weight:600;color:#333;text-align:center;margin:20px 0;padding:0 0 20px;border-bottom:1px solid #ddd}.main[data-v-09ee1df4]{width:100%}",""])},231:function(t,a,i){"use strict";var e=function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticStyle:{margin:"30px 0 0 0"}},[i("div",{staticClass:"gonggao bdb_d fix"},[i("div",{staticClass:"txtScroll-top l fix zy_cur"},[t._m(0),t._v(" "),i("p",{staticClass:"l"},[i("router-link",{attrs:{to:"/index"}},[t._v("首页")]),i("span",[t._v("成果展示")])],1)])]),t._v(" "),i("div",{staticClass:"zy_contentbox"},[i("div",{staticClass:"zy_content fix"},[i("div",{staticClass:"zy_left_con"},[i("div",{staticClass:"zy_twonav"},[i("h2",{staticClass:"tit"},[t._v("成果展示")]),t._v(" "),i("ul",t._l(t.menu,function(a,e){return i("li",{class:{cur:t.currpage==a}},[i("a",{attrs:{href:"javascript:void(0)"},on:{click:function(i){return t.loadData(a)}}},[t._v(t._s(a))])])}),0)])]),t._v(" "),i("div",{staticClass:"zy_right_con"},["成果展示"==t.currpage?i("div",[i("div",{staticClass:"zy_content fix"},[i("div",{staticClass:"zy_conbox"},[i("div",{staticClass:"zy_newsxq"},[i("div",{staticClass:"title"},[i("span",[t._v(t._s(t.title))])]),t._v(" "),i("div",{staticClass:"main"},[i("div",{domProps:{innerHTML:t._s(t.mainText)}})])])])])]):t._e()])])])])},s=[function(){var t=this,a=t.$createElement,i=t._self._c||a;return i("div",{staticClass:"l tit"},[i("b",{staticClass:"dib vm"},[t._v("当前位置：")])])}],n={render:e,staticRenderFns:s};a.a=n}});
//# sourceMappingURL=2.build.js.map