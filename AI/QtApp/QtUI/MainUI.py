# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'Main.ui'
#
# Created by: PyQt5 UI code generator 5.11.3
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(1182, 707)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Preferred, QtWidgets.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(MainWindow.sizePolicy().hasHeightForWidth())
        MainWindow.setSizePolicy(sizePolicy)
        font = QtGui.QFont()
        font.setFamily("Arial Black")
        font.setPointSize(10)
        font.setBold(False)
        font.setItalic(False)
        font.setWeight(50)
        MainWindow.setFont(font)
        MainWindow.setStyleSheet("background-color: rgb(0,0,0);\n"
"font: 9pt \"HY헤드라인M\";\n"
"selection-background-color:rgb(20,20,20);\n"
"font:10pt \"Arial Black\";\n"
"color:white")
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.gridLayout_2 = QtWidgets.QGridLayout(self.centralwidget)
        self.gridLayout_2.setObjectName("gridLayout_2")
        self.gridLayout = QtWidgets.QGridLayout()
        self.gridLayout.setContentsMargins(10, 10, 10, 10)
        self.gridLayout.setSpacing(2)
        self.gridLayout.setObjectName("gridLayout")
        self.cctv_2 = QtWidgets.QLabel(self.centralwidget)
        self.cctv_2.setStyleSheet("background-color: rgb(20,20,20);")
        self.cctv_2.setText("")
        self.cctv_2.setObjectName("cctv_2")
        self.gridLayout.addWidget(self.cctv_2, 0, 1, 1, 1)
        self.cctv_4 = QtWidgets.QLabel(self.centralwidget)
        self.cctv_4.setStyleSheet("background-color: rgb(20,20,20);")
        self.cctv_4.setText("")
        self.cctv_4.setObjectName("cctv_4")
        self.gridLayout.addWidget(self.cctv_4, 1, 1, 1, 1)
        self.cctv_3 = QtWidgets.QLabel(self.centralwidget)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Expanding, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.cctv_3.sizePolicy().hasHeightForWidth())
        self.cctv_3.setSizePolicy(sizePolicy)
        self.cctv_3.setStyleSheet("background-color: rgb(20,20,20);")
        self.cctv_3.setText("")
        self.cctv_3.setObjectName("cctv_3")
        self.gridLayout.addWidget(self.cctv_3, 1, 0, 1, 1)
        self.cctv_1 = QtWidgets.QLabel(self.centralwidget)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Expanding, QtWidgets.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.cctv_1.sizePolicy().hasHeightForWidth())
        self.cctv_1.setSizePolicy(sizePolicy)
        self.cctv_1.setStyleSheet("background-color: rgb(20,20,20);")
        self.cctv_1.setText("")
        self.cctv_1.setObjectName("cctv_1")
        self.gridLayout.addWidget(self.cctv_1, 0, 0, 1, 1)
        self.gridLayout.setColumnMinimumWidth(0, 430)
        self.gridLayout.setColumnMinimumWidth(1, 430)
        self.gridLayout.setRowMinimumHeight(0, 310)
        self.gridLayout.setRowMinimumHeight(1, 310)
        self.gridLayout.setColumnStretch(0, 1)
        self.gridLayout.setColumnStretch(1, 1)
        self.gridLayout.setRowStretch(0, 1)
        self.gridLayout.setRowStretch(1, 1)
        self.gridLayout_2.addLayout(self.gridLayout, 0, 0, 1, 1)
        self.textBrowser = QtWidgets.QTextBrowser(self.centralwidget)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Preferred, QtWidgets.QSizePolicy.Preferred)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.textBrowser.sizePolicy().hasHeightForWidth())
        self.textBrowser.setSizePolicy(sizePolicy)
        self.textBrowser.setMinimumSize(QtCore.QSize(256, 642))
        self.textBrowser.setStyleSheet("border-width: 3px;\n"
"border-style: solid;\n"
"border-color: rgb(40,40,40)")
        self.textBrowser.setLineWidth(1)
        self.textBrowser.setObjectName("textBrowser")
        self.gridLayout_2.addWidget(self.textBrowser, 0, 1, 1, 1)
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 1182, 24))
        self.menubar.setObjectName("menubar")
        self.menu = QtWidgets.QMenu(self.menubar)
        self.menu.setObjectName("menu")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)
        self.menubar.addAction(self.menu.menuAction())

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "떡잎방범대"))
        self.menu.setTitle(_translate("MainWindow", "Menu"))


if __name__ == "__main__":
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())

