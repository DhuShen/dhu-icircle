new Vue({
    el: '#app',
    data() {
        return {
            user: {
                userId: '',
                userImg: '',
                userName: '',
                userSex: '',
                userTime: '',
                userMajor: '',
                userIntroduction: ''
            },
            form: {
                userId: '',
                userImg: '',
                userName: '',
                userSex: '',
                userTime: '',
                userMajor: '',
                userIntroduction: ''
            },
            rules: {
                userName: [
                    {required: true, message: '请输入用户名', trigger: 'change'},
                ],
                userSex: [
                    {required: true, message: '请输入性别', trigger: 'change'}
                ],
                userMajor: [
                    {required: true, message: '请输入专业', trigger: 'change'},
                ],
                userIntroduction: [
                    {required: true, message: '请输入个人简介', trigger: 'change'}
                ],
            },
            size: '',
            loading: true,
            dialogFormVisible: false
        }
    },
    methods: {
        goBack() {
            history.back()
        },
        updateInfo() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    axios({
                        url: 'api/user/update',
                        method: 'post',
                        data: this.form
                    }).then(resp => {
                        if (resp.data.code === 20021) {
                            this.loading = true
                            axios.get('api/user/myInfo').then(resp => {
                                if (resp.data.code === 20041) {
                                    this.user = resp.data.data
                                    this.form = resp.data.data
                                    setTimeout(() => {
                                        this.loading = false
                                    }, 1000)
                                } else if (resp.data.code === 20040) {
                                    this.$message.error('session不存在，请重新登录');
                                } else if (resp.data.code === 10000) {
                                    this.$message.error('后端异常，报错：' + resp.data.message)
                                }
                            }).catch(error => {
                                this.$message.error('api/user/myInfo接口请求错误')
                            })
                            this.$message.success('用户信息更新成功')
                        } else if (resp.data.code === 20020) {
                            this.$message.error('用户信息更新失败')
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message)
                        }
                    }).catch(error => {
                        this.$message.error('api/user/update接口请求错误')
                    }).finally(()=>{
                        this.dialogFormVisible=false
                    })
                } else {
                    return false;
                }
            })
        }
    },
    mounted() {
        axios.get('api/user/myInfo').then(resp => {
            if (resp.data.code === 20041) {
                this.user = resp.data.data
                this.form = JSON.parse(JSON.stringify(this.user))
                setTimeout(() => {
                    this.loading = false
                }, 1000)
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message)
            }
        }).catch(error => {
            this.$message.error('api/user/myInfo接口请求错误')
        })
    }
})