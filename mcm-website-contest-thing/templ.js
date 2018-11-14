const F_NAME = window.location.pathname.split('/').slice(-1)[0];
var TEMPL = {
    css: '<link rel="stylesheet" href="main.css">',
    top: `  <div class="container">
    <span class="display-1 head">Motorsports Car Sales&trade;</span>
    <!-- <img src="https://logopond.com/logos/70d621242e939dfcdb2b9bdc562c7f23.png" style="width:8%;height:8%;"> -->
    <img src="logo.PNG" class="logo" align="top">
</div>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">Navigate</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto">
            <li class="nav-item index.html">
              <a class="nav-link" href="index.html">Home</a>
            </li>
            <li class="nav-item about.html">
              <a class="nav-link" href="about.html">About Us</a>
            </li>
            <li class="nav-item sales.html">
              <a class="nav-link" href="sales.html" >
                Inventory
              </a>
            </li>
            <li class="nav-item contact.html">
              <a class="nav-link" href="contact.html">Contact</a>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0" action="http://www.google.ca/search">
            <!-- <input class="form-control mr-sm-2 search-input" type="search" placeholder="Search" aria-label="Search"> -->
            <!-- <button class="btn btn-outline-success my-2 my-sm-0 search-btn" type="submit" onclick="return googleSearch()">Search</button> -->

            <input class="form-control mr-sm-2 search-input" type="search" placeholder="Search" aria-label="Search" name="q">
            <button class="btn btn-outline-success my-2 my-sm-0 search-btn" type="submit">Search</button>
          </form>
        </div>
</nav>`.replace(F_NAME, 'active')
     //SOOOOOOOO HACKY
};

$('head').append(TEMPL.css);
$('body').prepend(TEMPL.top);