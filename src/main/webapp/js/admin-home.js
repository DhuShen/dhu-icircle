new Vue({
    el: '#app',
    data() {
        return {
            adminName: '',
            isCollapse: false,
            loading: true,
            tab: '1-1',
            tableData: []
        }
    },
    methods: {
        select(key, keyPath) {
            switch (key) {
                case '0':
                    this.isCollapse = !this.isCollapse
                    break
                case '1-1':
                    this.tab = key
                    this.loading = true
                    axios.get('api/request/getRequest').then(resp => {
                        if (resp.data.code === 20041) {
                            this.tableData = resp.data.data
                            for (let i = 0; i < this.tableData.length; i++) {
                                axios.get('api/user/name?userId=' + this.tableData[i].request_UserId).then(resp => {
                                    this.tableData[i].user = resp.data.data
                                })
                            }
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error('获取请求失败，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message)
                        }
                    }).catch(error => {
                        this.$message.error('api/request/getRequest接口请求错误')
                    })
                    break

                case '2-1':
                    this.tab = key
                    this.loading = true
                    axios.get('api/report/getReport').then(resp => {
                        if (resp.data.code === 20041) {
                            this.tableData = resp.data.data
                            for (let i = 0; i < this.tableData.length; i++) {
                                if (this.tableData[i].reportUserIdGet!=null){
                                    axios.get('api/user/name?userId=' + this.tableData[i].reportUserIdGet).then(resp => {
                                        this.tableData[i].userGet = resp.data.data
                                    })
                                }
                                axios.get('api/user/name?userId=' + this.tableData[i].reportUserIdSet).then(resp => {
                                    this.tableData[i].userSet = resp.data.data
                                })
                            }
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error('获取举报失败，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message)
                        }
                    }).catch(error => {
                        this.$message.error('api/report/getReport接口请求错误')
                    })
                    break
                case '3':
                    axios.get('api/admin/out').then(resp => {
                        this.$message.success('注销成功')
                        setTimeout("location.href = 'login'", 1000)
                    })
            }
        },
        agree(requestId) {
            axios.get('api/request/agree?requestId='+requestId).then(resp => {
                if (resp.data.code === 20021) {
                    this.$message.success('申请处理成功')
                    this.loading = true
                    axios.get('api/request/getRequest').then(resp => {
                        if (resp.data.code === 20041) {
                            this.tableData = resp.data.data
                            for (let i = 0; i < this.tableData.length; i++) {
                                axios.get('api/user/name?userId=' + this.tableData[i].request_UserId).then(resp => {
                                    this.tableData[i].user = resp.data.data
                                })
                            }
                            setTimeout(() => {
                                this.loading = false
                            }, 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error('获取请求失败，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message)
                        }
                    }).catch(error => {
                        this.$message.error('api/request/getRequest接口请求错误')
                    })
                } else if (resp.data.code === 20020) {
                    this.$message.error('申请处理失败，请稍后重试');
                } else if (resp.data.code === 10000) {
                    this.$message.error('后端异常，报错：' + resp.data.message);
                }
            }).catch(error => {
                this.$message.error('api/request/agree接口请求错误');
            }).finally(() => {
                this.reportDialog = false
            })
        },
        tableRowClassName({row, rowIndex}) {
            if (rowIndex === 1) {
                return 'warning-row';
            } else if (rowIndex === 3) {
                return 'success-row';
            }
            return '';
        },
        filterTag(value, row) {
            return row.requestType === value;
        }
    },
    mounted() {
        axios.get('api/admin/myInfo').then(resp => {
            if (resp.data.code === 20041) {
                this.adminName = resp.data.data.adminName
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message)
            }
        }).catch(error => {
            this.$message.error('api/admin/myInfo接口请求错误')
        })
        axios.get('api/request/getRequest').then(resp => {
            if (resp.data.code === 20041) {
                this.tableData = resp.data.data
                for (let i = 0; i < this.tableData.length; i++) {
                    axios.get('api/user/name?userId=' + this.tableData[i].request_UserId).then(resp => {
                        this.tableData[i].user = resp.data.data
                    })
                }
                setTimeout(() => {
                    this.loading = false
                }, 1000)
            } else if (resp.data.code === 20040) {
                this.$message.error('获取请求失败，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message)
            }
        }).catch(error => {
            this.$message.error('api/request/getRequest接口请求错误')
        })
    }
})