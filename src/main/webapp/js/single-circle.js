new Vue({
    el: '#app',
    data() {
        return {
            activeName: 'first',
            isAttention: true,
            isCircleAdmin:false,
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
            },
            circle: {
                circleId: -1,
                circleName: '',
                circleImg: '',
                circleContent: '',
                circleLife: '',
                circleTime: '',
                circle_UserId: '',
            }
        }
    },
    methods: {
        goBack() {
            history.go(-1)
        },
        handleClick(tab, event) {

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
        axios.get("api/circle/getInfo?circleId=" + this.circle.circleId).then(resp => {
            if (resp.data.code === 20041) {
                this.circle = resp.data.data
            } else if (resp.data.code === 20040) {
                this.$message.error('圈子请求错误，请重试');
            } else if (resp.data.code === 10000) {
                this.$message.error('后端异常，报错：' + resp.data.message);
            }
        })
    }
})