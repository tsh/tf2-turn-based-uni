import asyncio
import json
import random

import websockets

game_map = [[random.randint(0, 2) for _ in range(10)] for _ in range(10)]

@asyncio.coroutine
def hello(websocket, path):
    while True:
        message = yield from websocket.recv()
        print(message)
        if message is None:
            # connection closed
            print("closed")
            break
        yield from websocket.send(json.dumps({'type': 'echo',
                                              'message': message}))
        yield from websocket.send(json.dumps({'type': 'map',
                                              'map': game_map}))

start_server = websockets.serve(hello, 'localhost', 8765)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
