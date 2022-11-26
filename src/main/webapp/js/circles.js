new Vue({
    el: '#app',
    data() {
        return {
            searchForm: {
                type: 'circle',
                content: '',
            },
            activeName: 'first',
            circles:[],
            create:false
        };
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
                    this.create=false;
                    this.circles= [
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
                case '1':
                    this.create=true;
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
    }, mounted() {
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
    }
})