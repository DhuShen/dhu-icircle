new Vue({
    el: '#app',
    data() {
        return {
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
                    location.href="mail"
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