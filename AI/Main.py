import sys
from PyQt5 import uic
from PyQt5.QtWidgets import QApplication, QMainWindow
from QtApp.QtUI import MainUI
from QtApp.MainWindow import MainWindow

def runApplication():
    app = QApplication(sys.argv)

    mainWindow = MainWindow()
    mainForm = MainUI.Ui_MainWindow()
    mainForm.setupUi(mainWindow)
    mainForm.retranslateUi(mainWindow)
    mainWindow.initialize(mainForm)

    mainWindow.show()
    return app.exec_()

if __name__ == "__main__":
    sys.exit(runApplication())
