

function initialize() {

    var myLatlng = new google.maps.LatLng(50.0755381,14.4378005);
    var mapOptions = {
        zoom: 5,
        center: myLatlng,
        disableDefaultUI: false
    };

    var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    /*
     // Example standard marker
     var marker = new google.maps.Marker({
     position: myLatlng,
     map: map,
     title: 'Hello World!'
     });
     */

    $( "#listFindora").find(" > li" ).each(function( index ) {
        var findoraLatlng = new google.maps.LatLng($(this).attr("lat"), $(this).attr("long"));

        var authors = [];
        var travels = [];
        $(this).find("ul").find("li").each(function(index) {
            var author = $(this).html();
            var found = $.inArray(author, authors) > -1;
            if(!found) {
                authors.push(author);
                travels.push($(this).attr("id"));
            }
        });
        overlay = new CustomMarker(
            findoraLatlng,
            map,
            {
                marker_id: $(this).attr("id"),
                nb_travel: $(this).attr("nb_travel"),
                name: $(this).attr("name"),
                authors: authors,
                travels: travels
            }
        );
    });
}

google.maps.event.addDomListener(window, 'load', initialize);