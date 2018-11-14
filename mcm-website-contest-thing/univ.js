//Universal Scripts

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

    //scip reference
    $('p, div, span').each(function(index, el) {
        // console.log($(this).html());
        var htmlAttr = $(this).html();
        if(htmlAttr){
            console.log('Attempting replace:, prop', htmlAttr)
            $(this).html(htmlAttr
                .replace(/\$red/g, '<code>[Redacted]</code>')
                .replace(/\$exp-o5/g, '<code>[Data Expunged - O5 Security Clearence Required]</code>')
                .replace(/\$exp/g, '<code>[Data Expunged]</code>')
                .replace(/\$missing/g, '<code>[SYSTEM ERROR: DATA CORRUPTED. PLEASE SEE A NETWORK ADMINISTRATOR FOR MORE DETAILS]</code>')
            )
        }
    });

    console.log('Completed Replacements')

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

$(document).ready(function(){
    var $searchInput = $('.search-input');
    var $searchBtn = $('.search-btn');

    $searchBtn.prop("disabled", true);

    $searchInput.keyup(function(){
        console.log('val', $searchInput.val())
        if(!$searchInput.val()){
            console.log('fail');
            $searchBtn.prop("disabled", true);
        }
        else{
            console.log('pass');
            $searchBtn.prop("disabled", false);
        }
    });
});

function googleSearch(){
    var val = $searchInput.val();

    if(!val){
        return;
    }

    window.location.href = 'https://www.google.ca/search?q=' + escape(val);

    return false;
}