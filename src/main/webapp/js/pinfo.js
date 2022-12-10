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
            size: '',
            loading: true,
            reportDialog: false,
            reportForm: {
                content: ''
            },
            rules: {
                content: [
                    {required: true, message: '请输入举报内容', trigger: 'change'}
                ]
            }
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
        report() {
            this.$refs['reportForm'].validate((valid) => {
                if (valid) {
                    let params = new URLSearchParams()
                    params.append('userId', this.user.userId)
                    params.append('content', this.reportForm.content)
                    axios.post('api/report/reportUser', params).then(resp => {
                        if (resp.data.code === 20011) {
                            this.$message.success('举报用户成功，请等待管理员处理')
                            this.$refs['reportForm'].resetFields()
                        } else if (resp.data.code === 20010) {
                            this.$message.error('举报用户失败，请稍后重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error('api/report/reportUser接口请求错误');
                    }).finally(() => {
                        this.reportDialog = false
                    })
                } else {
                    return false;
                }
            })
        }
    },
    mounted() {
        this.user.userId = this.getQueryVariable('userId')
        axios.get('api/user/getInfo?userId=' + this.user.userId).then(resp => {
            if (resp.data.code === 20041) {
                this.user = resp.data.data
                setTimeout(() => {
                    this.loading = false
                }, 1000)
            } else if (resp.data.code === 20040) {
                this.$message.error('用户不存在');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message)
            }
        }).catch(error => {
            this.$message.error('api/user/myInfo接口请求错误')
        })
    }
})