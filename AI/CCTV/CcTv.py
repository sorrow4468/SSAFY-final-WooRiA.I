import cv2


class CcTv():
    def rtsp():
        cam = cv2.VideoCapture(
            'rtsp://tapoadmin:tapoadmin@bandong.iptime.org:554/stream1')
        return cam
        # while True:
        #     _, img = cam.read()
        #     cv2.imshow("TEST", img)
        #     cv2.waitKey(1)
