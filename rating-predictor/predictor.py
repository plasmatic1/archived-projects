import urllib.request as request
import json
import tkinter
from collections import namedtuple
from bisect import bisect

# This is the code of the contest you want to check
# Change this constant if you want to see the changes from a different contest.
# Note that there is a lack of info on many earlier contests so it's best to try
# on a currently running one.
#
# This program uses Ninjaclasher's rating prediction api
CONTEST = 'dmopc18c5'

# -- Constants

URL = 'https://ninjaclasher.tk/rating/contest/%s/api' % CONTEST
USER_AGENT = 'Mozilla/5.0'

USERS_KEY = 'users'
RATING_KEY = 'rating'
RATING_CHANGE_KEY = 'rating_change'
VOLATILITY_KEY = 'volatility'

RATING_TYPE_REQS = [0, 1000, 1200, 1500, 1800, 2200, 3000]
RATING_TYPE_NAMES = ['Newbie', 'Amateur', 'Expert', 'Candidate Master', 'Master', 'Grandmaster', 'Legendary Grandmaster']
RATING_TYPE_COLOURS = ['grey', 'green', 'blue', 'purple', 'goldenrod', 'red', 'black']
SORTING_KEYS = ['handle', 'old_rating', 'new_rating', 'change', 'volatility']

WIDTH = 600
BAR_WIDTH = 300
OPTIONS_HEIGHT = 100
RANKS_HEIGHT = 500
RANKS_PER_PAGE = 23
WINDOW_TITLE = 'DMOJ Visual Rating Predictor'
LABEL_TITLE_TEXT = 'Rating Changes for Contest \'%s\'' % CONTEST
LABEL_TITLE_FONT = ('Courier', 20)
NEGATIVE_CHANGE_CLR = 'firebrick3'
POSITIVE_CHANGE_CLR = 'green3'

# -- Utility Functions and Classes

def rating_index(rating):
    return bisect(RATING_TYPE_REQS, rating) - 1

def rating_name(rating):
    return RATING_TYPE_NAMES[rating_index(rating)]

def rating_colour(rating):
    return RATING_TYPE_COLOURS[rating_index(rating)]

def change_colour(change):
    if change > 0:
        return 'lime green'
    elif change == 0:
        return 'snow4'
    return 'firebrick2'

def change_bar_colour(change):
    if change > 0:
        return 'green3'
    return 'firebrick3'

def format_change(num):
    if num > 0:
        return '+%d' % num
    elif num == 0:
        return 'None'
    else:
        return str(num)

def load_data():
    data = None
    req = request.Request(url=URL, headers={'User-Agent': USER_AGENT})
    with request.urlopen(req) as req:
        data = json.loads(req.read())
    return data

class Progressbar(tkinter.Frame):
    def __init__(self, master, width, height=20, left_clr='green', right_clr='grey'):
        super().__init__(master=master, width=width, height=height)

        self.width = width
        self.height = height

        self.left_super = tkinter.Frame(master=self)
        self.right_super = tkinter.Frame(master=self)
    
        self.left = tkinter.Label(master=self.left_super, background=left_clr)
        self.right = tkinter.Label(master=self.right_super, background=right_clr)

        self.left_super.pack(side=tkinter.LEFT)
        self.right_super.pack(side=tkinter.LEFT)
        self.left_super.pack_propagate(0)
        self.right_super.pack_propagate(0)

        self.left.pack(fill=tkinter.BOTH, expand=1)
        self.right.pack(fill=tkinter.BOTH, expand=1)

        self.progress(0.)

    def progress(self, percent):
        self.left_super.configure(width=int(percent * self.width), height=self.height)
        self.right_super.configure(width=int((1 - percent) * self.width), height=self.height)

    def left_text(self, text):
        self.left.configure(text=text)

    def right_text(self, text):
        self.right.configure(text=text)

# Redefining load data for testing
'''
def load_data():
    data = None
    with open('tmp.json') as f:
        data = json.loads(f.readline())
        return data
'''

# -- Variables

Rank = namedtuple('Rank', 'handle old_rating new_rating change volatility')

ranks = []
rank_count = 0
rank_offset = 0
data = None
curr_key = None
reverse = None
ratings_loaded = False
highest_rating = 0

root = None
rank_display_frame = None
rank_widgets = []

# Actual Computing Stuff

def key_fun(rank_obj):
    global curr_key
    return getattr(rank_obj, curr_key.get())

def destroy_rank_widgets():
    global rank_widgets
    
    for widget in rank_widgets:
        widget.destroy()
    rank_widgets.clear()

def reset():
    global ranks, data, curr_key, rank_widgets, rank_count, rank_offset, highest_rating
    ranks = []
    rank_count = 0
    rank_offset = 0
    highest_rating = 0
    ratings_loaded = False
    
    destroy_rank_widgets()

def tts(cnt, rnk):
        print('#%d: %s %s (%d -> %d), %s rating' % (cnt, rating_name(rnk.new_rating), rnk.handle, rnk.old_rating, rnk.new_rating, format_change(rnk.change)))

def initialize_ranks():
    global ranks, data, rank_count, ratings_loaded, highest_rating

    data = load_data()

    for handle, data in data[USERS_KEY].items():
        rating = data[RATING_KEY]
        change = data[RATING_CHANGE_KEY]
        volatility = data[VOLATILITY_KEY]
        ranks.append(Rank(handle, rating - change, rating, change, volatility))

        highest_rating = max(highest_rating, rating, rating - change)

    ranks.sort(key=key_fun, reverse=reverse.get())
    rank_count = len(ranks)
    ratings_loaded = True

