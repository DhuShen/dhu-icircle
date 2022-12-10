new Vue({
    el: '#app',
    data() {
        return {
            post: {
                postId: -1,
                postName: '',
                postContent: '',
                postKey: '',
                postGood: '',
                postTime: '',
                post_UserId: '',
                postCircleId: '',
            },
            discusses: [],
            discussForm: {
                content: ''
            },
            rules: {
                content: [
                    {required: true, message: '输入内容为空', trigger: 'change'}
                ]
            },
            replay: -1,
            likeType: '',
            loading: true,
            userId:''
        }
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
        },
        likePost() {
            axios.get('api/post/good?postId=' + this.post.postId).then(resp => {
                if (resp.data.code === 20021) {
                    this.$message.success('点赞成功，感谢你的支持');
                    this.post.postGood += 1
                } else if (resp.data.code === 20020) {
                    this.$message.error('帖子点赞失败');
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message);
                }
            })
        },
        likeDiscuss(id, i) {
            axios.get('api/discuss/good?discussId=' + id).then(resp => {
                if (resp.data.code === 20021) {
                    this.$message.success('评论点赞成功，感谢你的支持');
                    this.discusses[i].discussGood += 1
                } else if (resp.data.code === 20020) {
                    this.$message.error('评论点赞失败');
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message);
                }
            })
        },
        addDiscuss() {
            this.$refs['discussForm'].validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams()
                    params.append('postId', this.post.postId)
                    if (this.replay === -1)
                        params.append('replayed', null)
                    else
                        params.append('replayed', this.discusses[this.replay].discuss_UserId)
                    params.append('content', this.discussForm.content)
                    axios.post('api/discuss/add', params).then(resp => {
                        if (resp.data.code === 20011) {
                            this.$message.success('发布成功，感谢你的评论')
                            this.$refs['discussForm'].resetFields()
                            this.replay = -1
                            this.loading = true
                            axios.get("api/discuss/getDiscuss?postId=" + this.post.postId).then(resp => {
                                if (resp.data.code === 20041) {
                                    this.discusses = resp.data.data
                                    for (let i = 0; i < this.discusses.length; i++) {
                                        axios.get('api/user/name?userId=' + this.discusses[i].discuss_UserId).then(resp => {
                                            this.discusses[i].user = resp.data.data
                                        })
                                        if (this.discusses[i].discussReplayed != null) {
                                            axios.get('api/user/name?userId=' + this.discusses[i].discussReplayed).then(resp => {
                                                this.discusses[i].replay = resp.data.data
                                            })
                                        }
                                    }
                                    setTimeout(() => {
                                        this.loading = false
                                    }, 500)
                                } else if (resp.data.code === 20040) {
                                    this.$message.error('评论请求错误，请重试');
                                } else if (resp.data.code === 10000) {
                                    this.$message.error('后端异常，报错：' + resp.data.message);
                                }
                            }).catch(error => {
                                this.$message.error('api/discuss/getDiscuss接口请求错误');
                            })
                        } else if (resp.data.code === 20010) {
                            this.$message.error('评论失败，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/discuss/add接口请求错误');
                    })
                } else {
                    return false;
                }
            })
        }
    },
    mounted() {
        this.post.postId = this.getQueryVariable('postId')
        axios.get('api/user/myId').then(resp => {
            if (resp.data.code === 20041) {
                this.userId = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/user/myId接口请求错误');
        })
        axios.get('api/post/getInfo?postId=' + this.post.postId).then(resp => {
            if (resp.data.code === 20041) {
                this.post = resp.data.data
                axios.get('api/user/name?userId=' + this.post.post_UserId).then(resp => {
                    this.post.user = resp.data.data
                    axios.get("api/discuss/getDiscuss?postId=" + this.post.postId).then(resp => {
                        if (resp.data.code === 20041) {
                            this.discusses = resp.data.data
                            for (let i = 0; i < this.discusses.length; i++) {
                                axios.get('api/user/name?userId=' + this.discusses[i].discuss_UserId).then(resp => {
                                    this.discusses[i].user = resp.data.data
                                })
                                if (this.discusses[i].discussReplayed != null) {
                                    axios.get('api/user/name?userId=' + this.discusses[i].discussReplayed).then(resp => {
                                        this.discusses[i].replay = resp.data.data
                                    })
                                }
                            }
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error('评论请求错误，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/discuss/getDiscuss接口请求错误');
                    })
                })
            } else if (resp.data.code === 20040) {
                this.$message.error('帖子请求错误，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/post/getInfo接口请求错误');
        })
    }
})