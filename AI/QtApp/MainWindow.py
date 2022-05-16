import queue
from re import X
import threading
import cv2
import datetime
import time
import boto3
import os
import torch
from matplotlib import pyplot as plt
import numpy as np
import math
import os
import tensorflow as tf
import numpy as np
import cv2
import mediapipe as mp
from sklearn.model_selection import train_test_split
from tensorflow.keras.utils import to_categorical
from matplotlib import pyplot as plt

# os.environ['KMP_DUPLICATE_LIB_OK']='True'

from PIL import Image as im
from PyQt5.QtWidgets import QMainWindow
from QtApp.QtUI.MainUI import Ui_MainWindow
from PyQt5.QtGui import QIcon
from PyQt5 import QtGui, QtWidgets
from PyQt5.QtCore import *
from CCTV.CcTv import CcTv
from kafka import KafkaProducer
from json import dumps
from yolov5.utils.general import (LOGGER, check_img_size, non_max_suppression, scale_coords, 
                                check_imshow, xyxy2xywh, increment_path)
from yolov5.utils.torch_utils import select_device, time_sync
from yolov5.utils.plots import Annotator, colors
from deep_sort.utils.parser import get_config
from deep_sort.deep_sort import DeepSort

class roiimage:
    def __init__(self,frame,x,y,w,h):
        self.frame = frame
        self.x = x
        self.y = y
        self.w = w
        self.h = h

