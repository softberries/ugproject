/**
 * Created by Maciej Rudnicki on 2016-01-05.
 */

$(document).ready(function() {
    EUKW.init();
});

UG = {
    init : function() {
        this.infiniteScrollFunction();
        this.tootip();

    },



    infiniteScrollFunction : function(){
        $('#content').infinitescroll({

            navSelector: "#next:last",
            nextSelector: "#next:last",
            itemSelector: "#content",
            debug: false,
            dataType: 'html',
            maxPage: 7,

            path: function(index) {
                return "parts/index" + index + ".html";
            }
        });
    },
    tootip : function(){
        $( '#content' ).tooltip();
    },

}

