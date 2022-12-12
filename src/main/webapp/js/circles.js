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
            circles: [],
            loading: true,
            create: false,
            pushDialog: false,
            updateDialog: false,
            pushRequest: {
                circleName: '',
                circleContent: ''
            },
            updateRequest: {
                circleId: -1,
                circleName: '',
                circleContent: ''
            },
            rules: {
                circleName: [
                    {required: true, message: '请输入圈子名称', trigger: 'change'},
                ],
                circleContent: [
                    {required: true, message: '请输入圈子介绍', trigger: 'change'}
                ]
            }
        }
    },
    methods: {
        handleSelect(key) {
            switch (key) {
                case '1':
                    location.href = "recommend"
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
        quitCircle(id) {
            axios.get('api/circle/quitCirlce?circleId=' + id).then(resp => {
                if (resp.data.code === 20021) {
                    this.isAttention = false
                    this.$message.success("取消关注成功，欢迎你下次再来")
                    this.loading = true
                    axios.get('api/circle/getMyCircle').then(resp => {
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
                            this.$message.error("获取帖子失败");
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/circle/getMyCircle接口请求错误');
                    })
                } else if (resp.data.code === 20020) {
                    this.$message.error("取消关注失败");
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message)
                }
            }).catch(error => {
                this.$message.error('api/circle/quitCirlce接口请求错误')
            })
        },
        handleClick(tab, event) {
            this.loading = true
            switch (tab.index) {
                case '0':
                    this.create = false;
                    axios.get('api/circle/getMyCircle').then(resp => {
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
                            this.$message.error("获取帖子失败");
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/circle/getMyCircle接口请求错误');
                    })
                    break
                case '1':
                    this.create = true;
                    axios.get('api/circle/getMyMasterCircle').then(resp => {
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
                            this.$message.error("获取帖子失败");
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/circle/getMyMasterCircle接口请求错误');
                    })
                    break
            }
        },
        createCircle() {
            this.$refs['createForm'].validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams()
                    params.append('circleName', this.pushRequest.circleName)
                    params.append('circleContent', this.pushRequest.circleContent)
                    axios.post('api/request/create', params).then(resp => {
                        if (resp.data.code === 20011) {
                            this.$message.success('建立圈子请求已发出，请等待管理员审核')
                            this.$refs['createForm'].resetFields()
                        } else if (resp.data.code === 20010) {
                            this.$message.error('建立圈子请求失败，请稍后重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/request/create接口请求错误');
                    }).finally(() => {
                        this.pushDialog = false
                    })
                } else {
                    return false;
                }
            })
        },
        updateCircle() {
            this.$refs['updateForm'].validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams()
                    params.append('circleId', this.updateRequest.circleId)
                    params.append('circleName', this.updateRequest.circleName)
                    params.append('circleContent', this.updateRequest.circleContent)
                    axios.post('api/request/update', params).then(resp => {
                        if (resp.data.code === 20011) {
                            this.$message.success('修改圈子请求已发出，请等待管理员审核')
                            this.loading = true
                            axios.get('api/circle/getMyMasterCircle').then(resp => {
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
                                    this.$message.error("获取帖子失败");
                                } else if (resp.data.code === 10000) {
                                    this.$message.error('后端异常，报错：' + resp.data.message);
                                }
                            }).catch(error => {
                                this.$message.error('api/circle/getMyMasterCircle接口请求错误');
                            })
                        } else if (resp.data.code === 20010) {
                            this.$message.error('修改圈子请求失败，请稍后重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/request/update接口请求错误');
                    }).finally(() => {
                        this.updateDialog = false
                    })
                } else {
                    return false;
                }
            })
        },
        deleteCircle() {
            this.$confirm('此操作将永久解散您的圈子, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                axios.get('api/circle/deleteCircle?circleId=' + this.updateRequest.circleId).then(resp => {
                    if (resp.data.code === 20031) {
                        this.$message.success('解散圈子成功')
                        this.loading = true
                        axios.get('api/circle/getMyMasterCircle').then(resp => {
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
                                this.$message.error("获取帖子失败");
                            } else if (resp.data.code === 10000) {
                                this.$message.error('后端异常，报错：' + resp.data.message);
                            }
                        }).catch(error => {
                            this.$message.error('api/circle/getMyMasterCircle接口请求错误');
                        })
                    } else if (resp.data.code === 20030) {
                        this.$message.error('解散圈子请求失败，请稍后重试');
                    } else if (resp.data.code === 10000) {
                        this.$message.error('后端异常，报错：' + resp.data.message);
                    }
                }).catch(error => {
                    this.$message.error('api/circle/deleteCircle接口请求错误');
                }).finally(() => {
                    this.updateDialog = false
                })
            }).catch(() => {
                this.$message.info('已取消删除')
            });
        },
        updateClick(i) {
            this.updateRequest.circleId = this.circles[i].circleId
            this.updateRequest.circleName = this.circles[i].circleName
            this.updateRequest.circleContent = this.circles[i].circleContent
            this.updateDialog = true
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
        axios.get('api/circle/getMyCircle').then(resp => {
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
                this.$message.error("获取帖子失败");
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/circle/getMyCircle接口请求错误');
        })
    }
})