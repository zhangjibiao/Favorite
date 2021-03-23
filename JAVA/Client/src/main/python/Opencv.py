# -*- coding: UTF-8 -*-
import time
import sys

print("Iris captured successfully")
sys.stdout.flush()
time.sleep(5)

print("模拟等待下一次捕获虹膜，等待2S")
sys.stdout.flush()
time.sleep(2)

print("Iris captured successfully")
sys.stdout.flush()

time.sleep(60*60*24)

