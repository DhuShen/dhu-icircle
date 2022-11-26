new Vue({
    el: '#app',
    data() {
        return {
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

        },
        handleClick(tab, event) {
            console.log(tab, event);
        }
    }
})