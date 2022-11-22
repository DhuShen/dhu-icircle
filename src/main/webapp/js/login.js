new Vue({
    el: '#app',
    data() {
        return {
            loginForm: {
                userId: '',
                password: ''
            },
            isAdmin:false
        }
    },
    methods:{
        onSubmit(){
            location.href="recommend"
        }
    }
})