
Vue.component("left-menu",{
    template:`
    <div id="side-nav">
        <ul id="nav">
            <li v-for="(item,i) in data">
                    <a href="javascript:;" v-bind:_href="item.path" v-bind:id="item.id">
                        <i class="iconfont">&#xe6b8;</i>
                        <cite>{{item.name}}</cite>
                        <i v-if="item.children!=null" class="iconfont nav_right">&#xe697;</i>
                    </a>
                    <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
             </li>
        </ul>
    </div>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("children-menu",{
    template:`
            <ul class="sub-menu">
                <li v-for="(item,i) in data">
                    <a v-bind:_href="item.path" v-bind:id="item.id">
                        <i class="iconfont">&#xe6a7;</i>
                        <cite>{{item.name}}</cite>
                    </a>
                    <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                </li >
            </ul>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("form-input",{
    template:`
            <div class="layui-form-item">
              <label v-bind:for="id" class="layui-form-label">
                  <span class="x-red" v-if="required">*</span>{{label}}
              </label>
              <div class="layui-input-inline">
                  <input type="text"
                    v-bind:id="id"
	                v-bind:value="value"  
	                v-bind:placeholder="'请输入'+label"
	                v-on:input="updateValue($event.target.value)" 
	                v-bind:required="required" 
	                v-bind:lay-verify="'email'"
	                class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="x-red">*</span>{{info}}
              </div>
          </div>`,
    props:{
        data: {
            type: Array,
            default: []
        },
        required: {
            type: Boolean,
            default: false
        },
        placeholder: {
            type: String,
            default: ''
        },
        info: {
            type: String,
            default: ''
        },
        label: {
            type: String,
            default: ''
        },
        id: {
            type: String,
            default: ''
        },
        value: {
            type: String,
            default: ''
        }
    },
    methods: {
        updateValue: function (value) {
            this.$emit('input', value);
        }
    }
});
Vue.component("form-select",{
    template:`
            <div class="layui-form-item">
              <label v-bind:for="id" class="layui-form-label">
                  <span class="x-red">*</span>{{label}}
              </label>
              <div class="layui-input-inline">
                  <select   
                    v-bind:id="id"
	                v-on:change="updateValue"   
	                v-bind:name="id" class="valid">
                    <option v-for="(item,i) in data" v-bind:id="item.id">{{item.name}}</option>
                  </select>
              </div>
            </div>
`,
    props:{
        data: {
            type: Array,
            default: []
        },
        required: {
            type: Boolean,
            default: false
        },
        placeholder: {
            type: String,
            default: ''
        },
        info: {
            type: String,
            default: ''
        },
        label: {
            type: String,
            default: ''
        },
        id: {
            type: String,
            default: ''
        },
        value: {
            type: String,
            default: ''
        }
    },
    methods: {
        updateValue: function (value) {
            this.$emit('input', value);

        }
    },
    mounted:function () {
        form.render('select');
        form.on('select(filter)', function(data){
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            console.log(data.othis); //得到美化后的DOM对象
        });
    }
});
/*
Vue.component("left-menu",{
    template:`
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" id="ul" style="background-color:#20222a">
                    <!--这里可以写菜单结构,也可以放空,使用异步加载-->
                    <span class="layui-nav-bar" style="height: 0px; top: 0px; opacity: 0;"></span>
                    <li class="lau-nav-item" v-for="(item,i) in data">
                        <a class="lau-nav-header" v-bind:lau-href="item.path" v-bind:id="item.id"><i class="layui-icon layui-icon-auz"></i><cite>{{item.name}}</cite></a>
                        <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                    </li>
             </ul>
        </div>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});
Vue.component("children-menu",{
    template:`
            <dl class="lau-nav-child">
                <dd v-for="(item,i) in data">
                    <a v-bind:lau-href="item.path" v-bind:id="item.id" lau-event="reload"><i class="layui-icon"></i><cite>{{item.name}}</cite></a>
                    <children-menu v-if="item.children!=null" v-bind:data="item.children"></children-menu>
                </dd>
            </dl>`,
    props:{
        data: {
            type: Array,
            default: []
        }
    }
});*/