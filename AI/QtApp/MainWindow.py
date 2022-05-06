import cv2

from PyQt5.QtWidgets import QMainWindow
from QtApp.QtUI.MainUI import Ui_MainWindow
from PyQt5.QtGui import QIcon
from PyQt5 import QtGui, QtWidgets
from PyQt5.QtCore import *

from CCTV.CcTv import CcTv

class MainWindow(QMainWindow):
    def initialize(self, mainForm: Ui_MainWindow):
        self.mainForm = mainForm
        self.setWindowIcon(QIcon("icon.png"))
        
        self.cctv_1 = None
        self.cctv_2 = None
        self.cctv_3 = None
        self.cctv_4 = None
        self.CCTV_start()
        self.thread_CCTV = Worker(target = self.thread_CCTV_run)
        self.thread_CCTV.start()
    
    def thread_CCTV_run(self):
        Frame_1, Frame_2, Frame3, Frame4 = None, None, None, None
        if self.cctv_1 != None and self.cctv_1.grab():
            _, Frame_1 = self.cctv_1.retrieve()
            if Frame_1 is not None:
                width = (int)((self.mainForm.centralwidget.width()-227)/2)-10
                height = (int)((self.mainForm.centralwidget.height()-20)/2)-10
                pixmap = QtGui.QPixmap(
                    self.convert_image_to_QImage(
                        self.resize_image(Frame_1, width,height)))
                self.mainForm.cctv_1.setPixmap(pixmap)
                self.mainForm.cctv_1.update()

    def CCTV_start(self):
        self.cctv_1 = CcTv.rtsp()

    def convert_image_to_QImage(self, img):

        height, width, channel = img.shape
        bytesPerLine = width * channel
        format = QtGui.QImage.Format_RGB888

        img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)

        return QtGui.QImage(img.data, width, height, bytesPerLine, format)

    def resize_image(self, img, width, height):
        return cv2.resize(img, dsize=(width, height), interpolation=cv2.INTER_AREA)
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