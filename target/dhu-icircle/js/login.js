new Vue({
    el: '#app',
    data() {
        return {
            btnStatus: false,
            loginForm: {
                id: '',
                password: ''
            },
            rules: {
                id: [
                    {required: true, message: '输入ID为空', trigger: 'change'}
                ],
                password: [
                    {required: true, message: '请输入密码为空', trigger: 'change'}
                ]
            },
            isAdmin: false
        }
    },
    methods: {
        onSubmit() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    this.btnStatus=true
                    let params = new URLSearchParams()
                    let url = this.isAdmin ? 'api/admin/login' : 'api/user/login'
                    params.append('id', this.loginForm.id)
                    params.append('password', this.loginForm.password)
                    axios.post(url, params).then(resp => {
                        if (resp.data.code === 20041) {
                            this.$message.success('登陆成功')
                            setTimeout("location.href = 'recommend'", 1000)
                        } else if (resp.data.code === 20040) {
                            this.$message.error('用户名或密码错误，请重试');
                        } else if (resp.data.code === 10000) {
                            this.$message.error('后端异常，报错：' + resp.data.message);
                        }
                    }).catch(error => {
                        this.$message.error(url + '接口请求错误');
                    }).finally(()=>{
                        this.btnStatus=false
                    })
                } else {
                    return false;
                }
            });
        },
        toRegister() {
            location.href = "register";
        }
    }
})