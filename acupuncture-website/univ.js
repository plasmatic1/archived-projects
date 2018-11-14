const F_NAME = window.location.pathname.split('/').slice(-1)[0];
var TEMPL = {
    css: `
    <link href="main.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">`,
    footer: `<br><br><br><br>
	<footer class="base-margin">&copy;五行痛症专科 2018<br>Design by Gloria Li and Moses Xu</footer>`,
    top: `<div class="container shrink heading base-margin">
    <div class="row title">
    <div class="col-2">
            <img class="float-left" src="imgs/logo.PNG" alt="Logo" dim="87%">
    </div>
        <div class="col-7">
    <div class="">
      <h1 class="display-4">五行痛症专科<br>Acupuncture &amp; Massage</h1>
    </div>	
        </div>
        
</div>
</div>

<div class="container shrink">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item index.html"> <a class="nav-link" href="index.html">首页<br>Home</a>
                </li>
                <li class="nav-item about.html"> <a class="nav-link" href="about.html">关于我们<br>About Us</a>
                </li>
                <li class="nav-item contact.html"> <a class="nav-link" href="contact.html">联系我们<br>Contact Us</a>
                </li>
                <li class="nav-item fees.html"> <a class="nav-link" href="fees.html">医疗项目和收费<br>
                Services and Fees</a>
                </li>
                <li class="nav-item health.html"> <a class="nav-link" href="health.html">健康讲座<br>Health Lectures</a>
                </li>
            </ul>
        </div>
    </nav>
</div>`.replace(F_NAME, 'active')
     //SOOOOOOOO HACKY
};

$('head').append(TEMPL.css);
$('body').prepend(TEMPL.top).append(TEMPL.footer);

$(document).ready(function() {
    //Image sizing (Mainly for contact)
    $('img').each(function(index, el) {
        var props = $(this).attr('dim');

        if (props) {
            props = props.split(' ');
        } else {
            return;
        }

        $(this).attr('style', 'width:' + props[0] + ';height:' + props[1] + ';');
    });

    $('p, div, span').each(function(index, el){
        var color = $(this).attr('color');  
        if (color) {
            // console.log('Element', $(this).nodeName, ', has css', $(this).css());
            $(this).css('color', color);
        }
    });

    $('div[page-top]').each(function(index, el){
        var parts = $(this).attr('page-top').split(',');
        var text = parts[0], width = parts[1];

        $(this).append(`<h6 class="display-3 page-top-text">` + text + `</h6>`)
        .append(`<hr width="` + width + `">`);
    });
});