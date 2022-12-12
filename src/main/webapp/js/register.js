new Vue({
    el: '#app',
    data() {
        let validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.registerForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        }

        return {
            btnStatus: false,
            registerForm: {
                id: '',
                password: '',
                check: '',
            },
            rules: {
                id: [
                    {required: true, message: '请输入ID', trigger: 'change'},
                    {min: 9, max: 9, message: '长度为9个字符', trigger: 'change'}
                ],
                password: [
                    {required: true, message: '请输入密码', trigger: 'change'}
                ],
                check: [
                    {required: true, validator: validatePass, trigger: 'change'}
                ]
            },
            isAdmin: false
        }
    },
    methods: {
        onSubmit() {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    this.btnStatus = true
                    let params = new URLSearchParams()
                    params.append('id', this.registerForm.id)
                    params.append('password', this.registerForm.password)
                    axios.get('http://api.btstu.cn/sjtx/api.php?format=json').then(resp => {
                        let img = resp.data.imgurl
                        params.append('img', img)
                        axios.post('api/user/register', params).then(resp => {
                            if (resp.data.code === 20011) {
                                this.$message.success('注册成功，3秒后将自动返回')
                                setTimeout("location.href = 'login'", 3000)
                            } else if (resp.data.code === 20010) {
                                this.$message.error('用户ID已经存在，请重试');
                            } else if (resp.data.code === 10000) {
                                this.$message.error('后端异常，报错：' + resp.data.message);
                            }
                        }).catch(error => {
                            this.$message.error('api/user/register接口请求错误')
                        }).finally(() => {
                            this.btnStatus = false
                        })
                    })
                } else {
                    return false;
                }
            });
        },
        backLogin() {
            location.href = "login"
        }
    }
})