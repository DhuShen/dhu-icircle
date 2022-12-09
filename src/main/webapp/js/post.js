new Vue({
    el: '#app',
    data() {
        return {
            activeName: 'first',
            isAttention: true,
            isCircleAdmin:false,
            post: {
                postId: -1,
                postName: '',
                postContent: '',
                postKey:'',
                postGood: '',
                postTime: '',
                post_UserId: '',
                postCircleId:'',
            },
            discusses:[],
            discussForm: {
                content: ''
            },
            rules: {
                content: [
                    {required: true, message: '输入内容为空', trigger: 'change'}
                ]
            },
            replay:''
        }
    },
    methods: {
        goBack() {
            history.go(-1)
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
        this.post.postId = this.getQueryVariable('postId')
        axios.get("api/post/getInfo?postId=" + this.post.postId).then(resp => {
            if (resp.data.code === 20041) {
                this.post = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('帖子请求错误，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/post/getInfo接口请求错误');
        })
        axios.get("api/discuss/getDiscuss?postId="+this.post.postId).then(resp=>{
            if (resp.data.code === 20041) {
                this.discusses = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('评论请求错误，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        }).catch(error => {
            this.$message.error('api/discuss/getDiscuss接口请求错误');
        })
    }
})