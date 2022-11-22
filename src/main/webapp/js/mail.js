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
                case '1':
                    location.href = "recommend"
                    break
                case '2':
                    location.href = "circles"
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