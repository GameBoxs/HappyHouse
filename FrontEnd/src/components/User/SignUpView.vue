<template>
    <div class="signBody">
        <div class="signbody1">
        </div>
        <div class="wrapperDiv">
            <div class="wrapper border">
                <div class="logo">
                    <img src="https://cdn.pixabay.com/photo/2015/12/28/02/58/home-1110868_960_720.png" alt="">
                </div>
                <div class="text-center mt-4 name">
                    SignUp
                </div>
                <form class="p-3" v-on:submit.prevent>
                    <div class="failnameText text-center"> {{failnameText}} </div>
                    <div class="form-field d-flex align-items-center">
                        <span class="far fa-user"></span>
                        <input type="text" name="name" :value="userName" id="userName" placeholder="Name" @input="changeName" @keyup="checkname" @keydown.enter.prevent autocomplete="off">
                    </div>
                    <div class="failnameText text-center"> {{failemailText}} </div>
                    <div class="form-field d-flex align-items-center">
                        <span class="far fa-envelope"></span>
                        <input type="email" name="email" :value="userEmail" id="userEmail" placeholder="E-mail" @input="changeEmail" @keyup="checkemail" @keydown.enter.prevent autocomplete="off">
                    </div>
                    <div class="failnameText text-center"> {{failpwdText}} </div>
                    <div class="form-field d-flex align-items-center">
                        <span class="fas fa-key"></span>
                        <input type="password" name="password" :value="pwd" id="pwd" placeholder="Password" @input="changePwd" @keyup="checkpwd" @keydown.enter.prevent autocomplete="off">
                    </div>
                    <button class="btn mt-2" @click="goSign">가입하기</button>
                </form>

            </div>
        </div>
        
    </div>
</template>

<script>
import http from '@/api/http'
export default {
    name: 'SignUpView',

    data() {
        return {
            userName: '',
            userEmail: '',
            pwd: '',
            failname:false,
            failemail:false,
            failpwd:false,
            passname:false,
            passemail:false,
            passpwd:false,
            failnameText:"",
            failemailText:"",
            failpwdText:"",
        };
    },

    mounted() {
        
    },

    methods: {
        checkname() {
            // eslint-disable-next-line
            let namecheck = /^[가-힣]{2,6}$/;
            if(this.userName < 1 || this.userName > 7 || namecheck.test(this.userName) == false){
                this.failname=true;
                this.passname=false;
                this.failnameText="2~6자 이내로 올바르게 작성!!"
            } else if(namecheck.test(this.userName) == true) {
                this.failname=false;
                this.passname=true;
                this.failnameText=" ";
            }
        },
        changeName(e) {
            this.userName = e.target.value;
        },
        checkemail() {
            // eslint-disable-next-line
            let emailcheck = /^[a-z0-9\.\-_]+@([a-z0-9\-]+\.)+[a-z]{2,6}$/;
            if(emailcheck.test(this.userEmail)==false){
                this.failemail = true;
                this.passemail = false;
                this.failemailText="이메일 형식을 올바르게 작성!!"
            } else {
                this.failemail = false;
                this.passemail = true;
                this.failemailText=" ";
            }
        },
        changeEmail(e) {
            this.userEmail = e.target.value;
        },
        checkpwd() {
            // eslint-disable-next-line
            let pwdcheck = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{4,20}$/;
            if(this.pwd < 4){
                this.failpwd = true;
                this.passpwd = false;
                this.failpwdText = '4글자 이상 입력!!';
            } else if(pwdcheck.test(this.pwd) == false){
                this.failpwd = true;
                this.passpwd = false;
                this.failpwdText = "문자,숫자가 최소 1개 이상 필요!!";
            } else if(pwdcheck.test(this.pwd) == true) {
                this.failpwd = false;
                this.passpwd = true;
                this.failpwdText = " ";
            }
        },
        changePwd(e) {
            this.pwd = e.target.value;
        },
        goSign() {
            if(!this.passname && !this.passemail && !this.passpwd) {
                alert('양식에 맞게 올바르게 입력해 주세요!');
                return;
            }
            let url = "users";
            http.post(url,{name:this.userName, email:this.userEmail, password:this.pwd})
            .then(({data}) => {
                console.log(data);
                alert('회원 가입 성공!');
                this.$router.replace({ name: "login"});
            })
            .catch(() => {
                alert('중복된 이메일 입니다.');
            })
        },
    },
};
</script>

