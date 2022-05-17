<template id="cctvtimer">
  <div class="container">
    <div class="timer-border">
      <div class="timer-inner">
        <!--  감지중입니다 만들기 -->
        <va-button  @click="showModal = !showModal" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(21, 78, 193); border-color: rgb(21, 78, 193); background: rgba(0, 0, 0, 0);" >Start</va-button>
        <va-button @click="stop" class="va-button va-button--outline va-button--normal mr-2 mb-2" style="color: rgb(228, 34, 34); border-color: rgb(228, 34, 34); background: rgba(0, 0, 0, 0);">Stop</va-button>
        <va-button @click="reset" class="va-button va-button--outline va-button--normal mr-2 mb-2">Reset</va-button>
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
              <va-button class="timer-mg"  @click="showcomfirm = !showcomfirm, showModal = !showModal, setTimetwo">
                2시간
              </va-button>
              <va-button class="timer-mg"  @click="showcomfirm = !showcomfirm, showModal = !showModal, setTime">
                직접설정
              </va-button>
            </div>
            <div>
              <va-input v-model="setTimecustom"></va-input>
              <va-button class="timer-mg" @click="showcomfirm = !showcomfirm, showModal = !showModal, setTime">
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
        <!-- <p>{{formattedElapsedTime}}</p> -->
      </div>
    </div>
  </div>
</template>

<script>
// import message from './popup-message'
import http from '@/components/common/axios.js'

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
    };
  },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
  },
  methods: {
    start() {
      this.timer = setInterval(() => {
        this.elapsedTime += 1000;
      }, 1000);
    },
    stop() {
      clearInterval(this.timer);
    },
    reset() {
      this.elapsedTime = 0;
    },
    setTimeone() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          this.setTimecustom = 1;
        }
    },
    setTimetwo() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          this.setTimecustom = 1;
        }
    },
    setTime() {
        if (this.showModal) {
          this.showModal = false;
          this.showcomfirm = true;
          this.setTimecustom = this.setTimecustom;
        }
    },
    startTimer() {
        this.timerState = true;
        this.setTimer()

    },
    timerStop() {
        this.setTimecustom = 0;
        this.showcomfirm = false;
        this.showModal = true

    },
    setTimer () {
      const startdate = new Date()
      // console.log(Date.prototype.toJSON())
      // console.log(Date.prototype.toJSON())
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
    width: 700px;
    height: 200px;
  }
  .timer-inner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 600px;
    height: 150px;
    color: red;
  }
  .timer-mg {
    margin: 0px 10px;
  }
</style>