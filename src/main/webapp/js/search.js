new Vue({
    el: '#app',
    data() {
        return {
            loading: false,
            list: [],
            content: '',
            type: ''
        };
    },
    methods: {
        goBack() {
            history.back()
        },
        getQueryVariable(variable) {
            var query = window.location.search.substring(1);
            var vars = query.split("&");
            for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] === variable) {
                    return pair[1];
                }
            }
            return false;
        }
    },
    mounted() {
        this.loading = true
        this.content = this.getQueryVariable('name')
        this.type = this.getQueryVariable('type')
        if(this.type==='post'){
            axios.get('api/post/search?name=' + this.content).then(resp => {
                if (resp.data.code === 20041) {
                    this.list = resp.data.data
                    for (let i = 0; i < this.list.length; i++) {
                        axios.get('api/user/name?userId=' + this.list[i].post_UserId).then(resp => {
                            this.list[i].user = resp.data.data
                            axios.get('api/circle/name?circleId=' + this.list[i].post_CircleId).then(resp => {
                                this.list[i].circle = resp.data.data
                            })
                        })
                    }
                    setTimeout(() => {
                        this.loading = false
                    }, 1000)
                } else if (resp.data.code === 20040) {
                    this.$message.error("获取帖子失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message);
                }
            }).catch(error => {
                this.$message.error('api/post/search接口请求错误');
            })
        }else if(this.type==='circle'){
            axios.get('api/circle/search?name=' + this.content).then(resp => {
                if (resp.data.code === 20041) {
                    this.list = resp.data.data
                    for (let i = 0; i < this.list.length; i++) {
                        axios.get('api/user/name?userId=' + this.list[i].circle_UserId).then(resp => {
                            this.list[i].master = resp.data.data
                        })
                    }
                    setTimeout(() => {
                        this.loading = false
                    }, 1000)
                } else if (resp.data.code === 20040) {
                    this.$message.error("获取圈子失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message);
                }
            }).catch(error => {
                this.$message.error('api/post/search接口请求错误');
            })
        }
    }
})