new Vue({
    el: '#app',
    data() {
        return {
            username: '',
            userImg:'',
            searchForm: {
                type: 'circle',
                content: ''
            },
            searchRules: {
                content: [
                    {required: true, message: '请输入搜索内容', trigger: 'change'},
                ]
            },
            activeName: 'first',
            mails: [],
            loading: true
        };
    },
    methods: {
        handleSelect(key) {
            console.log(key);
            switch (key) {
                case '1':
                    location.href = "recommend"
                    break
                case '2':
                    location.href = "circles"
                    break
                case '4-1':
                    location.href = 'info'
                    break
                case '4-2':
                    axios.get('api/user/out').then(resp => {
                        this.$message.success('注销成功')
                        setTimeout("location.href = 'login'", 1000)
                    })
                    break
            }
        },
        onSearch() {
            this.$refs['search'].validate((valid) => {
                if (valid) {
                    location.href = `search?name=${this.searchForm.content}&type=${this.searchForm.type}`
                } else {
                    return false;
                }
            });
        }
    },
    mounted() {
        axios.get('api/user/myName').then(resp => {
            if (resp.data.code === 20041) {
                this.username = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        })
        axios.get('api/user/myImg').then(resp => {
            if (resp.data.code === 20041) {
                this.userImg = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/user/myImg接口请求错误')
        })
        axios.get('api/message/get').then(resp => {
            if (resp.data.code === 20041) {
                this.mails = resp.data.data
                for (let i = 0; i < this.mails.length; i++) {
                    axios.get('api/user/name?userId=' + this.mails[i].messageUserIdSet).then(resp => {
                        this.mails[i].user = resp.data.data
                    })
                }
                setTimeout(() => {
                    this.loading = false
                }, 1000)
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/message/get接口请求错误');
        })
    }
})