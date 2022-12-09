new Vue({
    el: '#app',
    data() {
        return {
            username:'',
            searchForm: {
                type: 'circle',
                content: '',
            },
            activeName: 'first',
            circles: [],
            create: false,
            pushDialog: false,
            pushRequest: {
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
                    location.href='info'
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

        },
        handleClick(tab, event) {
            switch (tab.index) {
                case '0':
                    this.create = false;
                    this.circles = [
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        }
                    ]
                    break
                case '1':
                    this.create = true;
                    this.circles = [
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        }
                    ]
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
                            this.$refs['createForm'].resetFields();
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
        }
    },
    mounted() {
        axios.get('api/user/myName').then(resp=>{
            if (resp.data.code === 20041) {
                this.username = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        })
        this.circles = [
            {
                img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                name: '杂谈圈子',
                master: 'shen din',
                content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                time: '2022-12-10',
                hot: 200
            },
            {
                img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                name: '杂谈圈子',
                master: 'shen din',
                content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                time: '2022-12-10',
                hot: 200
            },
            {
                img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                name: '杂谈圈子',
                master: 'shen din',
                content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                time: '2022-12-10',
                hot: 200
            },
            {
                img: 'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                name: '杂谈圈子',
                master: 'shen din',
                content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                time: '2022-12-10',
                hot: 200
            }
        ]
    }
})