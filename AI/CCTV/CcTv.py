import cv2


class CcTv():
    def rtsp():
        cam = cv2.VideoCapture(
            'rtsp://admin:12345@223.171.47.23:1111/stream_ch00_0')
        return cam
        # while True:
        #     _, img = cam.read()
        #     cv2.imshow("TEST", img)
        #     cv2.waitKey(1)
