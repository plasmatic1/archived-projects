import tkinter as tk

# TODO: Rendering stuff

MAX_CLR = 256
P1_COLORS = ('#000000', '#FFFFFF')


def __format_hex(c_scale_factor):
    def fun(clr):
        return format(int(clr * c_scale_factor), 'x').zfill(2)

    return fun


def __hex_p1():
    def fun(clr):
        return P1_COLORS[clr]

    return fun


def __hex_p2(c_scale_factor):
    hex_fun = __format_hex(c_scale_factor)

    def fun(clr):
        return '#' + (hex_fun(clr) * 3)

    return fun


def __hex_p3(c_scale_factor):
    hex_fun = __format_hex(c_scale_factor)

    def fun(clr):
        return '#' + ''.join(map(hex_fun, clr.tuple()))

    return fun


def draw_on_canvas(canvas, picture, scale_x=-1, scale_y=-1, x=0, y=0):
    c_scale_factor = 255 / picture.range  # P2 and P3 ONLY

    if picture.encoding == 'P1':
        hex_clr = __hex_p1()
    elif picture.encoding == 'P2':
        hex_clr = __hex_p2(c_scale_factor)
    else:
        hex_clr = __hex_p3(c_scale_factor)

    for j in range(picture.height):
        for i in range(picture.width):  # Reversed for no reason
            sca_x, sca_y = i * scale_x + x, j * scale_y + y
            clr = hex_clr(picture[i, j])

            canvas.create_rectangle(sca_x, sca_y, sca_x + scale_x, sca_y + scale_y, fill=clr, outline=clr)


def create_canvas(window, picture, scale=1, scale_x=-1, scale_y=-1, x=0, y=0):
    # Change scale to scalex and scaley
    if scale_x == -1:
        scale_x = scale
    if scale_y == -1:
        scale_y = scale

    canvas = tk.Canvas(window, width=picture.width * scale_x + x, height=picture.height * scale_y + y)
    canvas.pack()

    draw_on_canvas(canvas, picture, scale_x, scale_y, x, y)

    return canvas
