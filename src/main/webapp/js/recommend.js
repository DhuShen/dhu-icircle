new Vue({
    el: '#app',
    data() {
        return {
            loading: false,
            posts: [],
            circles: [],
            searchForm: {
                type: 'circle',
                content: '',

            },
            activeName: 'first'
        };
    },
    methods: {
        handleSelect(key) {
            switch (key) {
                case '2':
                    location.href = "circles"
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
            console.log(tab.index)
            switch (tab.index) {
                case '0':
                    this.loading = true
                    setTimeout(() => (this.loading = false), 2000)
                    this.circles = []
                    this.posts = [
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                        {
                            name: '世界杯夺冠之日',
                            good: 200,
                            time: '2022-12-20',
                            user: 'shen din',
                            circle: '杂谈圈子'
                        },
                    ]
                    break
                case '1':
                    this.loading = true
                    setTimeout(() => (this.loading = false), 2000)
                    this.posts = []
                    this.circles = [
                        {
                            img:'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img:'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img:'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        },
                        {
                            img:'https://wonderfulengineering.com/wp-content/uploads/2014/10/image-wallpaper-15.jpg',
                            name: '杂谈圈子',
                            master: 'shen din',
                            content: '欢迎来到我的杂谈圈子，请大家畅所欲言，各抒己见',
                            time: '2022-12-10',
                            hot: 200
                        }
                    ]
                    break
            }
        }
    },
    mounted() {
        this.loading = true
        setTimeout(() => (this.loading = false), 2000)
        this.posts = [
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
            {
                name: '世界杯夺冠之日',
                good: 200,
                time: '2022-12-20',
                user: 'shen din',
                circle: '杂谈圈子'
            },
        ]
    }
})