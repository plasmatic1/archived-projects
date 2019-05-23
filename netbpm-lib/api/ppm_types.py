from collections import namedtuple

rgb_ = namedtuple('rgb_', 'r g b')  # RGB

'''
RGB Class:
r, g, b => RGB Colours

Functions:

from_arr(arr) => Static function that creates a RGB object from a list or list-like object

tuple(self) => Function that turns the RGB object into a tuple
__str__(self) => String representation
__repr__(self) => same as __str__
'''


class rgb(rgb_):
    def __init__(self, r, g, b):
        super(rgb, self).__init__()

    @classmethod
    def from_arr(cls, arr):
        return rgb(arr[0], arr[1], arr[2])

    def tuple(self):
        return self.r, self.g, self.b

    def __str__(self):
        return ' '.join(map(lambda i: str(i).rjust(3), (self.r, self.g, self.b)))

    def __repr__(self):
        return str(self)


'''
Picture Class
encoding => Encoding of the image (P1, P2, P3)
width => Width of the image (in pixels)
height => Height of the image (in pixels)
range => Max colour value (int)
pixels => A 2d array of pixels (values are either int if encoding is P1 or P2 and rgb() if e)
n
Functions:

filter(self, filter_fun) => This function applies the parameter filter_fun to every single pixel, the function should take the parameters (picture, x, y, pixel) and must return a pixel value
    picture => The picture that the function is being executed on
    x, y => The x and y coordinates of the current pixel
    pixel => The value of the current pixel (Either an int or an rgb value
__getitem__(self, key) => Allows for direct access of pixels using picture[x, y]
__setitem__(self, key, value) => Allows for direct setting of pixels using picture[x, y]
__hasitem__(self, key) => checks is picture[x, y] is within the range of the picture or not
__str__(self) => String representation
__repr__(self) => same as __str__
'''


class Picture:
    @classmethod
    def __base_p1_p2(cls):
        return 0

    @classmethod
    def __base_p3(cls):
        return rgb(0, 0, 0)

    def __wrap_index_check(fun):
        def wrapper(self, key, val=None):
            if not (key in self):
                raise IndexError('Index %d, %d is out of range (Size: %d x %d)' %
                                 (key[0], key[1], self.width, self.height))

            if val is None:
                return fun(self, key)
            else:
                return fun(self, key, val)

        return wrapper

    def __init__(self, encoding, width, height, c_range):
        self.encoding = encoding
        self.width = width
        self.height = height
        self.range = c_range
        if self.encoding == 'P3':
            self.base = Picture.__base_p3
        else:
            self.base = Picture.__base_p1_p2
        self.pixels = [[self.base()] * height for _ in range(width)]

    def filter(self, filter_fun):
        for i, row in enumerate(self.pixels):
            for j, col in enumerate(row):
                self[i, j] = filter_fun(self, i, j, col)

    @__wrap_index_check
    def __setitem__(self, key, val):
        self.pixels[key[0]][key[1]] = val

    @__wrap_index_check
    def __getitem__(self, key):
        return self.pixels[key[0]][key[1]]

    def __contains__(self, key):
        return 0 <= key[0] < self.width and 0 <= key[1] < self.height

    def __delitem__(self, key):
        raise NotImplementedError('This is fucking impossible you dimwit')

    def __str__(self): # Mostly for debugging
        lines = ['Encoding: %s' % self.encoding,
                 'Dimensions: %d x %d pixels' % (self.width, self.height),
                 'Color Range: 0 - %d' % self.range,
                 'Pixels: ']
        for i in range(self.height):
            row_line = []
            for j in range(self.width):
                if self.encoding == 'P3':
                    row_line.append('[%s]' % str(self.pixels[j][i])) # Do it the fucking archaic way without the index checks because why the hell not
                else:
                    row_line.append(str(self.pixels[j][i]).rjust(3))
            lines.append(' '.join(row_line))
        return '\n'.join(lines)

    def __repr__(self):
        return str(self)