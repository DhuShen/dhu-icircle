new Vue({
    el: '#app',
    data() {
        return {
            username: '',
            userImg:'',
            loading: false,
            posts: [],
            circles: [],
            searchForm: {
                type: 'circle',
                content: ''
            },
            searchRules: {
                content: [
                    {required: true, message: '请输入搜索内容', trigger: 'change'},
                ]
            },
            activeName: 'first'
        };
    },
    methods: {
        handleSelect(key) {
            switch (key) {
                case '2':
                    location.href = "circles"
                    break
                case '3':
                    location.href = "mail"
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
        },
        handleClick(tab, event) {
            this.loading = true
            switch (tab.index) {
                case '0':
                    this.circles = []
                    axios.get('api/post/hotposts').then(resp => {
                        if (resp.data.code === 20041) {
                            this.posts = resp.data.data
                            for (let i = 0; i < this.posts.length; i++) {
                                axios.get('api/user/name?userId=' + this.posts[i].post_UserId).then(resp => {
                                    this.posts[i].user = resp.data.data
                                    axios.get('api/circle/name?circleId=' + this.posts[i].post_CircleId).then(resp => {
                                        this.posts[i].circle = resp.data.data
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
                        this.$message.error('api/post/hotposts接口请求错误');
                    })
                    break
                case '1':
                    this.posts = []
                    axios.get('api/circle/hotcircles').then(resp => {
                        if (resp.data.code === 20041) {
                            this.circles = resp.data.data
                            for (let i = 0; i < this.circles.length; i++) {
                                axios.get('api/user/name?userId=' + this.circles[i].circle_UserId).then(resp => {
                                    this.circles[i].master = resp.data.data
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
                        this.$message.error('api/circle/hotcircles接口请求错误');
                    })
                    break
            }
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
        }).catch(error => {
            this.$message.error('api/user/myName接口请求错误');
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
        this.loading = true
        axios.get('api/post/hotposts').then(resp => {
            if (resp.data.code === 20041) {
                this.posts = resp.data.data
                for (let i = 0; i < this.posts.length; i++) {
                    axios.get('api/user/name?userId=' + this.posts[i].post_UserId).then(resp => {
                        this.posts[i].user = resp.data.data
                        axios.get('api/circle/name?circleId=' + this.posts[i].post_CircleId).then(resp => {
                            this.posts[i].circle = resp.data.data
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
            this.$message.error('api/post/hotposts接口请求错误');
        })
    }
})