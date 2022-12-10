new Vue({
    el: '#app',
    data() {
        return {
            pushDialog: false,
            activeName: 'first',
            isAttention: false,
            isMaster: false,
            pushRequest: {
                postName: '',
                postContent: ''
            },
            rules: {
                postName: [
                    {required: true, message: '请输入圈子名称', trigger: 'change'},
                ],
                postContent: [
                    {required: true, message: '请输入圈子介绍', trigger: 'change'}
                ]
            },
            circle: {
                circleId: -1,
                circleName: '',
                circleImg: '',
                circleContent: '',
                circleLife: '',
                circleTime: '',
                circle_UserId: '',
            },
            posts: [],
            users: [],
            loading: true,
            userId: ''
        }
    },
    methods: {
        goBack() {
            history.back()
        },
        addPost() {
            this.$refs['createForm'].validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams()
                    params.append('circleId', this.circle.circleId)
                    params.append('postName', this.pushRequest.postName)
                    params.append('postContent', this.pushRequest.postContent)
                    axios.post('api/post/add', params).then(resp => {
                        this.loading = true
                        if (resp.data.code === 20011) {
                            this.$message.success('发帖成功，经验+1')
                            if (this.activeName === 'first') {
                                axios.get('api/post/getPost?circleId=' + this.circle.circleId).then(resp => {
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
                                        this.$refs['createForm'].resetFields();
                                        setTimeout(() => {
                                            this.loading = false
                                        }, 1000)
                                    } else if (resp.data.code === 20040) {
                                        this.$message.error("获取帖子失败");
                                    } else if (resp.data.code === 10000) {
                                        this.$message.error('后端异常，报错：' + resp.data.message);
                                    }
                                }).catch(error => {
                                    this.$message.error('api/post/getPost接口请求错误');
                                })
                            }
                        } else if (resp.data.code === 20010) {
                            this.$message.error('发帖失败，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/post/add接口请求错误');
                    }).finally(() => {
                        this.pushDialog = false
                    })
                } else {
                    return false;
                }
            });
        },
        enterCircle() {
            axios.get('api/circle/enterCirlce?circleId=' + this.circle.circleId).then(resp => {
                if (resp.data.code === 20011) {
                    this.isAttention = true
                    this.$message.success("关注成功，感谢你的支持")
                } else if (resp.data.code === 20010) {
                    this.$message.error("关注圈子失败")
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/circle/enterCirlce接口请求错误');
            })
        },
        quitCircle() {
            axios.get('api/circle/quitCirlce?circleId=' + this.circle.circleId).then(resp => {
                if (resp.data.code === 20021) {
                    this.isAttention = false
                    this.$message.success("取消关注成功，欢迎你下次再来")
                } else if (resp.data.code === 20020) {
                    this.$message.error("取消关注失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/circle/quitCirlce接口请求错误')
            })
        },
        keyPost(i) {
            axios.get('api/post/setKeyPost?postId=' + this.posts[i].postId).then(resp => {
                if (resp.data.code === 20021) {
                    this.posts[i].postKey = 1
                    this.$message.success("已加入精华帖")
                } else if (resp.data.code === 20020) {
                    this.$message.error("加入精华帖失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/post/setKeyPost接口请求错误')
            })
        },
        cancelKeyPost(postId) {
            axios.get('api/post/cancelKeyPost?postId=' + postId).then(resp => {
                if (resp.data.code === 20021) {
                    this.$message.success("已取消精华帖")
                    this.loading = true
                    axios.get('api/post/getKeyPost?circleId=' + this.circle.circleId).then(resp => {
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
                        this.$message.error('api/post/getKeyPost接口请求错误');
                    })
                } else if (resp.data.code === 20020) {
                    this.$message.error("取消精华帖失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/post/cancelKeyPost接口请求错误')
            })
        },
        kick(userId) {
            let params = new URLSearchParams()
            params.append('circleId', this.circle.circleId)
            params.append('userId', userId)
            axios.post('api/circle/deletePerson', params).then(resp => {
                if (resp.data.code === 20021) {
                    this.$message.success("已踢出用户")
                    this.loading = true
                    axios.get('api/circle/getPerson?circleId=' + this.circle.circleId).then(resp => {
                        if (resp.data.code === 20041) {
                            this.users = resp.data.data
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error("获取用户失败");
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/post/getPerson接口请求错误');
                    })
                } else if (resp.data.code === 20020) {
                    this.$message.error("踢出用户失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/user/deletePerson接口请求错误')
            })
        },
        handleClick(tab, event) {
            this.loading = true
            switch (tab.index) {
                case '0':
                    axios.get('api/post/getPost?circleId=' + this.circle.circleId).then(resp => {
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
                        this.$message.error('api/post/getPost接口请求错误');
                    })
                    break
                case '1':
                    axios.get('api/post/getKeyPost?circleId=' + this.circle.circleId).then(resp => {
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
                        this.$message.error('api/post/getKeyPost接口请求错误');
                    })
                    break
                case '2':
                    this.posts = []
                    axios.get('api/circle/getPerson?circleId=' + this.circle.circleId).then(resp => {
                        if (resp.data.code === 20041) {
                            this.users = resp.data.data
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error("获取用户失败");
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/post/getPerson接口请求错误');
                    })
                    break
            }
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
        this.circle.circleId = this.getQueryVariable('circleId')
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
        axios.get("api/circle/getInfo?circleId=" + this.circle.circleId).then(resp => {
            if (resp.data.code === 20041) {
                this.circle = resp.data.data
                axios.get('api/user/name?userId=' + this.circle.circle_UserId).then(resp => {
                    this.circle.user = resp.data.data
                })
                axios.get('api/circle/isAttention?circleId=' + this.circle.circleId).then(resp => {
                    this.isAttention = resp.data.data
                })
                axios.get('api/circle/isMaster?circleId=' + this.circle.circleId).then(resp => {
                    this.isMaster = resp.data.data
                })
                axios.get('api/post/getPost?circleId=' + this.circle.circleId).then(resp => {
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
                    this.$message.error('api/post/getPost接口请求错误');
                })
            } else if (resp.data.code === 20040) {
                this.$message.error('圈子请求错误，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/circle/getInfo接口请求错误');
        })
    }
})