<style scoped>
/* Importing fonts from Google */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700;800;900&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Black+Han+Sans&family=Dongle&family=Jua&family=Noto+Sans+KR&display=swap');

button, .name{
    font-family: 'Do Hyeon', sans-serif;
    font-size: 25px;
}

.signbody1{
    height: 100px;
    background: #212429;
    position: relative;
    z-index: 1;
}
/* Reseting */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins', sans-serif;
}

.signBody {
    /* background: #474E68; */
    height: 100vh;
    position: relative;
    overflow: hidden;
}
.signBody::before {
    /* background: url('https://images.unsplash.com/photo-1556379092-dca659792591?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2064&q=80'); */
    background: url('https://images.unsplash.com/flagged/photo-1559717865-a99cac1c95d8?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1742&q=80');
    background-size: cover;
    background-position: center center;
    height: 100vmin;
    width: 100%;
    position: absolute;
    filter: blur(5px);
    left: 0;
    top: 0;
    right: 0;
    bottom: 0;
    content: "";
    z-index: auto;
    transform:scale(1.01);
    overflow-x: hidden;
    overflow-y: hidden;
}
.wrapperDiv {
    margin: 0;
    padding: 0;
    /* height: 100%; */
    z-index: 1;
    position: relative;
}

.wrapper {
    max-width: 350px;
    min-height: 500px;
    margin: 150px auto;
    padding: 40px 30px 30px 30px;
    background-color: #EEEEEE;
    border-radius: 15px;
    box-shadow: 13px 13px 20px rgba(17, 17, 17, 0.274), -13px -13px 20px rgba(17, 17, 17, 0.274);
    /* position: relative; */
    /* transform: translateY(100px); */
}
.logo {
    width: 80px;
    margin: auto;
}

.logo img {
    width: 100%;
    height: 80px;
    object-fit: cover;
    border-radius: 50%;
    box-shadow: 0px 0px 3px #5f5f5f,
        0px 0px 0px 5px #ecf0f3,
        8px 8px 15px #a7aaa7,
        -8px -8px 15px #fff;
}

.wrapper .name {
    font-weight: 600;
    font-size: 1.4rem;
    letter-spacing: 1.3px;
    padding-left: 10px;
    color: #555;
}
.failnameText {
    color: rgba(248, 51, 51, 0.932);
    font-size: 15px;
    font-family: 'Noto Sans KR', sans-serif;
}
.wrapper .form-field input {
    width: 100%;
    display: block;
    border: none;
    outline: none;
    background: none;
    font-size: 1.2rem;
    color: #666;
    padding: 10px 15px 10px 10px;
    /* border: 1px solid red; */
}

.wrapper .form-field {
    padding-left: 10px;
    margin-bottom: 20px;
    border-radius: 20px;
    box-shadow: inset 8px 8px 8px #cbced1, inset -8px -8px 8px #fff;
}


.wrapper .form-field .fas {
    color: #555;
}

.wrapper .btn {
    box-shadow: none;
    width: 100%;
    height: 40px;
    background-color: #03A9F4;
    color: #fff;
    border-radius: 25px;
    box-shadow: 3px 3px 3px #b1b1b1,
        -3px -3px 3px #fff;
    letter-spacing: 1.3px;
}

.wrapper .btn:hover {
    background-color: #039BE5;
}

.wrapper a {
    text-decoration: none;
    font-size: 0.8rem;
    color: #03A9F4;
}

.wrapper a:hover {
    color: #039BE5;
}
</style>