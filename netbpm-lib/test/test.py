import tkinter as tk

from api.ppm_io import read, write
from api.ppm_render import create_canvas
from api.default_filters import downsize_filter

pict = read('pictures/test3.ppm')
# print(pict)
# write('pictures/test2.ppm', pict)
#
pict2 = read('pictures/test_p1.ppm')
# print(pict2)
#
pict3 = read('pictures/test_p2.ppm')
# print(pict3)

# try:
#     del pict3[0, 0]
# except NotImplementedError as e:
#     print('Caught error "%s"' % e)

window = tk.Tk()
# create_canvas(window, pict3, scale_x=20, scale_y=100, x=100)

pict4 = read('pictures/ein2.ppm')
# create_canvas(window, pict, scale=100.5)

create_canvas(window, downsize_filter(pict4, 1, 4))

tk.mainloop()