def display_curr_ranks():
    global ranks, rank_display_frame, rank_widgets, rank_count, rank_offset, highest_rating

    destroy_rank_widgets()

    begin = rank_offset
    end = min(rank_offset + RANKS_PER_PAGE, rank_count)

    # Makes a label (because I'm lazy)
    def make_label(text, foreground='black'):
        return tkinter.Label(master=rank_display_frame, text=text, foreground=foreground)

    # Finishes up the widgets (places and logs them)
    def finish(row, settings, widgets):
        for i in range(len(widgets)):
            widgets[i].grid(row=row, column=i, **settings[i])
            rank_widgets.append(widgets[i])

    for i in range(begin, end):
        rnk = ranks[i]

        # Display Calculations

        bar_size_ratio = max(rnk.new_rating, rnk.old_rating) / highest_rating
        bar_size = bar_size_ratio * BAR_WIDTH

        # Display Widgets
        
        place_label = make_label('#%d:' % (i + 1))
        handle_label = make_label(rnk.handle, rating_colour(rnk.new_rating))
        rating_bar = Progressbar(rank_display_frame, bar_size)
        change_label = make_label(format_change(rnk.change), change_colour(rnk.change))
        volatility_label = make_label('%dV' % rnk.volatility)
        new_rating_label = make_label(str(rnk.new_rating), rating_colour(rnk.new_rating))
        rating_separator_label = make_label('->')
        old_rating_label = make_label(str(rnk.old_rating), rating_colour(rnk.old_rating))

        # Bar Configuration and Whatnot

        if rnk.change > 0:
            rating_bar.right.config(background=POSITIVE_CHANGE_CLR)
            rating_bar.progress(rnk.old_rating / rnk.new_rating)
            #rating_bar.left_text(str(rnk.old_rating))
            rating_bar.left.config(background=rating_colour(rnk.old_rating))
        else:
            rating_bar.right.config(background=NEGATIVE_CHANGE_CLR)
            rating_bar.progress(rnk.new_rating / rnk.old_rating)
            #rating_bar.left_text(str(rnk.old_rating))
            rating_bar.left.config(background=rating_colour(rnk.new_rating))

        # Finish the widgets

        finish(i, [{}, {}, {'sticky': tkinter.W, 'padx': 10}, {}, {}, {}, {}, {}], [place_label, handle_label, rating_bar, old_rating_label, rating_separator_label, new_rating_label, change_label, volatility_label])

# Callbacks

def run_callback(event):
    reset()
    initialize_ranks()
    display_curr_ranks()

def prev_callback(event):
    global rank_offset, ratings_loaded

    if not ratings_loaded:
        return
    
    if rank_offset > 0:
        rank_offset -= RANKS_PER_PAGE
        display_curr_ranks()

def next_callback(event):
    global rank_offset, ratings_loaded

    if not ratings_loaded:
        return
    
    if rank_offset + RANKS_PER_PAGE < rank_count:
        rank_offset += RANKS_PER_PAGE
        display_curr_ranks()

# -- UI Building

# Frame Definitions and Other Starting Stuff
root = tkinter.Tk()
options_frame = tkinter.Frame(master=root, width=WIDTH, height=OPTIONS_HEIGHT)
rank_display_frame = tkinter.Frame(master=root, width=WIDTH, height=RANKS_HEIGHT)

root.title(WINDOW_TITLE)

# Options - Option Rows
title_row = tkinter.Frame(master=options_frame)
config_row = tkinter.Frame(master=options_frame)
scroll_row = tkinter.Frame(master=options_frame)

# Options - Title
title_label = tkinter.Label(master=title_row, text=LABEL_TITLE_TEXT, font=LABEL_TITLE_FONT)

# Options - Rank Sorting Variables
curr_key = tkinter.StringVar(master=root)
curr_key.set('new_rating')
reverse = tkinter.IntVar(master=root)
reverse.set(1)

# Options - Rank Sorting
sort_key_label = tkinter.Label(master=config_row, text='Sort By: ')
sort_key_list = tkinter.OptionMenu(config_row, curr_key, *SORTING_KEYS)
reverse_check_button = tkinter.Checkbutton(master=config_row, text='Reverse   ', variable=reverse, onvalue=1, offvalue=0)
run_button = tkinter.Button(master=config_row, text='Calculate Ratings!')

# Options - Previous and Next
prev_button = tkinter.Button(master=scroll_row, text='<')
next_button = tkinter.Button(master=scroll_row, text='>')


# Options - Event Binding
run_button.bind('<Button-1>', run_callback)
prev_button.bind('<Button-1>', prev_callback)
next_button.bind('<Button-1>', next_callback)

# Options - Packing on Row

title_label.pack()

sort_key_label.pack(side=tkinter.LEFT)
sort_key_list.pack(side=tkinter.LEFT)
reverse_check_button.pack(side=tkinter.LEFT)
run_button.pack(side=tkinter.LEFT)

prev_button.pack(side=tkinter.LEFT)
next_button.pack(side=tkinter.LEFT)

# Options - Row Packing

title_row.pack(anchor=tkinter.W)
config_row.pack(anchor=tkinter.W)
scroll_row.pack(anchor=tkinter.W)

# Final Packing

options_frame.pack(padx=10, pady=10)
rank_display_frame.pack(pady=10)

tkinter.mainloop()