class MainWindow(QMainWindow):
    def initialize(self, mainForm: Ui_MainWindow):
        print(torch.cuda.is_available())
        self.mainForm = mainForm
        self.producer = KafkaProducer(acks=0, compression_type='gzip', bootstrap_servers=[
            '52.79.114.28:9092'], value_serializer=lambda x: dumps(x).encode('utf-8'))
        self.setWindowIcon(QIcon("icon.png"))
        self.cnt = 0
        self.cctv_1 = None
        self.cctv_2 = None
        self.cctv_3 = None
        self.cctv_4 = None
        self.center_prev_points = []
        self.tracking_objects = {}
        self.track_id = 0
        self.count = -1
        self.static_image_mode=False
        self.upper_body_only = False
        self.roi_person = None
        smoth_landmarks=True
        min_detection_confidence=0.5
        min_tracking_confidence=0.5
        self.roi_que = queue.Queue()
        self.model = torch.hub.load('ultralytics/yolov5', 'custom', path='./yolov5s.pt', force_reload=True)
        self.Frame_1 = None
        # self.model = torch.hub.load('ultralytics/yolov5', 'yolov5s')
        # self.device = select_device(0)
        # cfg = get_config()
        # cfg.merge_from_file("deep_sort/configs/deep_sort.yaml")
        # self.deepsort = DeepSort('osnet_x0_25',
        #                 max_dist=cfg.DEEPSORT.MAX_DIST,
        #                 max_iou_distance=cfg.DEEPSORT.MAX_IOU_DISTANCE,
        #                 max_age=cfg.DEEPSORT.MAX_AGE, n_init=cfg.DEEPSORT.N_INIT, nn_budget=cfg.DEEPSORT.NN_BUDGET,
        #                 use_cuda=True)

        # self.names = self.model.module.names if hasattr(self.model, 'module') else self.model.names
        
        self.s3 = self.s3_connection()
        self.CCTV_start()
        self.thread_CCTV = Worker(target=self.thread_CCTV_run)
        self.thread_CCTV.start()
        test = Worker(target=self.testfunc)
        test.start()


    def s3_connection(self):
        try:
            s3 = boto3.client(
                service_name="s3",
                region_name="ap-northeast-2",  # 자신이 설정한 bucket region
                aws_access_key_id="AKIAZM2UBXT27P5Z2PGL",
                aws_secret_access_key="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",
            )
        except Exception as e:
            print(e)

        else:
            print("s3 bucket connected!")
            return s3

    def s3_put_object(self, bucket, filepath, access_key):
        """
        s3 bucket에 지정 파일 업로드
        :param s3: 연결된 s3 객체(boto3 client)
        :param bucket: 버킷명
        :param filepath: 파일 위치
        :param access_key: 저장 파일명
        :return: 성공 시 True, 실패 시 False 반환
        """
        try:
            self.s3.upload_file(
                Filename=filepath,
                Bucket=bucket,
                Key=access_key,
                ExtraArgs={"ContentType": "video/avi", "ACL": "public-read"},
            )
            print("upload video to aws s3!")
        except Exception as e:
            return False
        return True

    def s3_get_image_url(self, filename):
        """
        s3 : 연결된 s3 객체(boto3 client)
        filename : s3에 저장된 파일 명
        """
        location = self.s3.get_bucket_location(
            Bucket={"ssafit-01-bucket"})["LocationConstraint"]
        return f"https://{{ssafit-01-bucket}}.s3.{location}.amazonaws.com/{filename}.avi"

    def thread_CCTV_run(self):
        Frame_2, Frame3, Frame4 = None, None, None
        center_points = []
        if self.cctv_1 != None and self.cctv_1.grab():
            _, self.Frame_1 = self.cctv_1.retrieve()
            self.count += 1
            if self.Frame_1 is not None and self.roi_que.empty()==True:
                result = self.model(self.Frame_1)
                # result = self.model(Frame_1, augment = True)
                labels, cord = result.xyxyn[0][:,-1].cpu().numpy(), result.xyxyn[0][:, :-1].cpu().numpy()
                # annotator = Annotator(Frame_1, line_width=2, pil=not ascii)
                # det = result.pred[0]

                # if det is not None and len(det):
                #     xywhs = xyxy2xywh(det[:, 0:4])
                #     confs = det[:, 4]
                #     clss = det[:, 5]
                #     outputs = self.deepsort.update(xywhs.cpu(), confs.cpu(), clss.cpu(), Frame_1)
                #     if len(outputs) > 0:
                #         for j, (output, conf) in enumerate(zip(outputs, confs)):

                #             bboxes = output[0:4]
                #             id = output[4]
                #             cls = output[5]

                #             c = int(cls)  # integer class
                #             label = f'{id} {self.names[c]} {conf:.2f}'
                #             annotator.box_label(bboxes, label, color=colors(c, True))

                # result_img = annotator.result()

                # print(result)
                ob = -1
                objectpoint = []
                if len(cord)>0:
                    temp = []
                    for box in cord:
                        ob+=1
                        if int(labels[ob]) != 0:
                            continue
                        height, width, c = self.Frame_1.shape
                        x = box[0]*width
                        y = box[1]*height
                        w = box[2]*width - x
                        h = box[3]*height - y
                        self.roi_person = self.Frame_1[int(y):int(y+h),int(x):int(x+w)]
                        pose_img = self.roi_person.copy()
                        roi_info = roiimage(pose_img, x,y,w,h)
                        temp.append(roi_info)
                        cx = (x + x + w)/2
                        cy = (y + y + h)/2
                        center_points.append((int(cx),int(cy)))
                        objectpoint.append((int(cx),int(cy)))
                    index = 0
                    ob = -1
                    for pt in objectpoint:
                        inner_index = 0
                        for pt2 in objectpoint:
                            if index >= inner_index:
                                inner_index+=1
                                continue
                            dis = math.hypot(pt2[0] - pt[0], pt2[1] - pt[1])
                            print(dis)
                            if dis<250:
                                temp_ob = temp[inner_index]
                                self.roi_que.put(temp_ob)
                                temp_ob = temp[index]
                                self.roi_que.put(temp_ob)
                            inner_index+=1
                        index+=1
                    for box in cord:
                        ob+=1
                        if int(labels[ob]) != 0:
                            continue
                        height, width, c = self.Frame_1.shape
                        x = box[0]*width
                        y = box[1]*height
                        w = box[2]*width - x
                        h = box[3]*height - y
                        # cx = (x + x + w)/2
                        # cy = (y + y + h)/2
                        # center_points.append((int(cx),int(cy)))
                        # objectpoint.append((int(cx),int(cy)))
                        # # cv2.circle(self.Frame_1, (int(cx),int(cy)), 5, (0,0,255),-1)
                        cv2.rectangle(self.Frame_1, (int(x),int(y)), (int(x+w), int(y+h)), (0,255,0), 2)
                    if(self.count <= 2):
                        for pt in center_points:
                            for pt2 in self.center_prev_points:
                                distance = math.hypot(pt2[0] - pt[0], pt2[1] - pt[1])

                                if distance < 50:
                                    self.tracking_objects[self.track_id] = pt
                                    self.track_id+=1
                    else :
                        tracking_objects_copy = self.tracking_objects.copy()
                        center_points_copy = center_points.copy()

                        for object_id,pt2 in tracking_objects_copy.items():
                            object_exists = False
                            for pt in center_points_copy:
                                distance = math.hypot(pt2[0] - pt[0], pt2[1] - pt[1])

                                if distance < 50:
                                    self.tracking_objects[object_id] = pt
                                    object_exists = True
                                    if pt in center_points:
                                        center_points.remove(pt)
                                    continue
                            if not object_exists:
                                self.tracking_objects.pop(object_id)

                        for pt in center_points:
                            self.tracking_objects[self.track_id] = pt
                            self.track_id += 1

                    for object_id, pt in self.tracking_objects.items():
                        cv2.circle(self.Frame_1, pt, 5, (0,0,255), -1)
                        cv2.putText(self.Frame_1, str(object_id), (pt[0], pt[1]), 0, 1, (0, 0, 255), 2)

                    # print("TRACKING")
                    # print(self.tracking_objects)

                width = (int)((self.mainForm.centralwidget.width()-227)/2)-10
                height = (int)((self.mainForm.centralwidget.height()-20)/2)-10
                pixmap = QtGui.QPixmap(
                    self.convert_image_to_QImage(
                        self.resize_image(self.Frame_1, width, height)
                        # self.resize_image(np.squeeze(result.render()), width, height)
                        ))
                self.mainForm.cctv_1.setPixmap(pixmap)
                
                self.mainForm.cctv_1.update()
                self.center_prev_points = center_points.copy()

                # AI
                # 상황이 발생하면.
                # 형이 전처리한 이미지.

                # makeVideo
                # 동영상으로 제작.
                # time.sleep(1)
                # now = datetime.datetime.now().strftime("%d_%H-%M-%S")
                # video = cv2.VideoWriter("C:/Users/dlrjs/Desktop/S06P31E202/AI/" + str(now) + ".avi", fourcc, 20.0, (frame.shape[1], frame.shape[0]))

                if self.cnt == 0:
                    self.cnt = 1
                    t = threading.Thread(target=self.record)  # video 녹음 쓰레드
                    t.start()

    # 형이 하나하나 전처리한 프레임을 리스트에 넣어서 주는 방식으로 하시는지
    # cctv(전처리) -> record

    def record(self):
        cctv_1 = CcTv.rtsp()
        trigger = True
        flag = True
        start = time.time()
        # while trigger == True :
        #     Frame_1, Frame_2, Frame3, Frame4 = None, None, None, None
        #     if cctv_1 != None and cctv_1.grab():
        #         _, Frame_1 = cctv_1.retrieve()

        #         if flag == True :
        #             print("video record start!")
        #             now = datetime.datetime.now().strftime("%d_%H-%M-%S")
        #             fourcc = cv2.VideoWriter_fourcc(*'XVID')
        #             path = "C:/Users/dlrjs/Desktop/S06P31E202/AI/" + str(now) + ".avi"
        #             video = cv2.VideoWriter("C:/Users/dlrjs/Desktop/S06P31E202/AI/" + str(now) + ".avi", fourcc, 20.0, (Frame_1.shape[1], Frame_1.shape[0]))
        #             flag = False

        #         video.write(Frame_1)
        #         if (time.time() - start) > 5 :
        #             trigger = False
        #             video.release()
        #             print("video record end!")
        #             print(self.s3_put_object("ssafit-01-bucket", path, str(now)+ ".avi"))
        #             if os.path.isfile(path):
        #                 os.remove(path)
        #             # connect to web
        #             url = self.s3_get_image_url(str(now))
        #             data = {'str' : url}
        #             self.producer.send('kafka-demo2', value=data)

    def CCTV_start(self):
        self.cctv_1 = CcTv.rtsp()

    def convert_image_to_QImage(self, img):

        height, width, channel = img.shape
        bytesPerLine = width * channel
        format = QtGui.QImage.Format_RGB888

        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

        return QtGui.QImage(img.data, width, height, bytesPerLine, format)

    def resize_image(self, img, width, height):
        return cv2.resize(img, dsize=(width, height),fx=0.5, fy=0.5, interpolation=cv2.INTER_AREA)
    def testfunc(self):
        sequence = []
        sentence = []
        predictions = []
        threshold = 0.5
        actions = np.array(['assult', 'faint'])
        model = tf.keras.models.load_model('action.h5')
        while True:
            if(self.roi_que.empty()!=True):
                frame_info = self.roi_que.get()
                mpDraw = mp.solutions.drawing_utils
                mpPose = mp.solutions.pose
                pose = mpPose.Pose()
                roi = cv2.cvtColor(frame_info.frame,cv2.COLOR_BGR2RGB)
                results = pose.process(roi)
                pose = np.array([[res.x, res.y, res.z, res.visibility] for res in results.pose_landmarks.landmark]).flatten() if results.pose_landmarks else np.zeros(33*4)
                keypoints = np.concatenate([pose])
                sequence.append(keypoints)
                sequence = sequence[-1:]
                if len(sequence) == 1:
                    res = model.predict(np.expand_dims(sequence, axis=0))[0]
                    print(actions[np.argmax(res)])
                    predictions.append(np.argmax(res))

                    if np.unique(predictions[-10:])[0]==np.argmax(res): 
                        if res[np.argmax(res)] > threshold: 
                            
                            if len(sentence) > 0: 
                                if actions[np.argmax(res)] != sentence[-1]:
                                    sentence.append(actions[np.argmax(res)])
                            else:
                                sentence.append(actions[np.argmax(res)])

                    if len(sentence) > 5: 
                        sentence = sentence[-5:]
                    print(res)
                if results.pose_landmarks:
                    mpDraw.draw_landmarks(frame_info.frame, results.pose_landmarks,mpPose.POSE_CONNECTIONS)
                # self.Frame_1[int(frame_info.y):int(frame_info.y+frame_info.h),int(frame_info.x):int(frame_info.x+frame_info.w)] = frame_info.frame
                # width = (int)((self.mainForm.centralwidget.width()-227)/2)-10
                # height = (int)((self.mainForm.centralwidget.height()-20)/2)-10
                # pixmap = QtGui.QPixmap(
                #     self.convert_image_to_QImage(
                #         self.resize_image(frame_info.frame, width, height)
                #         # self.resize_image(np.squeeze(result.render()), width, height)
                #         ))
                # self.mainForm.cctv_2.setPixmap(pixmap)
                
                # self.mainForm.cctv_2.update()
            else:
                break
            if cv2.waitKey(1) & 0xFF == ord('q'):
                break

class Worker(QThread):
    def __init__(self, target):
        assert callable(target)
        super().__init__()
        self.func = target
        self.isWorking = True

    def run(self):
        while self.isWorking:
            self.func()
            self.sleep(0)
