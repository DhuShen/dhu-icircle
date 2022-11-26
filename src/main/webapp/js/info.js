new Vue({
    el: '#app',
    data() {
        return {
            user: {
                id: '200901514',
                name: '李华',
                sex: '男',
                time: '2022-11-21',
                major: '软件工程',
                introduction: '暂无'
            },
            size: ''
        }
    },
    methods: {
        goBack() {
            history.back (-1)
        }
    }
})