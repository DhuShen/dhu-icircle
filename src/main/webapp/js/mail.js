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
            mails: []
        };
    },
    methods: {
        handleSelect(key) {
            console.log(key);
            switch (key) {
                case '1':
                    location.href = "recommend"
                    break
                case '2':
                    location.href = "circles"
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

        }
    },
    mounted(){
        axios.get('api/user/myName').then(resp=>{
            if (resp.data.code === 20041) {
                this.username = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        })
        axios.get('api/message/get').then(resp=>{
            if (resp.data.code === 20041) {
                this.mails = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('session不存在，请重新登录');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/message/get接口请求错误');
        })
    }
})