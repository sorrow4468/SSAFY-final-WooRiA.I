import cv2

class CcTv():
    def rtsp():
        cam = cv2.VideoCapture(
            'rtsp://sorrow4468:q2eqweqw3q23@sorrow4468.iptime.org:554/stream_ch001')
        return cam
        # while True:
        #     _, img = cam.read()
        #     cv2.imshow("TEST", img)
        #     cv2.waitKey(1)