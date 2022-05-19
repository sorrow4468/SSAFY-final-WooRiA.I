<template id="cctvtimer">
  <div class="container">
    <div class="timer-border">

      <div class="timer-inner">
        <!--  감지중입니다 만들기 -->
        <div v-if="!timerState" class="timer-s">
          <h1>감시를 시작하세요</h1>
          <va-button @click="showModal = !showModal" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(21, 78, 193); border-color: rgb(21, 78, 193); background: rgba(0, 0, 0, 0);" >Start</va-button>
        </div>
        <div v-else class="timer-s">
          <h1 style="color: rgb(228, 34, 34);"> 우리 AI 가 실행중입니다 </h1>
          <va-button @click="stop" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(228, 34, 34); border-color: rgb(228, 34, 34); background: rgba(0, 0, 0, 0);">정지</va-button>
          <h1>{{hour}} : {{ min }} : {{sec}}</h1>
        </div>

        
        
      </div>
    </div>
            
        
        <va-modal
          v-model="showModal"
          hide-default-actions
          overlay-opacity="0.2"
        >
          <template #header>
            <h2>시간 설정</h2>
          </template>
          <div style="margin-top : 5px">{{ message }}</div>
          <template #footer>
            <div>
              <va-button class="timer-mg" @click="setTimeone" >
                1시간
              </va-button>
              <va-button class="timer-mg"  @click=" setTimetwo">
                2시간
              </va-button>
              <va-button class="timer-mg"  @click=" setTime">
                직접설정
              </va-button>
            </div>
            <div>
              <va-input v-model="setTimecustom"></va-input>
              <va-button class="timer-mg" @click=" setTime">
                보내기
              </va-button>
            </div>
          </template>
            
        </va-modal>
        
        <va-modal
          v-model="showcomfirm"
          hide-default-actions
          overlay-opacity="0.2"
        >
          <template #header>
            <h2>시간 설정</h2>
          </template>
          <div style="margin-top : 5px">{{setTimecustom}}{{ startMessage }}</div>
          <template #footer>
              <va-button class="timer-mg" @click="startTimer">
                시작
              </va-button>
              <va-button class="timer-mg" @click="timerStop">
                취소
              </va-button>
          </template>
        </va-modal>
  </div>
</template>

<script>
// import message from './popup-message'
import http from '@/components/common/axios.js'
import httpB from '@/components/common/axiosB.js'

export default {
  name: "cctvtimer",
  data() {
    return {
      elapsedTime: 0,
      timer: undefined,
      showModal: false,
      showcomfirm: false,
      message : '시간을 설정해 주세요',
      startMessage :'시간 선택하셨습니다',
      setTimecustom : 0,
      timerState : false,
      startHours : undefined,
      startMinutes : undefined,
      startSeconds : undefined,
      enddate : undefined,
      countDown : undefined,
      min : undefined,
      hour : undefined,
      sec : undefined
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    },
  },
  methods: {
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime -= 1000;
      }, 1000);
    },
    stop() {
      clearInterval(this.timer);
      this.timerState = false;
    },
    reset() {
      this.elapsedTime = 0;
    },
    setTimeone() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          this.setTimecustom = 1;
          this.countDown = 3600000;
        }
    },
    setTimetwo() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          this.setTimecustom = 2;
          this.countDown = 7200000;

        }
    },
    setTime() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          // this.setTimecustom = this.setTimecustom;
          console.log(this.setTimecustom)
          const tests = this.setTimecustom;
          this.countDown = (3600000 * tests);
          this.startTimer()

        }
    },
    startTimer() {
        this.timerState = true;
        this.setTimer()
        this.showcomfirm = false;
        this.start()
        this.countDownTimer()

    },
    timerStop() {
        this.setTimecustom = 0;
        this.showcomfirm = false;
        this.showModal = true
        this.countDown = 1;
        

    },
    setTimer () {
      const startdate = new Date()
      console.log(Date.parse(startdate))
      console.log(startdate)
      const endDate = new Date(startdate)
      endDate.setHours(endDate.getHours()+1)
      http.post(
            '/cctv/set/timer',
        {
          "startTime": startdate
,
          "endTime": endDate
        }
          ).then((res)=>{
            console.log(res)
            
            }
          ).catch((err) => {
            console.log(err)
            })
      //  시작 시간 체크
    },


    refresh() {
      httpB.get(
        'https://k6e2021.p.ssafy.io/api/streaming/cctv1/start'

      ).then(

        'https://k6e2021.p.ssafy.io/api/streaming/cctv2/start'

      ).then(
        'https://k6e2021.p.ssafy.io/api/streaming/cctv3/start'
      ).then(
       'https://k6e2021.p.ssafy.io/api/streaming/cctv4/start' 

      ).catch((err) => { 
        console.log(err)}
      )
    },
    countDownTimer() {
                console.log('들어옴')
                if (!this.timerState){
                  this.countDown = 0;
                }
                if(this.countDown > 0) {
                    setTimeout(() => {
                        this.countDown -= 1
                        this.countDownTimer()
                    }, 1000)
                   
                }else {
                  this.timerState = false;
                }
                 console.log(this.countDown)
                    let h = Math.trunc((this.countDown) /1000/ 3600);
                    let m = Math.trunc((this.countDown )  /1000/ 60) % 60;
                    let s = Math.trunc((this.countDown)) % 60
                    console.log(h,m,s)
                    if (10<h) {
                      this.hour = '0'+h
                    }else {
                      this.hour = h;
                    }
                    if (m<10) {
                      this.min = '0'+m
                    }else {
                      this.min = m;
                    }
                    
                    if (s<10) {
                      this.sec = '0'+s
                    }else {
                      this.sec = s;
                    }
                    console.log(this.hour, this.min, this.sec)

            }

  }
  
};
</script>
<style scoped>
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: auto;
    flex-direction: column;
  }
  .hour, .min, .secs {
    font-size: 4em;
  }
  strong {
    color: blue;
  }
  p {
    font-family: 'Lucida Sans', sans-serif;
    font-size: 20px;
  }
  .timer-border {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1000px;
    height: 100px;
  }
  .timer-inner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1000px;
    height: 100px;
  }
  .timer-s {
    flex-direction: column;
    display: flex;
    justify-content: center;
    justify-items: center;
    align-items: center;
    width: 600px;
    height: 150px;
    margin-bottom: 2rem;
  }
  .timer-mg {
    margin: 0px 10px;
  }
    .icon-size {
    size: 10rem;
  }
</style>