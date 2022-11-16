<template>
    <footer class="footer py-4">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-4 my-3 my-lg-0">
                    <h3 class="fw-bold m-0 ms-5"><span class="text-warning">Where</span> <span class="text-white">Is My </span>
                        <span class="text-success">Home</span>
                    </h3>
                </div>
                <div class="col-lg-4 my-3 my-lg-0">
                    <a class="btn btn-dark btn-social mx-2 me-5 border-white border-2" href="#!" aria-label="Github"><i class="fab fa-github" style="position: relative; right:2px;"></i></a>
                    <a class="btn btn-dark btn-social mx-2 border-white border-2" href="#!" aria-label="Ssafy"><i class="fab fa-linkedin-in"></i></a>
                </div>
                <!-- <div class="col-lg-4 text-lg-end">
                    <a class="link-dark text-decoration-none me-3" href="#!">Privacy Policy</a>
                    <a class="link-dark text-decoration-none" href="#!">Terms of Use</a>
                </div> -->
                <div class="col-lg-4 text-lg-start text-white">Copyright &copy; K.K Company</div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'FooterView',

    data() {
        return {
            
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style>
.footer {
    background-color: #212429;
}
footer {
    bottom: 0;
    height: 80px;
    position: fixed;
    width: 100%;
}
</style>