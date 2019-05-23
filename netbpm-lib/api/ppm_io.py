from api.ppm_types import rgb, Picture
import re

'''
File Format:

<File encoding> (P1 (BW), P2 (Greyscale), P3 (RGB))
<Size> (Width, Height)
<Accuracy> (1 - 255)
<Content> ...
'''

'''
Reads whatever is at the specified path and returns a _types.picture object constructed by it
'''


def read(path):
    with open(path, 'r') as f:
        content = [line.strip() for line in f.readlines()]  # Strips newlines
        header = content[:3]
        content = content[3:]

    # Read Header
    p_encoding = header[0]
    p_width, p_height = [int(p) for p in header[1].split(' ')]
    p_range = int(header[2])

    # Possible invalid inputs

    if p_range > 255:
        raise ValueError('Colour Range is greater than 255!')

    if p_encoding != 'P1' and p_encoding != 'P2' and p_encoding != 'P3':
        raise ValueError('Invalid encoding! (%s)' % p_encoding)

    if p_width < 1 or p_height < 1:
        raise ValueError('Invalid dimensions! (%d x %d)' % (p_width, p_height))

    # Read Content
    pict = Picture(p_encoding, p_width, p_height, p_range)

    for i, line in enumerate(content):
        pix = iter(map(int, re.split(' +', line)))
        for j in range(p_width):
            if p_encoding == 'P3':
                r = next(pix)
                g = next(pix)
                b = next(pix)
                pict[j, i] = rgb(r, g, b)
            else:
                bw = next(pix)
                pict[j, i] = bw

    return pict


def write(path, pict):
    with open(path, 'w') as f:
        f.write('%s\n%d %d\n%d\n' % (pict.encoding, pict.width, pict.height, pict.range))

        for row in pict.pixels:
            line = []
            for pixel in row:
                line.append(str(pixel))
            f.write('%s\n' % ' '.join(line))
