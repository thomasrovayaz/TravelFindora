/**
 * Created by thomas on 14/06/15.
 */

function addTravelPhoto(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/image/add";
}

function addTravelVideo(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/movie/add";

}

function addTravelStory(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/story/add";

}

function addTravelPlace(travelId, findoraId) {
    window.location.href = "/" + travelId + "/" + findoraId + "/content/place/add";

}

function addFindora(travelId) {
    $("#addFindora").show();
    //window.location.href = "/travel/" + travelId + "/toto/add";
}

function searchFindora() {
    $('#findoraName').autocomplete({
        source : function(requete, reponse){ // les deux arguments représentent les données nécessaires au plugin
            $.ajax({
                url : "http://localhost:9000/findora/search/" +  $('#findoraName').val(),
                dataType : 'json',

                success : function(donnee){
                    console.log(donnee);
                    reponse($.map(donnee, function(objet){
                        console.log(objet);
                        return objet[4]; // on retourne cette forme de suggestion
                    }));
                }
            });
        }
    });
}

$(document).ready(function () {
    searchFindora();
});